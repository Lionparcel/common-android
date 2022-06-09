package com.lionparcel.commonandroidsample.form

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.lionparcel.commonandroid.form.LPAutoCompleteForm
import com.lionparcel.commonandroidsample.R

class AutoCompleteFormSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_complete_form_sample)
        val months = arrayListOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
        val years = arrayListOf(2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008 , 2009, 2010)

        findViewById<LPAutoCompleteForm>(R.id.lpAutoCompleteForm)
            .apply {
                autoCompleteArrayText(years)
            }

        findViewById<Button>(R.id.btnAddAutoCompleteText).setOnClickListener {
            findViewById<TextView>(R.id.txtPreview).text = findViewById<LPAutoCompleteForm>(R.id.lpAutoCompleteForm).getAutoCompleteText()
        }

        findViewById<LPAutoCompleteForm>(R.id.lpAutoCompleteForm1)
            .apply {
                autoCompleteArrayText(months)
            }

        findViewById<Button>(R.id.btnDisableAutoCompleteText).setOnClickListener {
            findViewById<LPAutoCompleteForm>(R.id.lpAutoCompleteForm1).setEnabledEditText(false)
        }

        findViewById<LPAutoCompleteForm>(R.id.lpAutoCompleteForm2)
            .apply {
                autoCompleteArrayText(months)
            }

        findViewById<Button>(R.id.btnCheckErrorAutoCompleteText).setOnClickListener {
            findViewById<LPAutoCompleteForm>(R.id.lpAutoCompleteForm2).changeStateViewTextViewError(true)
        }
    }
}