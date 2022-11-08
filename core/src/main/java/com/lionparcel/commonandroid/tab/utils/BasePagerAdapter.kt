package com.lionparcel.commonandroid.tab.utils

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

@Suppress("DEPRECATION")
abstract class BasePagerAdapter(
    fragmentManager: FragmentManager,
    behavior: Int = BEHAVIOR_SET_USER_VISIBLE_HINT
) : FragmentStatePagerAdapter(fragmentManager, behavior) {

    abstract fun getTabView(position: Int, root: ViewGroup) : View
}
