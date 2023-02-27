package com.lionparcel.commonandroid.navbar.utils

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lionparcel.commonandroid.R

@Suppress("LeakingThis")
abstract class BaseNavigationBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : BottomNavigationView(context, attrs, defStyleAttrs) {

    enum class Type(
        val paddingVertical : Float,
        val iconSize : Float,
        val setBackground : Int,
        val iconTint : Int,
        val activeTextAppearance : Int,
        val inactiveTextAppearance : Int

    ) {
        CA(
            16F,
            24F,
            R.drawable.bg_navbar_ca_shadow,
            R.color.selector_navbar_interpack7_solid,
            R.style.BottomNavigationViewSelectedTextStyle,
            R.style.BottomNavigationViewTextStyle
        ),
        DA(
            8F,
            24F,
            R.drawable.bg_navbar_da_ta_shadow,
            R.color.selector_navbar_interpack7_solid,
            R.style.BottomNavigationViewSelectedTextStyle,
            R.style.BottomNavigationViewTextStyle
        ),
        TA(
            8F,
            24F,
            R.drawable.bg_navbar_da_ta_shadow,
            R.color.selector_navbar_interpack7_solid,
            R.style.BottomNavigationViewSelectedTextStyle,
            R.style.BottomNavigationViewTextStyle
        )
    }

    protected var paddingVertical = dpToPxF(type.paddingVertical)
    protected var iconSize = dpToPxF(type.iconSize)
    protected var setBackground = type.setBackground
    protected var iconTint = type.iconTint
    protected var activeTextAppearance = type.activeTextAppearance
    protected var inactiveTextAppearance = type.inactiveTextAppearance

    private fun dpToPxF(dp: Float) : Float {
        return resources.displayMetrics.density * dp
    }

    private fun dpToPx(dp: Float) : Int {
        return (resources.displayMetrics.density * dp).toInt()
    }

    protected fun Int.toDp(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

    protected fun setIconMenu(bottomNavbar : BaseNavigationBarView, navigationId : Int, iconMenu : Int) {
        val menu = bottomNavbar.menu
        menu.findItem(navigationId).setIcon(iconMenu)
    }

    protected fun dotBadge(context: Context, view: ViewGroup) : View {
        return LayoutInflater.from(context).inflate(R.layout.lp_layout_navbar_badge_dot, view, false)
    }

    protected fun numberBadge(context: Context, view: ViewGroup) : View {
        return LayoutInflater.from(context).inflate(R.layout.lp_layout_navbar_badge_number, view, false)
    }

    abstract val type: Type


}