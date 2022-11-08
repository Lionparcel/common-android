package com.lionparcel.commonandroidsample.navbar.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.lionparcel.commonandroid.navbar.utils.CANavbarMenu

class NavbarViewPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int = 4

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> TrackFragment()
            2 -> PaymentFragment()
            3 -> ProfileFragment()
            else -> throw IndexOutOfBoundsException()
        }
    }
}