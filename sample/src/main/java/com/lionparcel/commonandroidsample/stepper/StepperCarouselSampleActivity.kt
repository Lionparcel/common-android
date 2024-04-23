package com.lionparcel.commonandroidsample.stepper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.lionparcel.commonandroid.stepper.LPStepperCarousel
import com.lionparcel.commonandroid.stepper.LPStepperCarouselBar
import com.lionparcel.commonandroid.stepper.LPStepperCarouselBarVertical
import com.lionparcel.commonandroidsample.R
import com.lionparcel.commonandroidsample.stepper.utils.ViewPager2Adapter
import com.lionparcel.commonandroidsample.stepper.utils.ViewPagerAdapter

class StepperCarouselSampleActivity : AppCompatActivity() {
    private val imageList = arrayListOf(
        R.drawable.img_stepper_success,
        R.drawable.spot_illustration,
        R.drawable.img_stepper_failed,
        R.drawable.ic_stepper_badge_warning,
        R.drawable.ic_16_checkbox_red
    )
    private val numberList = arrayListOf(
        "1",
        "2",
        "3"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stepper_carousel_sample)

        val viewPager = findViewById<ViewPager>(R.id.iv_view_pager_1)
        val stepperCarousel = findViewById<LPStepperCarousel>(R.id.lp_stepper_carousel_1)
        val viewPager2 = findViewById<ViewPager>(R.id.iv_view_pager_2)
        val stepperCarousel2 = findViewById<LPStepperCarousel>(R.id.lp_stepper_carousel_2)
        val viewPager3 = findViewById<ViewPager2>(R.id.iv_view_pager_3)
        val stepperCarousel3 = findViewById<LPStepperCarousel>(R.id.lp_stepper_carousel_3)
        val viewPager4 = findViewById<ViewPager2>(R.id.iv_view_pager_4)
        val stepperCarousel4 = findViewById<LPStepperCarouselBarVertical>(R.id.lp_stepper_carousel_4)

        viewPager.adapter = ViewPagerAdapter(this, imageList)
        stepperCarousel.setViewPager(viewPager)
        viewPager2.adapter = ViewPagerAdapter(this, imageList)
        stepperCarousel2.setViewPager(viewPager2)
        viewPager3.adapter = ViewPager2Adapter(numberList)
        stepperCarousel3.setViewPager2(viewPager3)
        viewPager4.adapter = ViewPager2Adapter(numberList)
        stepperCarousel4.setViewPager(viewPager4)
        viewPager.apply {
            pageMargin = 16
            currentItem = 1
        }
        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, possitonOffsetPixel: Int) {
                val totalItem = imageList.size + 2
                if ( imageList.size > 1) {
                    if (position >= totalItem.minus(2) && positionOffset > 0.5f)
                        viewPager.currentItem = 1
                    else if (position < 1 && positionOffset < 0.5f) viewPager.currentItem = totalItem.minus(2)
                }
            }

            override fun onPageSelected(p0: Int) {

            }

            override fun onPageScrollStateChanged(p0: Int) {

            }

        })
        viewPager3.setCurrentItem(1, true)
        viewPager3.registerOnPageChangeCallback(object : OnPageChangeCallback(){
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                val totalItem = numberList.size + 2
                if ( numberList.size > 1) {
                    if (position >= totalItem.minus(2) && positionOffset > 0.5f)
                        viewPager3.setCurrentItem(1, true)
                    else if (position < 1 && positionOffset < 0.5f) viewPager3.setCurrentItem(totalItem.minus(2), true)
                }
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        })
    }
}
