package com.lionparcel.commonandroidsample.header

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ScrollView
import android.widget.TextView
import com.lionparcel.commonandroid.header.LPHeaderCA
import com.lionparcel.commonandroid.snackbartoast.MessageType
import com.lionparcel.commonandroid.snackbartoast.showSnackbarLargeIconWithClose
import com.lionparcel.commonandroidsample.MainActivity
import com.lionparcel.commonandroidsample.R

class HeaderCASampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_header_casample)
        findViewById<LPHeaderCA>(R.id.header_scan_icon_2).setIconButton(
            firstIconImage = R.drawable.ics_f_info_circle_interpack6,
            secondIconImage = R.drawable.ics_f_info_circle_interpack6
        )
        findViewById<LPHeaderCA>(R.id.header_txt_btn_1).setIconButton(
            firstIconImage = R.drawable.ics_f_info_circle_interpack6,
        )
        findViewById<LPHeaderCA>(R.id.header_txt_btn_1).setTextButton(
            txtBtnListener = {findViewById<TextView>(R.id.txt_header_string1).text = findViewById<LPHeaderCA>(R.id.header_txt_btn_1).getTextFromSearch()}
        )
        findViewById<LPHeaderCA>(R.id.header_txt_btn_1).searchTextFromKeyboard(
            searchListener = {findViewById<TextView>(R.id.txt_header_string1).text = findViewById<LPHeaderCA>(R.id.header_txt_btn_1).getTextFromSearch()}
        )
        findViewById<LPHeaderCA>(R.id.header_txt_btn_1).searchArrayText(arrayListOf("January", "February", "March", "April", "May", "June"))
        findViewById<LPHeaderCA>(R.id.header_search_only_1).setHeaderStyle(LPHeaderCA.STYLE.SEARCH_ONLY)
        findViewById<LPHeaderCA>(R.id.header_scan_icon_3).setIconButton(
            firstIconImage = R.drawable.ics_f_info_circle_interpack6,
            secondIconImage = R.drawable.ic_ics_o_check,
            thirdIconImage = R.drawable.ics_o_profile,
            firstIconListener = {
                showSnackbarLargeIconWithClose(findViewById<ScrollView>(R.id.sv_header), "First Icon",messageType = MessageType.SUCCESS)
            },
            secondIconListener = {
                showSnackbarLargeIconWithClose(findViewById<ScrollView>(R.id.sv_header), "Second Icon",messageType = MessageType.SUCCESS)
            },
            thirdIconListener = {
                showSnackbarLargeIconWithClose(findViewById<ScrollView>(R.id.sv_header), "Third Icon",messageType = MessageType.SUCCESS)
            }
        )
    }
}
