package com.lionparcel.commonandroidsample.tooltip

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.lionparcel.commonandroid.tooltip.createLPTooltip
import com.lionparcel.commonandroidsample.R

class TooltipComponentSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tooltip_component_sample)

        findViewById<Button>(R.id.btnTooltipTopLeft).setOnClickListener {
            createLPTooltip(this) {
                setText(getString(R.string.general_message))
                setCloseIcon(false)
                setWidth(220)
                setHeight(60)
                setArrowPosition(0.25F)
            }.showFromBelow(it, -it.measuredWidth/3)
        }

        findViewById<Button>(R.id.btnTooltipTopCenter).setOnClickListener {
            createLPTooltip(this) {
                setText(getString(R.string.general_message))
                setCloseIcon(false)
                setWidth(200)
                setHeight(60)
            }.showFromBelow(it)
        }

        findViewById<Button>(R.id.btnTooltipTopRight).setOnClickListener {
            createLPTooltip(this) {
                setText(getString(R.string.general_message))
                setCloseIcon(true)
                setWidth(245)
                setHeight(60)
                setArrowPosition(0.75F)
            }.showFromBelow(it, it.measuredWidth/3)
        }

        findViewById<Button>(R.id.btnTooltipCenter).setOnClickListener {
            createLPTooltip(this) {
                setText(getString(R.string.general_message))
                setCloseIcon(true)
                setWidth(245)
                setHeight(60)
            }.showFromBelow(it)
        }

        findViewById<Button>(R.id.btnTooltipBottomLeft).setOnClickListener {
            createLPTooltip(this) {
                setText(getString(R.string.general_message))
                setCloseIcon(false)
                setWidth(220)
                setHeight(60)
                setArrowPosition(0.25F)
            }.showFromAbove(it, -it.measuredWidth/3)
        }

        findViewById<Button>(R.id.btnTooltipBottomCenter).setOnClickListener {
            val location = intArrayOf(0, 0)
            it.getLocationInWindow(location)
            createLPTooltip(this) {
                setText(getString(R.string.general_message))
                setCloseIcon(true)
                setWidth(245)
                setHeight(60)
            }.showFromAbove(it)
        }

        findViewById<Button>(R.id.btnTooltipBottomRight).setOnClickListener {
            createLPTooltip(this) {
                setText(getString(R.string.general_message))
                setCloseIcon(true)
                setWidth(245)
                setHeight(60)
                setArrowPosition(0.75F)
            }.showFromAbove(it, it.measuredWidth/3)
        }

        findViewById<Button>(R.id.btnTooltipTopCenterLeft).setOnClickListener {
            createLPTooltip(this) {
                setText(getString(R.string.general_message))
                setCloseIcon(false)
                setWidth(220)
                setHeight(50)
                setArrowPosition(0.75F)
            }.showFromRight(it, 0, -it.measuredHeight/4)
        }
        findViewById<Button>(R.id.btnTooltipTopCenterRight).setOnClickListener {
            createLPTooltip(this) {
                setText(getString(R.string.general_message))
                setCloseIcon(false)
                setWidth(215)
                setHeight(50)
                setArrowPosition(0.75F)
            }.showFromLeft(it, 0, -it.measuredHeight/4)
        }

        findViewById<Button>(R.id.btnTooltipCenterLeft).setOnClickListener {
            createLPTooltip(this) {
                setText(getString(R.string.general_message))
                setCloseIcon(true)
                setWidth(255)
                setHeight(50)
            }.showFromRight(it)
        }

        findViewById<Button>(R.id.btnTooltipCenterRight).setOnClickListener {
            createLPTooltip(this) {
                setText(getString(R.string.general_message))
                setCloseIcon(true)
                setWidth(255)
                setHeight(50)
            }.showFromLeft(it)
        }

        findViewById<Button>(R.id.btnTooltipBottomCenterLeft).setOnClickListener {
            createLPTooltip(this) {
                setText(getString(R.string.general_short_message))
                setCloseIcon(false)
                setWidth(215)
                setHeight(35)
            }.showFromRight(it)
        }

        findViewById<Button>(R.id.btnTooltipBottomCenterRight).setOnClickListener {
            createLPTooltip(this) {
                setText(getString(R.string.general_short_message))
                setCloseIcon(false)
                setWidth(205)
                setHeight(35)
            }.showFromLeft(it)
        }

        findViewById<ImageView>(R.id.ivInfoTooltip).setOnClickListener {
            createLPTooltip(this) {
                setText(getString(R.string.general_short_message))
                setCloseIcon(true)
                setWidth(238)
                setHeight(50)
            }.showFromBelow(it)
        }

        findViewById<ImageView>(R.id.ivInfoTooltip2).setOnClickListener {
            createLPTooltip(this) {
                setText(getString(R.string.general_message))
                setCloseIcon(false)
                setWidth(205)
                setHeight(60)
                setArrowPosition(0.23F)
            }.apply {
                val viewWidth = getMeasuredWidth()
                showFromAbove(it, viewWidth/4)
            }
        }
    }
}