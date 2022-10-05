package com.lionparcel.commonandroidsample.datepicker

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.lionparcel.commonandroid.datepicker.LPDatePickerRange
import com.lionparcel.commonandroid.datepicker.utils.DateUtils
import com.lionparcel.commonandroid.datepicker.utils.toDate
import com.lionparcel.commonandroid.datepicker.utils.toLocaleDate
import com.lionparcel.commonandroidsample.R
import java.lang.ref.WeakReference
import java.time.LocalDate
import java.util.*

class DatePickerSampleActivity : AppCompatActivity() {

    companion object {
        private const val DATE_PICKER_DIALOG = "DATE_PICKER_DIALOG"
    }

    private var startDate: Date? = null
    private var endDate: Date? = null
    private val dialogFragmentMap = mutableMapOf<String, WeakReference<DialogFragment>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_picker_sample)
        findViewById<LinearLayout>(R.id.llDatePicker).setOnClickListener {
            showDatePickerDialog()
        }
        setSelectedDateView(findViewById(R.id.tvDatePick))
    }

    init {
        startDate = run {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DATE, -7)
            DateUtils.dateToString(calendar.time, DateUtils.DATE_NUMBER_FORMAT)
            calendar.time
        }
        endDate = Date()
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

    private fun showDatePickerDialog() {
        val start = startDate ?: return
        showCustomDialog(
            supportFragmentManager,
            DATE_PICKER_DIALOG,
            LPDatePickerRange.newInstance(
                start.toLocaleDate(),
                endDate?.toLocaleDate(),
                this::onChooseButtonDatePicker
            )
        )
        Log.e("e","Error" )
    }

    private fun onChooseButtonDatePicker(startDate: LocalDate, endDate: LocalDate?) {
        dialogFragmentMap[DATE_PICKER_DIALOG]?.get()?.dismissAllowingStateLoss()
        dialogFragmentMap.remove(DATE_PICKER_DIALOG)
        this.startDate = startDate.toDate()
        this.endDate = endDate?.toDate()
        setSelectedDateView(findViewById(R.id.tvDatePick))
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