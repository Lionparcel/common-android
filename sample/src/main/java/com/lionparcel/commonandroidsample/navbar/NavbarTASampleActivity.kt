package com.lionparcel.commonandroidsample.navbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.lionparcel.commonandroid.navbar.LPNavbarTA
import com.lionparcel.commonandroid.navbar.utils.TANavbarMenu
import com.lionparcel.commonandroidsample.R
import com.lionparcel.commonandroidsample.navbar.utils.NavbarViewPagerAdapter

class NavbarTASampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navbar_tasample)
        val viewPager = findViewById<ViewPager>(R.id.vp_navbar_ta)
        val btmNavbar = findViewById<LPNavbarTA>(R.id.btm_navbar_ta)
        viewPager.adapter = NavbarViewPagerAdapter(supportFragmentManager)
        btmNavbar.viewPager = viewPager
        btmNavbar.setOnNavigationItemSelectedListener(btmNavbar::handleNavigation)
        btmNavbar.initSelectedIconMenu()
        btmNavbar.addNumberBadge(TANavbarMenu.HISTORY.ordinal, "99+")
        btmNavbar.addDotBadge(TANavbarMenu.PROFILE.ordinal)
    }
}