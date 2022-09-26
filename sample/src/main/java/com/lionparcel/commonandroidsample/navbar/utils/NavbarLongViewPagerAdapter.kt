package com.lionparcel.commonandroidsample.navbar.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class NavbarLongViewPagerAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int = 5

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> TrackFragment()
            2 -> HelpdeskFragment()
            3 -> PaymentFragment()
            4 -> ProfileFragment()
            else -> throw IndexOutOfBoundsException()
        }
    }
}