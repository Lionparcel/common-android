package com.lionparcel.commonandroid.navbar.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lionparcel.commonandroid.R

abstract class BaseNavigationBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : BottomNavigationView(context, attrs, defStyleAttrs) {

    companion object {
        const val DEFAULT_PADDING_VERTICAL = 8F
        const val DEFAULT_ICON_SIZE = 24F

    }

    enum class Type(
        val paddingVertical : Float,
        val iconSize : Float,
        val setBackground : Int,
        val iconTint : Int,
        val activeTextAppearance : Int,
        val inactiveTextAppearance : Int,
        val styleableId : IntArray

    ) {
        CA(
            16F,
            24F,
            R.drawable.bg_navbar_ca_shadow,
            R.color.selector_navbar_interpack6_solid,
            R.style.BottomNavigationViewSelectedTextStyle,
            R.style.BottomNavigationViewTextStyle,
            R.styleable.LPNavbarCA
        ),
        DA(
            8F,
            24F,
            R.drawable.bg_navbar_da_ta_shadow,
            R.color.selector_navbar_interpack6_solid,
            R.style.BottomNavigationViewSelectedTextStyle,
            R.style.BottomNavigationViewTextStyle,
            R.styleable.LPNavbarDA
        ),
        TA(
            8F,
            24F,
            R.drawable.bg_navbar_da_ta_shadow,
            R.color.selector_navbar_interpack6_solid,
            R.style.BottomNavigationViewSelectedTextStyle,
            R.style.BottomNavigationViewTextStyle,
            R.styleable.LPNavbarTA
        )
    }

    protected var paddingVertical = dpToPxF(DEFAULT_PADDING_VERTICAL)
    protected var iconSize = dpToPxF(DEFAULT_ICON_SIZE)
    protected var setBackground = R.drawable.bg_navbar_ca_shadow
    protected var iconTint = R.color.selector_navbar_interpack6_solid
    protected var activeTextAppearance = R.style.BottomNavigationViewSelectedTextStyle
    protected var inactiveTextAppearance = R.style.BottomNavigationViewTextStyle

    init {
        if (attrs != null) {
            context.obtainStyledAttributes(attrs, type.styleableId).apply {
                try {
                    paddingVertical = getDimension(dpToPx(type.paddingVertical), paddingVertical)
                    iconSize = getDimension(dpToPx(type.iconSize), iconSize)
                    setBackground = getInt(type.setBackground, setBackground)
                    iconTint = getInt(type.iconTint, iconTint)
                    activeTextAppearance = getInt(type.activeTextAppearance, activeTextAppearance)
                    inactiveTextAppearance = getInt(type.inactiveTextAppearance, inactiveTextAppearance)
                } finally {
                    recycle()
                }
            }
        }
    }

    private fun dpToPxF(dp: Float) : Float {
        return resources.displayMetrics.density * dp
    }

    private fun dpToPx(dp: Float) : Int {
        return (resources.displayMetrics.density * dp).toInt()
    }

    protected fun setIconMenu(bottomNavbar : BaseNavigationBarView, navigationId : Int, iconMenu : Int) {
        val menu = bottomNavbar.menu
        menu.findItem(navigationId).setIcon(iconMenu)
    }

    abstract val type: Type


}