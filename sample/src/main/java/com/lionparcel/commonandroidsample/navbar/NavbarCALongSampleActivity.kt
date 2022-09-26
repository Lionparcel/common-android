package com.lionparcel.commonandroidsample.navbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.lionparcel.commonandroid.navbar.LPNavbarCA
import com.lionparcel.commonandroid.navbar.utils.CANavbarLongMenu
import com.lionparcel.commonandroidsample.R
import com.lionparcel.commonandroidsample.navbar.utils.NavbarLongViewPagerAdapter

class NavbarCALongSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navbar_calong_sample)
        val viewPager = findViewById<ViewPager>(R.id.vp_navbar_ca_long)
        val btmNavbar = findViewById<LPNavbarCA>(R.id.btm_navbar_ca_long)
        viewPager.adapter = NavbarLongViewPagerAdapter(supportFragmentManager)
        btmNavbar.viewPager = viewPager
        btmNavbar.setOnNavigationItemSelectedListener(btmNavbar::handleNavigation)
        btmNavbar.initSelectedIconMenu()
        btmNavbar.addDotBadge(CANavbarLongMenu.PROFILE.ordinal)
        btmNavbar.addNumberBadge(CANavbarLongMenu.PAYMENT.ordinal, "20")
    }
}