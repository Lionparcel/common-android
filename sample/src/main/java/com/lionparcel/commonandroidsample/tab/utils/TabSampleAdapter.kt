package com.lionparcel.commonandroidsample.tab.utils

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.lionparcel.commonandroid.tab.LPTabLayout
import com.lionparcel.commonandroid.tab.utils.BasePagerAdapter

class TabSampleAdapter(
    private val context: Context,
    fragmentManager: FragmentManager,
    private val tabLayout: LPTabLayout,
    private val showRedBadge: () -> Boolean?
) : BasePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        const val ONGOING = 0
        const val HISTORY = 1
    }

    override fun getTabView(position: Int, root: ViewGroup): View {
        return tabLayout.setLayout(context, root, position, this, HISTORY, showRedBadge)
    }

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            ONGOING -> OngoingFragment()
            HISTORY -> HistoryFragment()
            else -> throwIndexOutOfBounds(position)
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            ONGOING -> "Aktif"
            HISTORY -> "Riwayat"
            else -> throwIndexOutOfBounds(position)
        }
    }

    private fun throwIndexOutOfBounds(position: Int): Nothing =
        throw IndexOutOfBoundsException("Error $position")
}