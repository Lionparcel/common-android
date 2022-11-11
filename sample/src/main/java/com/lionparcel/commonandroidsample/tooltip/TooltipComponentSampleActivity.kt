package com.lionparcel.commonandroidsample.tooltip

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.lionparcel.commonandroid.tooltip.LPTooltip
import com.lionparcel.commonandroidsample.R

class TooltipComponentSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tooltip_component_sample)
        findViewById<Button>(R.id.btnTooltipTopLeft).setOnClickListener {
            val location = intArrayOf(0,0)
            it.getLocationInWindow(location)
            val tooltip = LPTooltip(this, this)
                .content(getString(R.string.general_message))
                .pointTo(location[0] + it.width / 2, location[1] + it.height)
            tooltip.show()
        }

        findViewById<Button>(R.id.btnTooltipTopCenter).setOnClickListener {
            val location = intArrayOf(0,0)
            it.getLocationInWindow(location)
            val tooltip = LPTooltip(this, this)
                .content(getString(R.string.general_message))
                .pointTo(location[0] + it.width / 4 , location[1] + it.height)
                .closeIcon()
            tooltip.show()
            Log.i("X position", (location[0] + it.width / 2).toString())
        }

        findViewById<Button>(R.id.btnTooltipBottomLeft).setOnClickListener {
            val location = intArrayOf(0,0)
            it.getLocationInWindow(location)
            val tooltip = LPTooltip(this, this)
                .content(getString(R.string.general_message))
                .pointTo(location[0] + it.width / 2, location[1] - it.height, LPTooltip.Position.ABOVE)
                .closeIcon()
            tooltip.show()
        }

        findViewById<Button>(R.id.btnTooltipBottomCenter).setOnClickListener {
            val location = intArrayOf(0,0)
            it.getLocationInWindow(location)
            val tooltip = LPTooltip(this, this)
                .content(getString(R.string.general_message))
                .pointTo(location[0] + it.width / 8 , location[1] - it.height, LPTooltip.Position.ABOVE)
            tooltip.show()
            Log.i("X position", (location[0] + it.width / 2).toString())
        }
    }
}