package com.lionparcel.commonandroidsample.accordion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lionparcel.commonandroid.accordion.LPAccordion
import com.lionparcel.commonandroidsample.R

class AccordionComponentSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accordion_component_sample)
        findViewById<LPAccordion>(R.id.lp_accordion_size_l).apply {
            setTitle("Large")
        }
        findViewById<LPAccordion>(R.id.lp_accordion_size_m).apply {
            setTitle("Medium")
        }
        findViewById<LPAccordion>(R.id.lp_accordion_size_s).apply {
            setTitle("Small")
        }
        findViewById<LPAccordion>(R.id.lp_accordion_spacing_m).apply {
            setTitle("Spacing M/16Dp")
            setAccordionOnClickListener {  }
        }
        findViewById<LPAccordion>(R.id.lp_accordion_spacing_l).apply {
            setTitle("Spacing L/20Dp")
        }
        findViewById<LPAccordion>(R.id.lp_accordion_spacing_xl).apply {
            setTitle("Spacing XL/24Dp")
        }
        findViewById<LPAccordion>(R.id.lp_accordion_border).apply {
            setTitle("Border")
        }
        findViewById<LPAccordion>(R.id.lp_accordion_no_border).apply {
            setTitle("No Border")
        }
        findViewById<LPAccordion>(R.id.lp_accordion_border_without_text).apply {
            setTitle("Border Without Text")
            setAccordionOnClickListener {  }
        }
        findViewById<LPAccordion>(R.id.lp_accordion_no_border_without_text).apply {
            setTitle("No Border Without Text")
            setAccordionOnClickListener {  }
        }
        findViewById<LPAccordion>(R.id.lp_accordion_border_with_text).apply {
            setTitle("Border With Text")
            setAccordionOnClickListener {  }
            setContent(title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ullamcorper nibh diam arcu massa dui.", content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. A, vel sit interdum in aliquet nisi. Molestie massa egestas cum vehicula felis gravida. Id hendrerit quis ornare venenatis congue habitant placerat facilisi pulvinar. Donec molestie egestas ac magna.")
        }
        findViewById<LPAccordion>(R.id.lp_accordion_no_border_with_text).apply {
            setTitle("No Border With Text")
            setAccordionOnClickListener {  }
            setContent(title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ullamcorper nibh diam arcu massa dui.", content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. A, vel sit interdum in aliquet nisi. Molestie massa egestas cum vehicula felis gravida. Id hendrerit quis ornare venenatis congue habitant placerat facilisi pulvinar. Donec molestie egestas ac magna.")
        }
    }
}