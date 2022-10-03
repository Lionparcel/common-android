package com.lionparcel.commonandroid.datepicker

import android.content.res.Resources
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.*
import androidx.recyclerview.widget.RecyclerView
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import com.kizitonwose.calendarview.utils.Size
import com.kizitonwose.calendarview.utils.next
import com.kizitonwose.calendarview.utils.previous
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.databinding.LpDatePickerDayBinding
import com.lionparcel.commonandroid.databinding.LpLayoutDatePickerRangeBinding
import com.lionparcel.commonandroid.datepicker.utils.*
import com.lionparcel.commonandroid.snackbartoast.MessageType
import com.lionparcel.commonandroid.snackbartoast.showSnackbarSmallIconNoClose
import com.lionparcel.commonandroid.walkthrough.utils.ScreenUtils
import com.lionparcel.commonandroid.walkthrough.utils.toDp
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

class LPDatePickerRange: BaseDatePicker() {

    inner class DayViewContainer(view: View): ViewContainer(view) {
        lateinit var day: CalendarDay
        val binding = LpDatePickerDayBinding.bind(view)

        init {
            view.setOnClickListener {
                onDateClicked(day)
            }
        }
    }

    companion object {
        private const val DISABLE_ALPHA_VALUE = 0.5F
        private const val ENABLE_ALPHA_VALUE = 1.0F
        private const val MAX_RANGE_DATE_SELECTED = 30
        private const val MIN_MONTH_DATE = 12L * 5L
        private const val MIN_DAY_MONTH = 1

        fun newInstance(
            startDate: LocalDate,
            endDate: LocalDate?,
            onChooseButtonClicked: (LocalDate, LocalDate?) -> Unit,
            isUseLimit: Boolean = false,
            intervalDay: Long? = 0L
        ) = LPDatePickerRange().apply {
            this.startDate = startDate
            this.endDate = endDate
            this.onChooseButtonClicked = onChooseButtonClicked
            this.isUseLimit = isUseLimit
            intervalDay?.let { this.intervalDay = it }
        }

        fun newInstanceOptional(
            startDate: LocalDate?,
            endDate: LocalDate?,
            onChooseButtonClickedOptional: (LocalDate?, LocalDate?) -> Unit,
            isUseLimit: Boolean = false,
            intervalDay: Long? = 0L
        ) = LPDatePickerRange().apply {
            this.startDate = startDate
            this.endDate = endDate
            this.onChooseButtonClickedOptional = onChooseButtonClickedOptional
            this.isUseLimit = isUseLimit
            intervalDay?.let { this.intervalDay = it }
        }
    }

    private val today = LocalDate.now()
    private var intervalDay: Long = 60L
    private val shipmentFilterLimitDate = LocalDate.now().minusDays(intervalDay)
    private var startDate: LocalDate? = null
    private var endDate: LocalDate? = null
    private val monthTittleFormatter = DateTimeFormatter.ofPattern("MMM")
    private var onChooseButtonClicked: ((LocalDate, LocalDate?) -> Unit)? = null
    private var onChooseButtonClickedOptional: ((LocalDate?, LocalDate?) -> Unit)? = null
    private var isUseLimit = false

    private val dayOfWeek by lazy {
        arrayOf(
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY,
            DayOfWeek.SUNDAY
        )
    }

    private val localeId by lazy {
        Locale("id", "ID")
    }

    private val startBackground: GradientDrawable by lazy {
        ContextCompat.getDrawable(
            requireContext(),
            R.drawable.bg_date_picker_continous_selected_start
        ) as GradientDrawable
    }

    private val endBackground: GradientDrawable by lazy {
        ContextCompat.getDrawable(
            requireContext(),
            R.drawable.bg_date_picker_continous_selected_end
        ) as GradientDrawable
    }

    private lateinit var binding: LpLayoutDatePickerRangeBinding

    override fun getContentResource() = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LpLayoutDatePickerRangeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initViews() {
        super.initViews()
        val maxHeight = (0.99F * Resources.getSystem().displayMetrics.heightPixels).toInt()
        getLayoutBehaviour().peekHeight = maxHeight
        with(binding) {
            if (isUseLimit) {
                tvClear.text = getString(R.string.general_reset)
            }
            tvClear.setOnClickListener {
                onClearSelectedDate()
            }
            ivChevronUp.setOnClickListener {
                onScrollPreviousMonth()
            }
            ivChevronDown.setOnClickListener {
                onScrollNextMonth()
            }
            btnChoose.setOnClickListener {
                startDate?.let {
                    onChooseButtonClicked?.invoke(it, endDate) ?: onChooseButtonClickedOptional?.invoke(it, endDate)
                } ?: onChooseButtonClickedOptional?.invoke(null, null)
            }
        }
        setDaySize()
        changeStateButtonChoose()
        setupCalendarView()
        bindCalendarDayView()
        addOnScrollCalendarView()
        bindSummaryDateSelectedViews()
    }

    private fun setDaySize() {
        val size = ScreenUtils.getScreenWidthCompat(requireActivity()) / 7
        binding.calendarView.apply {
            daySize = Size(size, size)
            setMonthPadding(monthPaddingStart, 4.toDp(), monthPaddingEnd, 4.toDp())
        }
        binding.calendarView.post {
            val radius = (size / 2).toFloat()
            startBackground.setCornerRadius(topLeft = radius, bottomLeft = radius)
            endBackground.setCornerRadius(topRight = radius, bottomRight = radius)
        }
    }

    private fun setupCalendarView() {
        val currentMonth = YearMonth.now()
        binding.calendarView.setup(
            currentMonth.minusMonths(MIN_MONTH_DATE),
            currentMonth,
            dayOfWeek.first()
        )
        binding.calendarView.post {
            binding.calendarView.scrollToMonth(
                (endDate ?: startDate)?.let {
                    YearMonth.from(it)
                } ?: currentMonth
            )
        }
    }

    private fun bindCalendarDayView() {
        binding.calendarView.dayBinder = object : DayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.day = day
                val tvDay = container.binding.tvDay
                val vRoundBackground = container.binding.vRoundBackground
                val llBgStart = container.binding.llBgStart
                val llBgEnd = container.binding.llBgEnd
                tvDay.run {
                    text = null
                    background = null
                    typeface = ResourcesCompat.getFont(context, R.font.poppins_regular)
                }
                vRoundBackground.run {
                    isVisible = false
                    updateLayoutParams<FrameLayout.LayoutParams> {
                        setMargins(8.toDp(), marginTop, 8.toDp(), marginBottom)
                    }
                }
                llBgEnd.isVisible = false
                llBgStart.isVisible = false
                when (day.owner) {
                    DayOwner.THIS_MONTH -> {
                        tvDay.text = day.day.toString()
                        if (day.date.isAfter(today)) {
                            tvDay.setTextColor(ResourcesCompat.getColor(resources, R.color.shades3, null))
                        } else {
                            if (isDateInRange(day)) setStateSelectedDateThisMonth(container, day)
                            else tvDay.setTextColor(ResourcesCompat.getColor(resources, R.color.shades3, null))
                        }
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun setStateSelectedDateThisMonth(container: DayViewContainer, day: CalendarDay) {
        with(container.binding) {
            when {
                startDate == day.date && endDate == null -> {
                    tvDay.changeStateToSelected()
                    vRoundBackground.isVisible = true
                    vRoundBackground.setBackgroundResource(R.drawable.bg_date_picker_single_selected)
                }
                startDate == day.date -> {
                    tvDay.changeStateToSelected()
                    llBgEnd.isVisible =
                        day.date.dayOfWeek != DayOfWeek.SUNDAY && day.date.dayOfMonth != day.date.month.minLength()
                    vRoundBackground.isVisible = true
                    vRoundBackground.setBackgroundResource(R.drawable.bg_date_picker_single_selected)
                }
                startDate != null && endDate != null && (day.date > startDate && day.date < endDate) -> {
                    tvDay.setTextColor(ResourcesCompat.getColor(resources, R.color.shades5, null))
                    changeStateBackgroundSelectedMiddle(container, day)
                }
                endDate == day.date -> {
                    tvDay.changeStateToSelected()
                    llBgStart.isVisible =
                        day.date.dayOfWeek != DayOfWeek.MONDAY && day.date.dayOfMonth != MIN_DAY_MONTH
                    vRoundBackground.isVisible = true
                    vRoundBackground.setBackgroundResource(R.drawable.bg_date_picker_single_selected)
                }
                day.date == today -> {
                    tvDay.setTextColor(ResourcesCompat.getColor(resources, R.color.interpack6, null))
                }
                else -> tvDay.setTextColor(ResourcesCompat.getColor(resources, R.color.shades5, null))
            }
        }
    }

    private fun bindSummaryDateSelectedViews() {
        if (startDate != null) {
            binding.llEndDate.alpha = ENABLE_ALPHA_VALUE
            binding.tvStartDate.text =
                startDate?.let { DateUtils.dateToString(it.toDate(), DateUtils.DATE_FORMAT) }
            binding.tvEndDate.text = if (endDate != null) {
                endDate?.let { DateUtils.dateToString(it.toDate(), DateUtils.DATE_FORMAT) }
            } else getString(R.string.date_picker_title)
        } else {
            binding.llEndDate.alpha = DISABLE_ALPHA_VALUE
            getString(R.string.date_picker_title).let {
                binding.tvEndDate.text = it
                binding.tvStartDate.text = it
            }
        }
        changeStateButtonChoose()
    }

    private fun addOnScrollCalendarView() {
        with(binding) {
            calendarView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    ivChevronDown.isEnabled =
                        calendarView.findFirstVisibleMonth()?.month != today?.monthValue
                    ivChevronDown.alpha = if (binding.ivChevronDown.isEnabled) {
                        ENABLE_ALPHA_VALUE
                    } else DISABLE_ALPHA_VALUE
                    ivChevronUp.isEnabled = recyclerView.canScrollVertically(-1)
                    ivChevronUp.alpha = if (binding.ivChevronUp.isEnabled) {
                        ENABLE_ALPHA_VALUE
                    } else DISABLE_ALPHA_VALUE
                    setMonthLabel()
                }
            })
        }
    }

    private fun setMonthLabel() {
        binding.calendarView.findFirstVisibleMonth()?.let {
            val title = "${
                monthTittleFormatter.withLocale(localeId).format(it.yearMonth)
            } ${it.yearMonth.year}"
            binding.tvMonth.text = title
        }
    }

    private fun onDateClicked(day: CalendarDay) {
        val isTodayOrBeforeToday = day.date == today || day.date.isBefore(today)
        if (day.owner == DayOwner.THIS_MONTH && isTodayOrBeforeToday && isDateInRange(day)) {
            val date = day.date
            val isStartDate = startDate?.isEqual(date) ?: false
            val isEndDate = endDate?.isEqual(date) ?: false
            if (isEndDate || isStartDate) return
            if (startDate != null) {
                if (date < startDate || endDate != null) {
                    startDate = date
                    endDate = null
                } else if (date != startDate) {
                    val isNotSameMonth = startDate?.monthValue != date.monthValue
                    if ((ChronoUnit.DAYS.between(
                            startDate,
                            date
                        ) + 1) > MAX_RANGE_DATE_SELECTED && isNotSameMonth
                    ) {
                        requireContext().showSnackbarSmallIconNoClose(
                            this.binding.parent,
                            getString(R.string.date_picker_error_message),
                            R.drawable.ics_f_warning_circle_yellow6,
                            MessageType.ERROR
                        )
                        return
                    }
                    endDate = date
                }
            } else {
                startDate = date
            }
            this.binding.calendarView.notifyCalendarChanged()
            bindSummaryDateSelectedViews()
        }
    }

    private fun changeStateButtonChoose() {
        binding.btnChoose.isEnabled = if (onChooseButtonClickedOptional == null) startDate != null else true
        binding.tvClear.isEnabled = startDate != null || endDate != null
        binding.tvClear.alpha = if (binding.tvClear.isEnabled) {
            ENABLE_ALPHA_VALUE
        } else {
            DISABLE_ALPHA_VALUE
        }
    }

    private fun onClearSelectedDate() {
        startDate = if (isUseLimit) Date().toLocaleDate() else null
        endDate = null
        binding.calendarView.notifyCalendarChanged()
        bindSummaryDateSelectedViews()
    }

    private fun onScrollNextMonth() {
        with(binding) {
            ivChevronDown.setOnClickListener {
                calendarView.findFirstVisibleMonth()?.let {
                    calendarView.smoothScrollToMonth(it.yearMonth.next)
                }
            }
        }
    }

    private fun onScrollPreviousMonth() {
        with(binding) {
            ivChevronUp.setOnClickListener {
                calendarView.findFirstVisibleMonth()?.let {
                    calendarView.smoothScrollToMonth(it.yearMonth.previous)
                }
            }
        }
    }


    private fun TextView.changeStateToSelected() {
        setTextColor(ResourcesCompat.getColor(resources, R.color.white, null))
        typeface = ResourcesCompat.getFont(context, R.font.poppins_semi_bold)
    }

    private fun changeStateBackgroundSelectedMiddle(
        container: DayViewContainer,
        day: CalendarDay
    ) {
        val date = day.date
        with(container.binding) {
            when {
                date.dayOfWeek == DayOfWeek.SUNDAY || date.dayOfMonth == date.month.maxLength() -> {
                    vRoundBackground.run {
                        isVisible = true
                        background = endBackground
                        updateLayoutParams<FrameLayout.LayoutParams> {
                            setMargins(0, marginTop, marginRight, marginBottom)
                        }
                    }
                }
                date.dayOfWeek == DayOfWeek.MONDAY || date.dayOfMonth == MIN_DAY_MONTH -> {
                    vRoundBackground.run {
                        isVisible = true
                        background = startBackground
                        updateLayoutParams<FrameLayout.LayoutParams> {
                            setMargins(marginLeft, marginTop, 0, marginBottom)
                        }
                    }
                }
                else -> tvDay.setBackgroundResource(R.drawable.bg_date_picker_continous_selected_middle)
            }
        }
    }

    private fun isDateInRange(day: CalendarDay): Boolean = if (!isUseLimit) true else day.date.isAfter(shipmentFilterLimitDate)
}