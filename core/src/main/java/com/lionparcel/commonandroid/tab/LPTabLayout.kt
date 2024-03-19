package com.lionparcel.commonandroid.tab

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.material.tabs.TabLayout
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.tab.utils.BasePagerAdapter
import com.lionparcel.commonandroid.tab.utils.LPRedBadge

@Suppress("DEPRECATION")
class LPTabLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : TabLayout(context, attrs) {

    init {
        val scale = resources.displayMetrics.density
        setSelectedTabIndicatorHeight((scale * 4).toInt())
        tabRippleColor = ContextCompat.getColorStateList(context, R.color.transparent)
        background = ContextCompat.getDrawable(context, R.color.transparent)
        setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.interpack7))
        setSelectedTabIndicator(ContextCompat.getDrawable(context, R.drawable.bg_tab_indicator_interpack7))
    }

    fun setTabViewMargin(adapter: BasePagerAdapter) {
        (0 until adapter.count).forEach { index ->
            val tabItem = this.getTabAt(index)
            tabItem?.customView = adapter.getTabView(index, this)
            (tabItem?.customView?.parent as? LinearLayout?)?.run {
                layoutParams =
                    (layoutParams as LinearLayout.LayoutParams).apply {
                        rightMargin =
                            if (index == adapter.count - 1) 0 else (resources.displayMetrics.density * 20F).toInt()
                        minimumWidth = (resources.displayMetrics.density * 98).toInt()
                    }
            }
        }
    }

    fun setLayout(
        context: Context,
        root: ViewGroup,
        position: Int,
        adapter: BasePagerAdapter,
        page: Int,
        showRedBadge: () -> Boolean?
    ) : View {
        val tabView =
            LayoutInflater.from(context).inflate(R.layout.lp_layout_tab_layout, root, false)
        tabView.findViewById<TextView>(R.id.tv_tab_title).text = adapter.getPageTitle(position)
        tabView.findViewById<LPRedBadge>(R.id.tab_red_badge).isVisible = position == page && showRedBadge.invoke() == true
        return tabView
    }

    fun changeTextAppearance(selectedIndex: Int) {
        (0 until this.tabCount).forEach { index ->
            val tabItem = this.getTabAt(index)
            val customView = tabItem?.customView
            val tabStyleRes =
                if (index == selectedIndex) R.style.TextAppearance_App_Widget_Tab_Active else R.style.TextAppearance_App_Widget_Tab_Inactive
            customView?.findViewById<TextView>(R.id.tv_tab_title)
                ?.setTextAppearance(context, tabStyleRes)
        }
    }
}
