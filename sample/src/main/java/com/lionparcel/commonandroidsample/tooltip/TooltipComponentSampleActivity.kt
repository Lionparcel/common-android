package com.lionparcel.commonandroidsample.tooltip

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
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
                it,
                0,
                0,
                LPTooltip.PositionVertical.BELOW,
                LPTooltip.HorizontalArrowAlignment.LEFT,
                true
            )
        }

        findViewById<Button>(R.id.btnTooltipTopCenter).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            showTooltipUpDown(
                it,
                0,
                0,
                LPTooltip.PositionVertical.BELOW,
                LPTooltip.HorizontalArrowAlignment.CENTER
            )
        }

        findViewById<Button>(R.id.btnTooltipTopRight).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            showTooltipUpDown(
                it,
                0,
                0,
                LPTooltip.PositionVertical.BELOW,
                LPTooltip.HorizontalArrowAlignment.RIGHT,
                true
            )
        }

        findViewById<Button>(R.id.btnTooltipCenter).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            showTooltipUpDown(
                it,
                0,
                0,
                LPTooltip.PositionVertical.BELOW,
                LPTooltip.HorizontalArrowAlignment.CENTER
            )
        }

        findViewById<Button>(R.id.btnTooltipBottomLeft).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            showTooltipUpDown(
                it,
                0,
                0,
                LPTooltip.PositionVertical.ABOVE,
                LPTooltip.HorizontalArrowAlignment.LEFT,
                true
            )
        }

        findViewById<Button>(R.id.btnTooltipBottomCenter).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            showTooltipUpDown(
                it,
                0,
                0,
                LPTooltip.PositionVertical.ABOVE,
                LPTooltip.HorizontalArrowAlignment.CENTER
            )
        }

        findViewById<Button>(R.id.btnTooltipBottomRight).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            showTooltipUpDown(
                it,
                0,
                0,
                LPTooltip.PositionVertical.ABOVE,
                LPTooltip.HorizontalArrowAlignment.CENTER_RIGHT
            )
        }

        findViewById<Button>(R.id.btnTooltipTopCenterLeft).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            showTooltipLeftRight(
                it,
                0,
                0,
                LPTooltip.PositionHorizontal.RIGHT,
                LPTooltip.VerticalArrowAlignment.TOP
            )
        }
        findViewById<Button>(R.id.btnTooltipTopCenterRight).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            showTooltipLeftRight(
                it,
                -50,
                0,
                LPTooltip.PositionHorizontal.LEFT,
                LPTooltip.VerticalArrowAlignment.TOP
            )
        }

        findViewById<Button>(R.id.btnTooltipCenterLeft).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            showTooltipLeftRight(
                it,
                0,
                0,
                LPTooltip.PositionHorizontal.RIGHT,
                LPTooltip.VerticalArrowAlignment.CENTER,
                true
            )
        }

        findViewById<Button>(R.id.btnTooltipCenterRight).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            showTooltipLeftRight(
                it,
                -it.measuredWidth/2,
                0,
                LPTooltip.PositionHorizontal.LEFT,
                LPTooltip.VerticalArrowAlignment.BOTTOM
            )
        }

        findViewById<Button>(R.id.btnTooltipBottomCenterLeft).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            showTooltipLeftRight(
                it,
                0,
                0,
                LPTooltip.PositionHorizontal.RIGHT,
                LPTooltip.VerticalArrowAlignment.BOTTOM
            )
        }

        findViewById<Button>(R.id.btnTooltipBottomCenterRight).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            showTooltipLeftRight(
                it,
                20,
                0,
                LPTooltip.PositionHorizontal.LEFT,
                LPTooltip.VerticalArrowAlignment.BOTTOM
            )
        }

        findViewById<ImageView>(R.id.ivInfoTooltip).setOnClickListener {
            showTooltipUpDown(
                it,
                0,
                0,
                LPTooltip.PositionVertical.ABOVE,
                LPTooltip.HorizontalArrowAlignment.CENTER
            )
        }

        findViewById<ImageView>(R.id.ivInfoTooltip2).setOnClickListener {
            showTooltipUpDown(
                it,
                -it.width*7/2,
                -it.height*2,
                LPTooltip.PositionVertical.ABOVE,
                LPTooltip.HorizontalArrowAlignment.CENTER
            )
        }
    }

    private fun showTooltipUpDown(
        view: View,
        x: Int,
        y: Int,
        positionVertical: LPTooltip.PositionVertical,
        alignment: LPTooltip.HorizontalArrowAlignment,
        showCloseIcon: Boolean = false
    ) {
            val tooltip = LPTooltip(this, this)
                .content(getString(R.string.general_message))
                .pointToUpDown(
                    view,
                    x,
                    y,
                    positionVertical,
                    alignment,
                    showCloseIcon
                )
            tooltip.show()
    }

    private fun showTooltipLeftRight(
        view: View,
        x: Int,
        y: Int,
        positionHorizontal: LPTooltip.PositionHorizontal,
        alignment: LPTooltip.VerticalArrowAlignment,
        showCloseIcon: Boolean = false
    ) {
            val tooltip = LPTooltip(this, this)
                .content(getString(R.string.general_message))
                .pointToLeftRight(
                    view,
                    x,
                    y,
                    positionHorizontal,
                    alignment,
                    showCloseIcon
                )
            tooltip.show()
    }
}