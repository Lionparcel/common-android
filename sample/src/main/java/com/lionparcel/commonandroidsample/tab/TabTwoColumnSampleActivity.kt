package com.lionparcel.commonandroidsample.tab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.lionparcel.commonandroid.tab.LPTabLayout
import com.lionparcel.commonandroidsample.R
import com.lionparcel.commonandroidsample.tab.utils.TabSampleAdapter

class TabTwoColumnSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_two_column_sample)

        val lpTabLayout = findViewById<LPTabLayout>(R.id.lp_tab_layout)
        val vpTabLayout = findViewById<ViewPager>(R.id.vp_tab_layout)
        val adapter = TabSampleAdapter(this, supportFragmentManager, 2,lpTabLayout) {
            true
        }

        vpTabLayout.adapter = adapter
        vpTabLayout.offscreenPageLimit = adapter.count - 1
        lpTabLayout.setupWithViewPager(vpTabLayout)
        lpTabLayout.setTabViewMargin(adapter)
        lpTabLayout.changeTextAppearance(vpTabLayout.currentItem)

        vpTabLayout.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                lpTabLayout.changeTextAppearance(vpTabLayout.currentItem)
            }

            override fun onPageScrollStateChanged(state: Int) {
                lpTabLayout.changeTextAppearance(vpTabLayout.currentItem)
            }

        })
    }
}
