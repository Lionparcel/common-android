package com.lionparcel.commonandroidsample.stepper

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.lionparcel.commonandroid.stepper.LPStepperCarousel
import com.lionparcel.commonandroidsample.R
import com.lionparcel.commonandroidsample.stepper.utils.ViewPagerAdapter

class StepperCarouselSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stepper_carousel_sample)

        val imageList = arrayListOf(
            R.drawable.img_stepper_success,
            R.drawable.spot_illustration,
            R.drawable.img_stepper_failed
        )
        val viewPager = findViewById<ViewPager>(R.id.iv_view_pager_1)
        val stepperCarousel = findViewById<LPStepperCarousel>(R.id.lp_stepper_carousel_1)
        val viewPager2 = findViewById<ViewPager>(R.id.iv_view_pager_2)
        val stepperCarousel2 = findViewById<LPStepperCarousel>(R.id.lp_stepper_carousel_2)

        viewPager.adapter = ViewPagerAdapter(this, imageList)
        stepperCarousel.setViewPager(viewPager)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {}

            override fun onPageScrollStateChanged(state: Int) {}

        })
        viewPager2.adapter = ViewPagerAdapter(this, imageList)
        stepperCarousel2.setViewPager(viewPager2)
    }
}
