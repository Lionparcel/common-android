package com.lionparcel.commonandroid.navbar

import android.content.Context
import android.util.AttributeSet
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.navbar.utils.BaseNavigationBarView
import com.lionparcel.commonandroid.navbar.utils.CANavbarMenu

class LPNavbarCA : BaseNavigationBarView {

    private var bottomNavigationView: BottomNavigationView? = null
    private var style: Int

    var viewPager: ViewPager? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPNavbarCA,
            0,
            0
        ).apply {
            try {
                style = getInt(R.styleable.LPNavbarCA_navbarStyle, 0)
            } finally {
                recycle()
            }
        }
        this.apply {
            background = ContextCompat.getDrawable(context, setBackground)
            setPadding(0, paddingVertical.toInt(), 0, paddingVertical.toInt())
            itemIconSize = iconSize.toInt()
            itemIconTintList = ContextCompat.getColorStateList(context, iconTint)
            itemTextAppearanceActive = activeTextAppearance
            itemTextAppearanceInactive = inactiveTextAppearance
        }
    }

    private fun resetIconMenu() {
        when (style) {
            0 -> {
                this.inflateMenu(R.menu.menu_navbar_ca_default)
                setIconMenu(this, R.id.navHome, R.drawable.ics_o_home)
                setIconMenu(this, R.id.navPayment, R.drawable.ics_o_payment)
                setIconMenu(this, R.id.navTrack, R.drawable.ics_o_box_alt)
                setIconMenu(this, R.id.navAccount, R.drawable.ics_o_profile)
            }
            1 -> {
                this.inflateMenu(R.menu.menu_navbar_ca_long)
                setIconMenu(this, R.id.navHome, R.drawable.ics_o_home)
                setIconMenu(this, R.id.navPayment, R.drawable.ics_o_payment)
                setIconMenu(this, R.id.navHelpdesk, R.drawable.ics_o_helpdesk)
                setIconMenu(this, R.id.navTrack, R.drawable.ics_o_box_alt)
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
        when (this.selectedItemId) {
            R.id.navHome -> setIconMenu(this, R.id.navHome, R.drawable.ics_f_home)
            R.id.navTrack -> setIconMenu(this, R.id.navTrack, R.drawable.ics_f_box_alt)
            R.id.navPayment -> setIconMenu(this, R.id.navPayment, R.drawable.ics_f_payment)
            R.id.navAccount -> setIconMenu(this, R.id.navAccount, R.drawable.ics_f_profile)
        }
    }


    override val type: Type
        get() = Type.CA
}