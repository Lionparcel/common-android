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
        val items = arrayListOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
//        val dataMonth = arrayListOf<Month>()
//        for (i in 0 until items.lastIndex){
//            dataMonth[i]
//        }

        findViewById<LPAutoCompleteForm>(R.id.lpAutoCompleteForm)
            .apply {
                autoCompleteArrayText(items)
            }
//        val dataUser = DataUser(month = (arrayListOf("January", "February", "March")), null)
//        fun data() = DataUser(arrayListOf("January", "February", "March"), arrayListOf(2000, 20001, 2002))
//        findViewById<LPAutoCompleteTextView>(R.id.autoCompleteTxtView).apply {
//            setAdapter(AutoCompleteArrayAdapter(context, arrayListOf<DataUser>()){it.month})
//            threshold = 0
//        }
    }

//    private fun addData() : List<Month>{
//        val items = arrayListOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
//        val dataUser = DataUser("January", 2000)
//        return dataUser
//
//    }

//    fun sortedList(
//        keyword: String,
//        routeList: List<DataUser>) : List<DataUser>{
//        val resList = mutableListOf<DataUser>()
//        val monthList = mutableListOf<DataUser>()
//        val districtContainKeywordList = mutableListOf<DataUser>()
//        routeList.forEach {
//            when {
//                it.month.startsWith(keyword, true) -> monthList.add(it)
//                else -> districtContainKeywordList.add(it)
//            }
//        }
//        resList.addAll(monthList)
//        resList.addAll(districtContainKeywordList)
//        return resList
//    }


}

data class Month(
    val a : String,
    val b : String,
    val c : String,
    val d : String,
    val e : String,
    val f : String,
    val g : String,
    val h : String,
    val i : String,
    val j : String,
    val k : String,
    val l : String
)