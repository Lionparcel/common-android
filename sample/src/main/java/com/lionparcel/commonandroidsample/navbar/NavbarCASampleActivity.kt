package com.lionparcel.commonandroidsample.navbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.lionparcel.commonandroid.navbar.LPNavbarCA
import com.lionparcel.commonandroid.navbar.utils.CANavbarLongMenu
import com.lionparcel.commonandroid.navbar.utils.CANavbarMenu
import com.lionparcel.commonandroidsample.R
import com.lionparcel.commonandroidsample.navbar.utils.NavbarViewPagerAdapter

class NavbarCASampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navbar_casample)
        val viewPager = findViewById<ViewPager>(R.id.vp_navbar_ca)
        val btmNavbar = findViewById<LPNavbarCA>(R.id.btm_navbar_ca)
        viewPager.adapter = NavbarViewPagerAdapter(supportFragmentManager)
        btmNavbar.viewPager = viewPager
        btmNavbar.setOnNavigationItemSelectedListener(btmNavbar::handleNavigation)
        btmNavbar.initSelectedIconMenu()
        btmNavbar.addDotBadge(CANavbarMenu.PROFILE.ordinal)
        btmNavbar.addNumberBadge(CANavbarMenu.PAYMENT.ordinal, "20")
    }
}