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
    private val itemCount : Int,
    private val tabLayout: LPTabLayout,
    private val showRedBadge: () -> Boolean?
) : BasePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        const val ONGOING = 0
        const val HISTORY = 1
        const val PAYLATER = 2
        const val TAB = 3
    }

    override fun getTabView(position: Int, root: ViewGroup): View {
        return tabLayout.setLayout(context, root, position, this, HISTORY, showRedBadge)
    }

    override fun getCount(): Int = itemCount

    override fun getItem(position: Int): Fragment {
        return when (position) {
            ONGOING -> OngoingFragment()
            HISTORY -> HistoryFragment()
            PAYLATER -> PaylaterFragment()
            TAB -> TabFragment()
            else -> throwIndexOutOfBounds(position)
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            ONGOING -> "Aktif"
            HISTORY -> "Riwayat"
            PAYLATER -> "Paylater"
            TAB -> "Tab"
            else -> throwIndexOutOfBounds(position)
        }
    }

    private fun throwIndexOutOfBounds(position: Int): Nothing =
        throw IndexOutOfBoundsException("Error $position")
}
