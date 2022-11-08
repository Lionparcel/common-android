package com.lionparcel.commonandroidsample.navbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.lionparcel.commonandroid.navbar.LPNavbarDA
import com.lionparcel.commonandroid.navbar.utils.DANavbarLongMenu
import com.lionparcel.commonandroidsample.R
import com.lionparcel.commonandroidsample.navbar.utils.NavbarViewPagerAdapter

class NavbarDALongSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navbar_dalong_sample)
        val viewPager = findViewById<ViewPager>(R.id.vp_navbar_da_long)
        val btmNavbar = findViewById<LPNavbarDA>(R.id.btm_navbar_da_long)
        viewPager.adapter = NavbarViewPagerAdapter(supportFragmentManager)
        btmNavbar.viewPager = viewPager
        btmNavbar.setOnNavigationItemSelectedListener(btmNavbar::handleNavigation)
        btmNavbar.initSelectedIconMenu()
        btmNavbar.addNumberBadge(DANavbarLongMenu.EARNING.ordinal, "99")
    }
}