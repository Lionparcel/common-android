package com.lionparcel.commonandroidsample.dropdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lionparcel.commonandroid.dropdown.LPDropdown
import com.lionparcel.commonandroidsample.R

class DropdownComponentSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dropdown_component_sample)
        val months = arrayListOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
        findViewById<LPDropdown>(R.id.dropDown1).setData(months)
    }
}