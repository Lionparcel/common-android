package com.lionparcel.commonandroidsample.datepicker

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.lionparcel.commonandroid.datepicker.LPDatePickerRange
import com.lionparcel.commonandroid.datepicker.LPDatePickerSingle
import com.lionparcel.commonandroid.datepicker.utils.DateUtils
import com.lionparcel.commonandroid.datepicker.utils.toDate
import com.lionparcel.commonandroid.datepicker.utils.toLocaleDate
import com.lionparcel.commonandroidsample.R
import java.lang.ref.WeakReference
import java.time.LocalDate
import java.time.YearMonth
import java.util.*

class DatePickerSampleActivity : AppCompatActivity() {

    companion object {
        private const val DATE_PICKER_RANGE_DIALOG = "DATE_PICKER_RANGE_DIALOG"
        private const val DATE_PICKER_SINGLE_DIALOG = "DATE_PICKER_SINGLE_DIALOG"
    }

    private var startDate: Date? = null
    private var endDate: Date? = null
    private val dialogFragmentMap = mutableMapOf<String, WeakReference<DialogFragment>>()

    private var selectedDate: Date? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_picker_sample)
        findViewById<LinearLayout>(R.id.llDatePicker).setOnClickListener {
            showDatePickerRangeDialog()
        }
        findViewById<LinearLayout>(R.id.llDatePicker2).setOnClickListener {
            showDatePickerSingleDialog()
        }
        findViewById<LinearLayout>(R.id.llDatePicker3).setOnClickListener {
            showDatePickerRangeDADialog()
        }
        setSelectedDateView(findViewById(R.id.tvDatePick))
        setSelectedSingleDateView(findViewById(R.id.tvDatePick2))
        setSelectedDateView(findViewById(R.id.tvDatePick3))
    }

    init {
        startDate = run {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DATE, -2)
            DateUtils.dateToString(calendar.time, DateUtils.DATE_NUMBER_FORMAT)
            calendar.time
        }
        endDate = Date()
        selectedDate = Date()
    }

    private fun setSelectedDateView(text: TextView) {
        text.text = startDate?.let {
            DateUtils.dateToString(it, DateUtils.DATE_FORMAT)
        }?.let {
            endDate?.let { endDate ->
                "$it - ${DateUtils.dateToString(endDate, DateUtils.DATE_FORMAT)}"
            } ?: it
        }
    }

    private fun setSelectedSingleDateView(text: TextView) {
        text.text = selectedDate?.let {
            DateUtils.dateToString(it, DateUtils.DATE_FORMAT)
        }
    }

    private fun showDatePickerRangeDialog() {
        val start = startDate ?: return
        showCustomDialog(
            supportFragmentManager,
            DATE_PICKER_RANGE_DIALOG,
            LPDatePickerRange.newInstance(
                start.toLocaleDate(),
                endDate?.toLocaleDate(),
                this::onChooseButtonDatePickerRange
            )
        )
    }

    private fun showDatePickerRangeDADialog() {
        val start = startDate ?: return
        showCustomDialog(
            supportFragmentManager,
            DATE_PICKER_RANGE_DIALOG,
            LPDatePickerRange.newInstanceWithAlert(
                start.toLocaleDate(),
                null,
                this::onChooseButtonDatePickerRange,
                isShowAlert = true,
                alertMessage = "Periode riwayat yang dapat dipilih maksimal 31 hari yang lalu",
                maxStartDate = 20L,
                maxRangeDateSelected = 5,
                showErrorSnackBar = true
            )
        )
    }

    private fun showDatePickerSingleDialog() {
        val date = selectedDate ?: return
        showCustomDialog(
            supportFragmentManager,
            DATE_PICKER_SINGLE_DIALOG,
            LPDatePickerSingle.newInstance(
                date.toLocaleDate(),
                this::onChooseButtonDatePickerSingle,
                maxStartDate = 20L,
                showErrorSnackBar = true
            )
        )
    }

    private fun onChooseButtonDatePickerRange(startDate: LocalDate, endDate: LocalDate?) {
        dialogFragmentMap[DATE_PICKER_RANGE_DIALOG]?.get()?.dismissAllowingStateLoss()
        dialogFragmentMap.remove(DATE_PICKER_RANGE_DIALOG)
        this.startDate = startDate.toDate()
        this.endDate = endDate?.toDate()
        setSelectedDateView(findViewById(R.id.tvDatePick))
    }

    private fun onChooseButtonDatePickerSingle(selectedDate: LocalDate) {
        dialogFragmentMap[DATE_PICKER_SINGLE_DIALOG]?.get()?.dismissAllowingStateLoss()
        dialogFragmentMap.remove(DATE_PICKER_SINGLE_DIALOG)
        this.selectedDate = selectedDate.toDate()
        setSelectedSingleDateView(findViewById(R.id.tvDatePick2))
    }

    private fun showCustomDialog(
        fragmentManager: FragmentManager,
        key: String,
        dialogFragment: DialogFragment
    ) {
        fragmentManager.let { manager ->
            dialogFragmentMap[key]?.get()?.dismissAllowingStateLoss()
            dialogFragmentMap[key] = WeakReference(dialogFragment)
            dialogFragmentMap[key]?.get()?.show(manager, key)
        }
    }
}