package com.lionparcel.commonandroidsample.tooltip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.lionparcel.commonandroid.tooltip.createTooltip
import com.lionparcel.commonandroidsample.R

class TooltipComponentSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tooltip_component_sample)
        findViewById<Button>(R.id.btnTooltipCenter).setOnClickListener {
            val tooltip = createTooltip(this) {
                setText("Hello World")
            }
            tooltip.showAtCenter(findViewById(R.id.ivTooltip), -80, 100)
        }
    }
}