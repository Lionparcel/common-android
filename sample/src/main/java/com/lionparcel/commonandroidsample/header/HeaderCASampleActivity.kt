package com.lionparcel.commonandroidsample.header

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.lionparcel.commonandroid.header.LPHeaderCA
import com.lionparcel.commonandroidsample.MainActivity
import com.lionparcel.commonandroidsample.R

class HeaderCASampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_header_casample)

        findViewById<LPHeaderCA>(R.id.header_txt_btn_1).setIconButton(
            firstIconImage = R.drawable.ics_o_profile,
            secondIconImage = R.drawable.ic_ics_o_close
        )
        findViewById<LPHeaderCA>(R.id.header_txt_btn_1).setTextButton(
            txtBtnListener = {findViewById<TextView>(R.id.txt_header_string1).text = findViewById<LPHeaderCA>(R.id.header_txt_btn_1).getTextFromSearch()}
        )
        findViewById<LPHeaderCA>(R.id.header_txt_btn_1).searchTextFromKeyboard(
            searchListener = {findViewById<TextView>(R.id.txt_header_string1).text = findViewById<LPHeaderCA>(R.id.header_txt_btn_1).getTextFromSearch()}
        )
        findViewById<LPHeaderCA>(R.id.header_txt_btn_1).searchArrayText(arrayListOf("January", "February", "March", "April", "May", "June"))
    }
}
