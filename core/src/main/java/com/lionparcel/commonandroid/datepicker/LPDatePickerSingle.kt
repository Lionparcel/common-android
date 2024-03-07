package com.lionparcel.commonandroid.datepicker

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
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
import com.lionparcel.commonandroid.databinding.LpLayoutDatePickerSingleBinding
import com.lionparcel.commonandroid.datepicker.utils.BaseDatePicker
import com.lionparcel.commonandroid.snackbartoast.MessageType
import com.lionparcel.commonandroid.snackbartoast.showToastSmallIconNoClose
import com.lionparcel.commonandroid.walkthrough.utils.ScreenUtils
import com.lionparcel.commonandroid.walkthrough.utils.toDp
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*

class LPDatePickerSingle : BaseDatePicker() {

    inner class DayViewContainer(view: View) : ViewContainer(view) {
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
        private const val MIN_MONTH_DATE = 12L * 5L

        fun newInstance(
            selectedDate: LocalDate? = null,
            onChooseButtonClicked: (LocalDate) -> Unit,
            title: String? = null,
            btnTitle: String? = null,
            maxStartDate: Long? = null,
            minDate: LocalDate? = null, // all dates before minDate are disabled
            maxDate: LocalDate? = null, // all dates after maxDate are disabled
            showErrorSnackBar: Boolean = false,
            disabledDates: List<LocalDate>? = null // all dates contains in list are disabled
        ) = LPDatePickerSingle().apply {
            this.selectedDate = selectedDate
            this.onChooseButtonClicked = onChooseButtonClicked
            this.title = title
            this.btnTitle = btnTitle
            this.maxStartDate = maxStartDate
            this.minDate = minDate
            this.maxDate = maxDate
            this.disabledDates = disabledDates
            this.showErrorSnackBar = showErrorSnackBar
        }

        fun newInstanceOptional(
            selectedDate: LocalDate?,
            onChooseButtonClickedOptional: (LocalDate?) -> Unit,
            title: String? = null,
            btnTitle: String? = null,
            maxStartDate: Long? = null,
            showErrorSnackBar: Boolean = false
        ) = LPDatePickerSingle().apply {
            this.selectedDate = selectedDate
            this.onChooseButtonClickedOptional = onChooseButtonClickedOptional
            this.title = title
            this.btnTitle = btnTitle
            this.maxStartDate = maxStartDate
            this.showErrorSnackBar = showErrorSnackBar
        }
    }

    private val today = LocalDate.now()
    private var selectedDate: LocalDate? = null
    private val monthTittleFormatter = DateTimeFormatter.ofPattern("MMM")
    private var onChooseButtonClicked: ((LocalDate) -> Unit)? = null
    private var onChooseButtonClickedOptional: ((LocalDate?) -> Unit)? = null
    private var title: String? = null
    private var btnTitle: String? = null
    private var maxStartDate: Long? = null
    private var minDate: LocalDate? = null
    private var maxDate: LocalDate? = null
    private var disabledDates: List<LocalDate>? = null
    private var showErrorSnackBar = false

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

    lateinit var binding: LpLayoutDatePickerSingleBinding

    override fun getContentResource() = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LpLayoutDatePickerSingleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initViews() {
        super.initViews()
        val maxHeight = (0.99F * Resources.getSystem().displayMetrics.heightPixels).toInt()
        getLayoutBehaviour().peekHeight = maxHeight
        with(binding) {
            tvDatePickerTitle.text = title ?: getString(R.string.date_picker_title)
            btnChoose.text = btnTitle ?: getString(R.string.general_select)
            ivClose.setOnClickListener {
                dismiss()
            }
            ivChevronLeft.setOnClickListener {
                onScrollPreviousMonth()
            }
            ivChevronRight.setOnClickListener {
                onScrollNextMonth()
            }
            btnChoose.setOnClickListener {
                selectedDate?.let {
                    onChooseButtonClicked?.invoke(it) ?: onChooseButtonClickedOptional?.invoke(it)
                } ?: onChooseButtonClickedOptional?.invoke(null)
            }
        }
        setDaySize()
        changeStateButtonChoose()
        setupCalendarView()
        bindCalendarDayView()
        addOnScrollCalendarView()
    }

    private fun setDaySize() {
        val size = (ScreenUtils.getScreenWidthCompat(requireActivity()) / 7) - 4
        binding.calendarView.apply {
            daySize = Size(size, size)
            setMonthPadding(4.toDp(), monthPaddingTop, 4.toDp(), monthPaddingBottom)
            layoutParams.width = ScreenUtils.getScreenWidthCompat(requireActivity())
        }
        Log.e("Date", size.toString())
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
                (selectedDate)?.let {
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

                var isDisabledDates = false
                disabledDates?.let {
                    for (disabledDate in it) {
                        if (day.date.isEqual(disabledDate)) isDisabledDates = true
                        break
                    }
                }

                tvDay.text = day.day.toString()
                if ((maxDate != null && day.date.isAfter(maxDate)) || (minDate != null && day.date.isBefore(minDate)) || isDisabledDates) {
                    tvDay.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.shades3,
                            null
                        )
                    )
                } else {
                    setStateSelectedDateThisMonth(container, day)
                }
            }
        }
    }

    private fun setStateSelectedDateThisMonth(container: DayViewContainer, day: CalendarDay) {
        with(container.binding) {
            when {
                selectedDate == day.date -> {
                    tvDay.changeStateToSelected()
                    vRoundBackground.isVisible = true
                    vRoundBackground.setBackgroundResource(R.drawable.bg_date_picker_single_selected)
                }
                day.date == today -> {
                    tvDay.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.interpack7,
                            null
                        )
                    )
                }
                else -> {
                    tvDay.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.shades5,
                            null
                        )
                    )
                }
            }
        }
    }

    private fun addOnScrollCalendarView() {
        with(binding) {
            calendarView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    ivChevronRight.isEnabled =
                        calendarView.findFirstVisibleMonth()?.month != today?.monthValue
                    ivChevronRight.alpha = if (binding.ivChevronRight.isEnabled) {
                        ENABLE_ALPHA_VALUE
                    } else DISABLE_ALPHA_VALUE
                    ivChevronLeft.isEnabled = recyclerView.canScrollHorizontally(-1)
                    ivChevronLeft.alpha = if (binding.ivChevronLeft.isEnabled) {
                        ENABLE_ALPHA_VALUE
                    } else DISABLE_ALPHA_VALUE
                    tvNextMonth.alpha = if (binding.ivChevronRight.isEnabled) {
                        ENABLE_ALPHA_VALUE
                    } else DISABLE_ALPHA_VALUE
                    tvPreviousMonth.alpha = if (binding.ivChevronLeft.isEnabled) {
                        ENABLE_ALPHA_VALUE
                    } else DISABLE_ALPHA_VALUE
                    setMonthLabel()
                }
            })
        }
    }

    private fun setMonthLabel() {
        binding.calendarView.findFirstVisibleMonth()?.let {
            val monthTitle = "${
                monthTittleFormatter.withLocale(localeId).format(it.yearMonth)
            } ${it.yearMonth.year}"
            val monthBeforeTitle = "${
                monthTittleFormatter.withLocale(localeId).format(it.yearMonth.previous)
            } "
            val monthAfterTitle = "${
                monthTittleFormatter.withLocale(localeId).format(it.yearMonth.next)
            } "
            binding.tvMonth.text = monthTitle
            binding.tvPreviousMonth.text = monthBeforeTitle
            binding.tvNextMonth.text = monthAfterTitle
        }
    }

    private fun onDateClicked(day: CalendarDay) {
        // disable click all dates before minDate
        minDate?.let {
            if (day.date.isBefore(it)) return
        }

        // disable click all dates after maxDate
        maxDate?.let {
            if (day.date.isAfter(it)) return
        }

        // disable click all dates if contains in disabledDates
        disabledDates?.let {
            for (disabledDate in it) {
                if (day.date.isEqual(disabledDate)) return
            }
        }

        if (day.owner == DayOwner.THIS_MONTH) {
            val currentSelection = selectedDate
            val date = day.date
            if (currentSelection == date) {
                selectedDate = null
            } else {
                if (maxStartDate != null) {
                    if (date.isBefore(LocalDate.now().minusDays(maxStartDate ?: 0L))) {
                        if (showErrorSnackBar) {
                            requireContext().showToastSmallIconNoClose(
                                this.binding.parent,
                                getString(R.string.date_picker_max_start_date_error_message, (maxStartDate ?: 0L).toString()),
                                R.drawable.ics_f_warning_circle_white,
                                MessageType.ERROR
                            )
                        }
                        return
                    }
                }
                selectedDate = date
            }
            this.binding.calendarView.notifyCalendarChanged()
        }

        changeStateButtonChoose()
    }

    private fun changeStateButtonChoose() {
        binding.btnChoose.isEnabled =
            if (onChooseButtonClickedOptional == null) selectedDate != null else true
    }

    private fun onScrollNextMonth() {
        with(binding) {
            ivChevronRight.setOnClickListener {
                calendarView.findFirstVisibleMonth()?.let {
                    calendarView.smoothScrollToMonth(it.yearMonth.next)
                }
            }
        }
    }

    private fun onScrollPreviousMonth() {
        with(binding) {
            ivChevronLeft.setOnClickListener {
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
}