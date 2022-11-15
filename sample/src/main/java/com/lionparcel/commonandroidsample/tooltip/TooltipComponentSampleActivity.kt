package com.lionparcel.commonandroidsample.tooltip

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.lionparcel.commonandroid.tooltip.LPTooltip
import com.lionparcel.commonandroidsample.R

class TooltipComponentSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tooltip_component_sample)

        findViewById<Button>(R.id.btnTooltipTopLeft).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            showTooltipUpDown(
                location[0] + it.width/2,
                location[1] + it.height,
                LPTooltip.PositionVertical.BELOW,
                LPTooltip.HorizontalArrowAlignment.LEFT,
                true
            )
        }

        findViewById<Button>(R.id.btnTooltipTopCenter).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            showTooltipUpDown(
                location[0] + it.width/2,
                location[1] + it.height,
                LPTooltip.PositionVertical.BELOW,
                LPTooltip.HorizontalArrowAlignment.CENTER
            )
        }

        findViewById<Button>(R.id.btnTooltipTopRight).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            showTooltipUpDown(
                location[0] + it.width,
                location[1] + it.height,
                LPTooltip.PositionVertical.BELOW,
                LPTooltip.HorizontalArrowAlignment.RIGHT,
                true
            )
        }

        findViewById<Button>(R.id.btnTooltipCenter).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            showTooltipUpDown(
                location[0] + it.width/2,
                location[1] + it.height,
                LPTooltip.PositionVertical.BELOW,
                LPTooltip.HorizontalArrowAlignment.CENTER
            )
        }

        findViewById<Button>(R.id.btnTooltipBottomLeft).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            showTooltipUpDown(
                location[0],
                location[1] - it.height,
                LPTooltip.PositionVertical.ABOVE,
                LPTooltip.HorizontalArrowAlignment.LEFT,
                true
            )
        }

        findViewById<Button>(R.id.btnTooltipBottomCenter).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            showTooltipUpDown(
                location[0] + it.width/2,
                location[1] - it.height,
                LPTooltip.PositionVertical.ABOVE,
                LPTooltip.HorizontalArrowAlignment.CENTER
            )
        }

        findViewById<Button>(R.id.btnTooltipBottomRight).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            showTooltipUpDown(
                location[0] + it.width,
                location[1] - it.height,
                LPTooltip.PositionVertical.ABOVE,
                LPTooltip.HorizontalArrowAlignment.CENTER_RIGHT
            )
        }

        findViewById<Button>(R.id.btnTooltipTopCenterLeft).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            showTooltipLeftRight(
                location[0] + it.width,
                location[1],
                LPTooltip.PositionHorizontal.RIGHT,
                LPTooltip.VerticalArrowAlignment.TOP
            )
        }
        findViewById<Button>(R.id.btnTooltipTopCenterRight).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            showTooltipLeftRight(
                location[0],
                location[1],
                LPTooltip.PositionHorizontal.LEFT,
                LPTooltip.VerticalArrowAlignment.TOP
            )
        }

        findViewById<Button>(R.id.btnTooltipCenterLeft).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            showTooltipLeftRight(
                location[0] + it.width,
                location[1] + it.height/2,
                LPTooltip.PositionHorizontal.RIGHT,
                LPTooltip.VerticalArrowAlignment.CENTER,
                true
            )
        }

        findViewById<Button>(R.id.btnTooltipCenterRight).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            showTooltipLeftRight(
                location[0],
                location[1] + it.height/2,
                LPTooltip.PositionHorizontal.LEFT,
                LPTooltip.VerticalArrowAlignment.BOTTOM
            )
        }

        findViewById<Button>(R.id.btnTooltipBottomCenterLeft).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            showTooltipLeftRight(
                location[0] + it.width,
                location[1] + it.height,
                LPTooltip.PositionHorizontal.RIGHT,
                LPTooltip.VerticalArrowAlignment.BOTTOM
            )
        }

        findViewById<Button>(R.id.btnTooltipBottomCenterRight).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            showTooltipLeftRight(
                location[0],
                location[1] + it.height,
                LPTooltip.PositionHorizontal.LEFT,
                LPTooltip.VerticalArrowAlignment.BOTTOM
            )
        }
    }

    private fun showTooltipUpDown(
        x: Int,
        y: Int,
        positionVertical: LPTooltip.PositionVertical,
        alignment: LPTooltip.HorizontalArrowAlignment,
        showCloseIcon: Boolean = false
    ) {
        if (showCloseIcon) {
            val tooltip = LPTooltip(this, this)
                .content(getString(R.string.general_message))
                .pointToUpDown(
                    x,
                    y,
                    positionVertical,
                    alignment
                )
                .closeIcon()
            tooltip.show()
        } else {
            val tooltip = LPTooltip(this, this)
                .content(getString(R.string.general_message))
                .pointToUpDown(
                    x,
                    y,
                    positionVertical,
                    alignment
                )
            tooltip.show()
        }
    }

    private fun showTooltipLeftRight(
        x: Int,
        y: Int,
        positionHorizontal: LPTooltip.PositionHorizontal,
        alignment: LPTooltip.VerticalArrowAlignment,
        showCloseIcon: Boolean = false
    ) {
        if (showCloseIcon) {
            val tooltip = LPTooltip(this, this)
                .content(getString(R.string.general_message))
                .pointToLeftRight(
                    x,
                    y,
                    positionHorizontal,
                    alignment
                )
                .closeIcon()
            tooltip.show()
        } else {
            val tooltip = LPTooltip(this, this)
                .content(getString(R.string.general_message))
                .pointToLeftRight(
                    x,
                    y,
                    positionHorizontal,
                    alignment
                )
            tooltip.show()
        }
    }
}