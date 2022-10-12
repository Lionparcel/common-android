package com.lionparcel.commonandroidsample.dropdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lionparcel.commonandroid.button.LPButton
import com.lionparcel.commonandroid.dropdown.LPDropdown
import com.lionparcel.commonandroid.dropdown.LPDropdownOutlined
import com.lionparcel.commonandroid.dropdown.LPWheelPicker
import com.lionparcel.commonandroid.modal.showCustomModal
import com.lionparcel.commonandroidsample.R

class DropdownComponentSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dropdown_component_sample)
        val months = arrayListOf("January", "February", "March", "April", "May")
        findViewById<LPDropdown>(R.id.dropDown1).setData(months)
        findViewById<LPDropdown>(R.id.dropDown2).apply {
            setData(months)
            getInputLayout().error = "This not an error text"
        }
        findViewById<LPDropdownOutlined>(R.id.dropDown3).setData(months)
        findViewById<LPDropdownOutlined>(R.id.dropDown4).apply {
            setData(months)
            getInputLayout().error = "This not an error text"
        }
        findViewById<LPButton>(R.id.btnWheelPickerSample).setOnClickListener {
            showCustomModal(
                supportFragmentManager,
                "WHEEL_PICKER",
                LPWheelPicker.newInstance(
                    "Pick One!",
                    "Get this!",
                    months
                ) {

                }
            )
        }
    }
}