package com.lionparcel.commonandroid.navbar.utils

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lionparcel.commonandroid.R

class BaseNavigationBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : BottomNavigationView(context, attrs, defStyleAttrs) {

    enum class Type(
        val paddingVertical : Float,
        val background : Int,
        val ic

    ) {
        CA(
            16F,

            24F,
        )
    }

}