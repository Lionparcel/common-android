package com.lionparcel.commonandroidsample.stepper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.lionparcel.commonandroid.stepper.LPStepperCarousel
import com.lionparcel.commonandroid.stepper.LPStepperCarouselBar
import com.lionparcel.commonandroidsample.R
import com.lionparcel.commonandroidsample.stepper.utils.ViewPager2Adapter
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
        val viewPager3 = findViewById<ViewPager2>(R.id.iv_view_pager_3)
        val stepperCarousel3 = findViewById<LPStepperCarouselBar>(R.id.lp_stepper_carousel_3)

        viewPager.adapter = ViewPagerAdapter(this, imageList)
        stepperCarousel.setViewPager(viewPager)
        viewPager2.adapter = ViewPagerAdapter(this, imageList)
        stepperCarousel2.setViewPager(viewPager2)
        viewPager3.adapter = ViewPager2Adapter()
        stepperCarousel3.setViewPager(viewPager3)
    }
}
