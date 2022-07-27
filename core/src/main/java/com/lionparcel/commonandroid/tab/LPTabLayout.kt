package com.lionparcel.commonandroid.tab

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.tabs.TabLayout
import com.lionparcel.commonandroid.R

class LPTabLayout : TabLayout {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private fun changeTextAppearance(selectedIndex : Int) {
        (0 until this.tabCount).forEach { index ->
            val tabItem = this.getTabAt(index)
            val customView = tabItem?.customView
            val tabStyleRes = if (index == selectedIndex) R.style.TextAppearance_App_Widget_Tab_Active else R.style.TextAppearance_App_Widget_Tab_Inactive

        }
    }
}