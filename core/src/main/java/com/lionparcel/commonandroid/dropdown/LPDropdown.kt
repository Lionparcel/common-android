package com.lionparcel.commonandroid.dropdown

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.AdapterView
import androidx.constraintlayout.widget.ConstraintLayout
import com.lionparcel.commonandroid.R

class LPDropdown @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var onItemSelectedListener: AdapterView.OnItemSelectedListener? = null

    private val mutableList = mutableListOf<Any?>()

    private var currentPosition: Int = -1

    init {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_layout_dropdown, this, true)
    }

    fun <T> setData(data: List<T>) {
        mutableList.clear()
        mutableList.addAll(data)
        mutableList.add(data.size, null)
    }
}