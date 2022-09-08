package com.lionparcel.commonandroid.navbar

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.view.updateLayoutParams
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.navbar.utils.BaseNavigationBarView
import com.lionparcel.commonandroid.navbar.utils.CANavbarMenu
import kotlinx.android.synthetic.main.lp_layout_navbar_badge_number.view.*

class LPNavbarCA @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : BaseNavigationBarView(context, attrs, defStyleAttrs) {

    private var style: Int

    var viewPager: ViewPager? = null

    init {
        context.obtainStyledAttributes(
            attrs,
            R.styleable.LPNavbarCA).apply {
            try {
                style = getInt(R.styleable.LPNavbarCA_navbarStyle, 0)
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
            0 -> this.inflateMenu(R.menu.menu_navbar_ca_default)
            1 -> this.inflateMenu(R.menu.menu_navbar_ca_long)
        }
    }

    private fun resetIconMenu() {
        when (style) {
            0 -> {
                setIconMenu(this, R.id.navHome, R.drawable.ics_o_home)
                setIconMenu(this, R.id.navPayment, R.drawable.ics_o_payment)
                setIconMenu(this, R.id.navTrack, R.drawable.ics_o_box_alt_1)
                setIconMenu(this, R.id.navAccount, R.drawable.ics_o_profile)
            }
            1 -> {
                setIconMenu(this, R.id.navHome, R.drawable.ics_o_home)
                setIconMenu(this, R.id.navPayment, R.drawable.ics_o_payment)
                setIconMenu(this, R.id.navHelpdesk, R.drawable.ics_o_helpdesk)
                setIconMenu(this, R.id.navTrack, R.drawable.ics_o_box_alt_1)
                setIconMenu(this, R.id.navAccount, R.drawable.ics_o_profile)
            }
        }
    }

    private fun goToHomePage(menuItem: MenuItem): Boolean {
        menuItem.setIcon(R.drawable.ics_f_home)
        viewPager!!.setCurrentItem(CANavbarMenu.HOME.ordinal, true)
        return true
    }

    private fun goToPaymentPage(menuItem: MenuItem): Boolean {
        menuItem.setIcon(R.drawable.ics_f_payment)
        viewPager!!.setCurrentItem(CANavbarMenu.PAYMENT.ordinal, true)
        return true

    }

    private fun goToHelpdeskPage(menuItem: MenuItem): Boolean {
        menuItem.setIcon(R.drawable.ics_f_helpdesk)
        viewPager!!.setCurrentItem(CANavbarMenu.HELPDESK.ordinal, true)
        return true

    }

    private fun goToTrackPage(menuItem: MenuItem): Boolean {
        menuItem.setIcon(R.drawable.ics_f_box_alt)
        viewPager!!.setCurrentItem(CANavbarMenu.TRACK.ordinal, true)
        return true

    }

    private fun goToProfilePage(menuItem: MenuItem): Boolean {
        menuItem.setIcon(R.drawable.ics_f_profile)
        viewPager!!.setCurrentItem(CANavbarMenu.PROFILE.ordinal, true)
        return true

    }

    fun handleNavigation(item: MenuItem): Boolean {
        resetIconMenu()
        return when (style) {
            0 -> {
                when (item.itemId) {
                    CANavbarMenu.HOME.menuRes -> goToHomePage(item)
                    CANavbarMenu.TRACK.menuRes -> goToTrackPage(item)
                    CANavbarMenu.PAYMENT.menuRes -> goToPaymentPage(item)
                    CANavbarMenu.PROFILE.menuRes -> goToProfilePage(item)
                    else -> false
                }
            }
            1 -> {
                when (item.itemId) {
                    CANavbarMenu.HOME.menuRes -> goToHomePage(item)
                    CANavbarMenu.TRACK.menuRes -> goToTrackPage(item)
                    CANavbarMenu.PAYMENT.menuRes -> goToPaymentPage(item)
                    CANavbarMenu.PROFILE.menuRes -> goToProfilePage(item)
                    CANavbarMenu.HELPDESK.menuRes -> goToHelpdeskPage(item)
                    else -> false
                }
            }
            else -> false
        }
    }

    fun initSelectedIconMenu() {
        when (style) {
            0 ->
                when (this.selectedItemId) {
                    R.id.navHome -> setIconMenu(this, R.id.navHome, R.drawable.ics_f_home)
                    R.id.navTrack -> setIconMenu(this, R.id.navTrack, R.drawable.ics_f_box_alt)
                    R.id.navPayment -> setIconMenu(this, R.id.navPayment, R.drawable.ics_f_payment)
                    R.id.navAccount -> setIconMenu(this, R.id.navAccount, R.drawable.ics_f_profile)
                }
            1 -> when (this.selectedItemId) {
                R.id.navHome -> setIconMenu(this, R.id.navHome, R.drawable.ics_f_home)
                R.id.navTrack -> setIconMenu(this, R.id.navTrack, R.drawable.ics_f_box_alt)
                R.id.navHelpdesk -> setIconMenu(this, R.id.navHelpdesk, R.drawable.ics_f_helpdesk)
                R.id.navPayment -> setIconMenu(this, R.id.navPayment, R.drawable.ics_f_payment)
                R.id.navAccount -> setIconMenu(this, R.id.navAccount, R.drawable.ics_f_profile)
            }
        }
    }

    fun addDotBadge(menuItemIndex : Int) {
        val menuBottomNavBar = this.getChildAt(0) as BottomNavigationMenuView
        val menuItem =  menuBottomNavBar.getChildAt(menuItemIndex) as BottomNavigationItemView
        menuItem.addView(dotBadge(context, this))
    }

    fun addNumberBadge(menuItemIndex: Int, number: String) {
        val menuBottomNavBar = this.getChildAt(0) as BottomNavigationMenuView
        val menuItem =  menuBottomNavBar.getChildAt(menuItemIndex) as BottomNavigationItemView
        val screenWidth = Resources.getSystem().displayMetrics.widthPixels
        val menuItemPadding = ((screenWidth/4) - 24.toDp()) /2
        val badgeMarginStart = menuItemPadding + 14.toDp()
        menuItem.addView(numberBadge(context, this))
        navbar_red_badge_number.updateLayoutParams<FrameLayout.LayoutParams> {
            marginStart = badgeMarginStart
        }
        navbar_red_badge_number.setNumber(number)
    }


    override val type: Type
        get() = Type.CA
}