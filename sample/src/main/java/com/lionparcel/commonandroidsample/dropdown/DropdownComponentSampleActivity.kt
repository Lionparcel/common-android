package com.lionparcel.commonandroidsample.dropdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lionparcel.commonandroid.dropdown.LPDropdown
import com.lionparcel.commonandroid.dropdown.LPDropdownDA
import com.lionparcel.commonandroid.dropdown.LPDropdownOutlined
import com.lionparcel.commonandroid.dropdown.utils.DropdownData
import com.lionparcel.commonandroidsample.R

class DropdownComponentSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dropdown_component_sample)
        val months = arrayListOf("January", "February", "March", "April", "May")
        val data = arrayListOf(DropdownData("January"), DropdownData("February"), DropdownData("March"), DropdownData("April", true), DropdownData("May", true))
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
        findViewById<LPDropdownDA>(R.id.dropDownDA).setData(data)
    }
}