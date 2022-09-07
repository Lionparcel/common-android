package com.lionparcel.commonandroid.navbar

import android.content.Context
import android.util.AttributeSet
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.navbar.utils.BaseNavigationBarView
import com.lionparcel.commonandroid.navbar.utils.CANavbarMenu

class LPNavbarDA @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : BaseNavigationBarView(context, attrs, defStyleAttrs) {

    var viewPager: ViewPager? = null

    var idHome : Int? = null

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
        this.inflateMenu(R.menu.menu_navbar_ca_default)
    }

    private fun resetIconMenu() {
        setIconMenu(this, R.id.navHome, R.drawable.ics_o_home)
        setIconMenu(this, R.id.navPayment, R.drawable.ics_o_payment)
        setIconMenu(this, R.id.navTrack, R.drawable.ics_o_box_alt_1)
        setIconMenu(this, R.id.navAccount, R.drawable.ics_o_profile)
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
        return when (item.itemId) {
            CANavbarMenu.HOME.menuRes -> goToHomePage(item)
            CANavbarMenu.TRACK.menuRes -> goToTrackPage(item)
            CANavbarMenu.PAYMENT.menuRes -> goToPaymentPage(item)
            CANavbarMenu.PROFILE.menuRes -> goToProfilePage(item)
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
        get() = Type.DA
}