package com.lionparcel.commonandroidsample.accordion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lionparcel.commonandroid.accordion.LPAccordion
import com.lionparcel.commonandroidsample.R

class AccordionComponentSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accordion_component_sample)
        findViewById<LPAccordion>(R.id.lp_accordion_m_1).apply {
            setTitle("Accordion Text Here")
            setAccordionOnClickListener {  }
            setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ullamcorper nibh diam arcu massa dui.","Lorem ipsum dolor sit amet, consectetur adipiscing elit. A, vel sit interdum in aliquet nisi. Molestie massa egestas cum vehicula felis gravida. Id hendrerit quis ornare venenatis congue habitant placerat facilisi pulvinar. Donec molestie egestas ac magna.")
        }
        findViewById<LPAccordion>(R.id.lp_accordion_m_2).apply {
            setTitle("Accordion Text Here")
            setAccordionOnClickListener {  }
            setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ullamcorper nibh diam arcu massa dui.","Lorem ipsum dolor sit amet, consectetur adipiscing elit. A, vel sit interdum in aliquet nisi. Molestie massa egestas cum vehicula felis gravida. Id hendrerit quis ornare venenatis congue habitant placerat facilisi pulvinar. Donec molestie egestas ac magna.")
        }
        findViewById<LPAccordion>(R.id.lp_accordion_m_3).apply {
            setTitle("Accordion Text Here")
            setAccordionOnClickListener {  }
            setContent(content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. A, vel sit interdum in aliquet nisi. Molestie massa egestas cum vehicula felis gravida. Id hendrerit quis ornare venenatis congue habitant placerat facilisi pulvinar. Donec molestie egestas ac magna.")
        }
        findViewById<LPAccordion>(R.id.lp_accordion_l_1).apply {
            setTitle("Accordion Text Here")
        }
        findViewById<LPAccordion>(R.id.lp_accordion_l_2).apply {
            setTitle("Accordion Text Here")
            setAccordionOnClickListener {  }
            setContent(content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. A, vel sit interdum in aliquet nisi. Molestie massa egestas cum vehicula felis gravida. Id hendrerit quis ornare venenatis congue habitant placerat facilisi pulvinar. Donec molestie egestas ac magna.")
        }
        findViewById<LPAccordion>(R.id.lp_accordion_l_3).apply {
            setTitle("Accordion Text Here")
        }
        findViewById<LPAccordion>(R.id.lp_accordion_xl_1).apply {
            setTitle("Accordion Text Here")
        }
        findViewById<LPAccordion>(R.id.lp_accordion_xl_2).apply {
            setTitle("Accordion Text Here")
            setAccordionOnClickListener {  }
            setContent(content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. A, vel sit interdum in aliquet nisi. Molestie massa egestas cum vehicula felis gravida. Id hendrerit quis ornare venenatis congue habitant placerat facilisi pulvinar. Donec molestie egestas ac magna.")
        }
        findViewById<LPAccordion>(R.id.lp_accordion_xl_3).apply {
            setTitle("Accordion Text Here")
        }
        findViewById<LPAccordion>(R.id.lp_accordion_no_border_1).apply {
            setTitle("Accordion Text Here")
        }
        findViewById<LPAccordion>(R.id.lp_accordion_no_border_2).apply {
            setTitle("Accordion Text Here")
            setAccordionOnClickListener {  }
            setContent(content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. A, vel sit interdum in aliquet nisi. Molestie massa egestas cum vehicula felis gravida. Id hendrerit quis ornare venenatis congue habitant placerat facilisi pulvinar. Donec molestie egestas ac magna.")
        }
        findViewById<LPAccordion>(R.id.lp_accordion_no_border_3).apply {
            setTitle("Accordion Text Here")
        }
    }
}