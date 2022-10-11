package com.lionparcel.commonandroidsample.navbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.lionparcel.commonandroid.navbar.LPNavbarDA
import com.lionparcel.commonandroid.navbar.utils.DANavbarMenu
import com.lionparcel.commonandroidsample.R
import com.lionparcel.commonandroidsample.navbar.utils.NavbarViewPagerAdapter

class NavbarDASampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navbar_dasample)
        val viewPager = findViewById<ViewPager>(R.id.vp_navbar_da)
        val btmNavbar = findViewById<LPNavbarDA>(R.id.btm_navbar_da)
        viewPager.adapter = NavbarViewPagerAdapter(supportFragmentManager)
        btmNavbar.viewPager = viewPager
        btmNavbar.setOnNavigationItemSelectedListener(btmNavbar::handleNavigation)
        btmNavbar.initSelectedIconMenu()
        btmNavbar.addNumberBadge(DANavbarMenu.HISTORY.ordinal, "99+")
        btmNavbar.addDotBadge(DANavbarMenu.PROFILE.ordinal)
    }
}