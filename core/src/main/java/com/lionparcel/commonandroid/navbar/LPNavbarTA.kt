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
import com.lionparcel.commonandroid.badge.LPBadgeNumber
import com.lionparcel.commonandroid.navbar.utils.BaseNavigationBarView
import com.lionparcel.commonandroid.navbar.utils.TANavbarMenu

class LPNavbarTA @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : BaseNavigationBarView(context, attrs, defStyleAttrs) {

    private val dotBadge: View by lazy {
        dotBadge(context, this)
    }

    private val numberBadge: View by lazy {
        numberBadge(context, this)
    }

    var viewPager: ViewPager? = null

    init {
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
        this.inflateMenu(R.menu.menu_navbar_ta_default)
    }

    private fun resetIconMenu() {
        setIconMenu(this, R.id.navHome, R.drawable.ics_o_home)
        setIconMenu(this, R.id.navHistory, R.drawable.ics_o_notebook)
        setIconMenu(this, R.id.navSync, R.drawable.ics_o_sync)
        setIconMenu(this, R.id.navAccount, R.drawable.ics_o_profile)
    }

    private fun goToHomePage(menuItem: MenuItem): Boolean {
        menuItem.setIcon(R.drawable.ics_f_home)
        viewPager!!.setCurrentItem(TANavbarMenu.HOME.ordinal, true)
        return true
    }

    private fun goToHistoryPage(menuItem: MenuItem): Boolean {
        menuItem.setIcon(R.drawable.ics_f_notebook)
        viewPager!!.setCurrentItem(TANavbarMenu.HISTORY.ordinal, true)
        return true

    }

    private fun goToSyncPage(menuItem: MenuItem): Boolean {
        menuItem.setIcon(R.drawable.ics_f_sync)
        viewPager!!.setCurrentItem(TANavbarMenu.SYNC.ordinal, true)
        return true

    }

    private fun goToProfilePage(menuItem: MenuItem): Boolean {
        menuItem.setIcon(R.drawable.ics_f_profile)
        viewPager!!.setCurrentItem(TANavbarMenu.PROFILE.ordinal, true)
        return true

    }

    fun handleNavigation(item: MenuItem): Boolean {
        resetIconMenu()
        return when (item.itemId) {
            TANavbarMenu.HOME.menuRes -> goToHomePage(item)
            TANavbarMenu.HISTORY.menuRes -> goToHistoryPage(item)
            TANavbarMenu.SYNC.menuRes -> goToSyncPage(item)
            TANavbarMenu.PROFILE.menuRes -> goToProfilePage(item)
            else -> false
        }
    }

    fun initSelectedIconMenu() {
        when (this.selectedItemId) {
            R.id.navHome -> setIconMenu(this, R.id.navHome, R.drawable.ics_f_home)
            R.id.navHistory -> setIconMenu(this, R.id.navHistory, R.drawable.ics_f_notebook)
            R.id.navSync -> setIconMenu(this, R.id.navSync, R.drawable.ics_f_sync)
            R.id.navAccount -> setIconMenu(this, R.id.navAccount, R.drawable.ics_f_profile)
        }
    }

    fun addDotBadge(menuItemIndex: Int) {
        val menuBottomNavBar = this.getChildAt(0) as BottomNavigationMenuView
        val menuItem = menuBottomNavBar.getChildAt(menuItemIndex) as BottomNavigationItemView
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
        val menuItem = menuBottomNavBar.getChildAt(menuItemIndex) as BottomNavigationItemView
        val screenWidth = Resources.getSystem().displayMetrics.widthPixels
        val menuItemPadding = ((screenWidth / 4) - 24.toDp()) / 2
        val badgeMargin = 14
        val badgeMarginStart = menuItemPadding + badgeMargin.toDp()
        if (numberBadge.parent == null){
            menuItem.addView(numberBadge)
        }
        val navbarRedBadgeNumber = findViewById<LPBadgeNumber>(R.id.navbar_red_badge_number)
        navbarRedBadgeNumber.updateLayoutParams<LayoutParams> {
            marginStart = badgeMarginStart
        }
        navbarRedBadgeNumber.setNumber(number)
    }

    fun removeNumberBadge(menuItemIndex: Int) {
        val menuBottomNavBar = this.getChildAt(0) as BottomNavigationMenuView
        val menuItem =  menuBottomNavBar.getChildAt(menuItemIndex) as BottomNavigationItemView
        if (numberBadge.parent != null){
            menuItem.removeView(numberBadge)
        }
    }

    override val type: Type
        get() = Type.TA
}