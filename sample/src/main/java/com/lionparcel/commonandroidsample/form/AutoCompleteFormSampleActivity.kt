package com.lionparcel.commonandroidsample.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import com.lionparcel.commonandroid.form.LPAutoCompleteForm
import com.lionparcel.commonandroid.form.LPAutoCompleteTextView
import com.lionparcel.commonandroid.form.utils.AutoCompleteArrayAdapter
import com.lionparcel.commonandroidsample.R
import java.lang.reflect.TypeVariable

class AutoCompleteFormSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_complete_form_sample)
        val month = arrayListOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
        findViewById<LPAutoCompleteForm>(R.id.lpAutoCompleteForm).autoCompleteArrayTextHardCoded(month)
//            .apply {
//                autoCompleteArrayText(arrayListOf<DataUser>(), DataUser().month)
//
//
//            }
//        val dataUser = DataUser(month = (arrayListOf("January", "February", "March")), null)
//        fun data() = DataUser(arrayListOf("January", "February", "March"), arrayListOf(2000, 20001, 2002))
        findViewById<LPAutoCompleteTextView>(R.id.autoCompleteTxtView).apply {
            setAdapter(AutoCompleteArrayAdapter(context, arrayListOf<DataUser>()){it.month})
            threshold = 0
        }
    }

    fun sortedList(
        keyword: String,
        routeList: List<DataUser>) : List<DataUser>{
        val resList = mutableListOf<DataUser>()
        val monthList = mutableListOf<DataUser>()
        val districtContainKeywordList = mutableListOf<DataUser>()
        routeList.forEach {
            when {
                it.month.startsWith(keyword, true) -> monthList.add(it)
                else -> districtContainKeywordList.add(it)
            }
        }
        resList.addAll(monthList)
        resList.addAll(districtContainKeywordList)
        return resList
    }


}

data class DataUser(
    val month : String = "January",
    val year : Int = 2000
)