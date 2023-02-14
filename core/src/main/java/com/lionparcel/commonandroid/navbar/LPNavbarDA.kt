package com.lionparcel.commonandroid.navbar

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.view.updateLayoutParams
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.navbar.utils.*
import kotlinx.android.synthetic.main.lp_layout_navbar_badge_number.view.*

class LPNavbarDA @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : BaseNavigationBarView(context, attrs, defStyleAttrs) {

    private var style: Int

    private val dotBadge: View by lazy {
        dotBadge(context, this)
    }

    private val numberBadge: View by lazy {
        numberBadge(context, this)
    }

    var viewPager: ViewPager? = null

    init {
        context.obtainStyledAttributes(
            attrs,
            R.styleable.LPNavbarDA).apply {
            try {
                style = getInt(R.styleable.LPNavbarDA_navbarStyle, 0)
            } finally {
                recycle()
            }
        }
        background = ContextCompat.getDrawable(context, setBackground)
        setPadding(
            0,
            paddingVertical.toInt(),
            0,
            paddingVertical.toInt()
        )
        itemIconSize = iconSize.toInt()
        itemIconTintList = ContextCompat.getColorStateList(context, iconTint)
        itemTextAppearanceActive = activeTextAppearance
        itemTextAppearanceInactive = inactiveTextAppearance
        when (style) {
            0 -> this.inflateMenu(R.menu.menu_navbar_da_default)
            1 -> this.inflateMenu(R.menu.menu_navbar_da_long)
        }

    }

    private fun resetIconMenu() {
        when (style) {
            0 -> {
                setIconMenu(this, R.id.navHome, R.drawable.ics_o_home)
                setIconMenu(this, R.id.navHistory, R.drawable.ics_o_notebook)
                setIconMenu(this, R.id.navSync, R.drawable.ics_o_sync)
                setIconMenu(this, R.id.navAccount, R.drawable.ics_o_profile)
            }
            1 -> {
                setIconMenu(this, R.id.navHome, R.drawable.ics_o_home)
                setIconMenu(this, R.id.navHistory, R.drawable.ics_o_notebook)
                setIconMenu(this, R.id.navSync, R.drawable.ics_o_sync)
                setIconMenu(this, R.id.navEarning, R.drawable.ics_o_payment)
                setIconMenu(this, R.id.navAccount, R.drawable.ics_o_profile)
            }
        }
    }

    private fun goToHomePage(menuItem: MenuItem): Boolean {
        menuItem.setIcon(R.drawable.ics_f_home)
        when (style) {
            0 -> viewPager!!.setCurrentItem(DANavbarMenu.HOME.ordinal, true)
            1 -> viewPager!!.setCurrentItem(DANavbarLongMenu.HOME.ordinal, true)
        }
        return true
    }

    private fun goToHistoryPage(menuItem: MenuItem): Boolean {
        menuItem.setIcon(R.drawable.ics_f_notebook)
        when (style) {
            0 -> viewPager!!.setCurrentItem(DANavbarMenu.HISTORY.ordinal, true)
            1 -> viewPager!!.setCurrentItem(DANavbarLongMenu.HISTORY.ordinal, true)
        }
        return true

    }

    private fun goToSyncPage(menuItem: MenuItem): Boolean {
        menuItem.setIcon(R.drawable.ics_f_sync)
        when (style) {
            0 -> viewPager!!.setCurrentItem(DANavbarMenu.SYNC.ordinal, true)
            1 -> viewPager!!.setCurrentItem(DANavbarLongMenu.SYNC.ordinal, true)
        }
        return true

    }

    private fun goToEarningPage(menuItem: MenuItem): Boolean {
        menuItem.setIcon(R.drawable.ics_f_payment)
        viewPager!!.setCurrentItem(DANavbarLongMenu.EARNING.ordinal, true)
        return true

    }

    private fun goToProfilePage(menuItem: MenuItem): Boolean {
        menuItem.setIcon(R.drawable.ics_f_profile)
        when (style) {
            0 -> viewPager!!.setCurrentItem(DANavbarMenu.PROFILE.ordinal, true)
            1 -> viewPager!!.setCurrentItem(DANavbarLongMenu.PROFILE.ordinal, true)
        }
        return true

    }

    fun handleNavigation(item: MenuItem): Boolean {
        resetIconMenu()
        return when (style) {
            0 -> {
                when (item.itemId) {
                    DANavbarMenu.HOME.menuRes -> goToHomePage(item)
                    DANavbarMenu.HISTORY.menuRes -> goToHistoryPage(item)
                    DANavbarMenu.SYNC.menuRes -> goToSyncPage(item)
                    DANavbarMenu.PROFILE.menuRes -> goToProfilePage(item)
                    else -> false
                }
            }
            1 -> {
                when (item.itemId) {
                    DANavbarLongMenu.HOME.menuRes -> goToHomePage(item)
                    DANavbarLongMenu.HISTORY.menuRes -> goToHistoryPage(item)
                    DANavbarLongMenu.SYNC.menuRes -> goToSyncPage(item)
                    DANavbarLongMenu.EARNING.menuRes -> goToEarningPage(item)
                    DANavbarLongMenu.PROFILE.menuRes -> goToProfilePage(item)
                    else -> false
                }
            }
            else -> false
        }
    }

    fun initSelectedIconMenu() {
        when (style) {
            0 -> {
                when (this.selectedItemId) {
                    R.id.navHome -> setIconMenu(this, R.id.navHome, R.drawable.ics_f_home)
                    R.id.navHistory -> setIconMenu(this, R.id.navHistory, R.drawable.ics_f_notebook)
                    R.id.navSync -> setIconMenu(this, R.id.navSync, R.drawable.ics_f_sync)
                    R.id.navAccount -> setIconMenu(this, R.id.navAccount, R.drawable.ics_f_profile)
                }
            }
            1 -> {
                when (this.selectedItemId) {
                    R.id.navHome -> setIconMenu(this, R.id.navHome, R.drawable.ics_f_home)
                    R.id.navHistory -> setIconMenu(this, R.id.navHistory, R.drawable.ics_f_notebook)
                    R.id.navSync -> setIconMenu(this, R.id.navSync, R.drawable.ics_f_sync)
                    R.id.navEarning -> setIconMenu(this, R.id.navEarning, R.drawable.ics_f_payment)
                    R.id.navAccount -> setIconMenu(this, R.id.navAccount, R.drawable.ics_f_profile)
                }
            }
        }
    }

    fun addDotBadge(menuItemIndex : Int) {
        val menuBottomNavBar = this.getChildAt(0) as BottomNavigationMenuView
        val menuItem =  menuBottomNavBar.getChildAt(menuItemIndex) as BottomNavigationItemView
        if (dotBadge.parent == null){
            menuItem.addView(dotBadge)
        }
    }

    fun removeDotBadge(menuItemIndex: Int) {
        val menuBottomNavBar = this.getChildAt(0) as BottomNavigationMenuView
        val menuItem =  menuBottomNavBar.getChildAt(menuItemIndex) as BottomNavigationItemView
        if (dotBadge.parent != null){
            menuItem.removeView(dotBadge)
        }
    }

    fun addNumberBadge(menuItemIndex: Int, number: String) {
        val menuBottomNavBar = this.getChildAt(0) as BottomNavigationMenuView
        val menuItem =  menuBottomNavBar.getChildAt(menuItemIndex) as BottomNavigationItemView
        val screenWidth = Resources.getSystem().displayMetrics.widthPixels
        val menuItemPadding = ((screenWidth/4) - 24.toDp()) /2
        val badgeMargin = when (style) {
            0 -> 14
            1 -> 4
            else -> 14
        }
        val badgeMarginStart = menuItemPadding + badgeMargin.toDp()
        if (numberBadge.parent == null){
            menuItem.addView(numberBadge)
        }
        navbar_red_badge_number.updateLayoutParams<FrameLayout.LayoutParams> {
            marginStart = badgeMarginStart
        }
        navbar_red_badge_number.setNumber(number)
    }

    fun removeNumberBadge(menuItemIndex: Int) {
        val menuBottomNavBar = this.getChildAt(0) as BottomNavigationMenuView
        val menuItem =  menuBottomNavBar.getChildAt(menuItemIndex) as BottomNavigationItemView
        if (numberBadge.parent != null){
            menuItem.removeView(numberBadge)
        }
    }

    override val type: Type
        get() = Type.DA
}