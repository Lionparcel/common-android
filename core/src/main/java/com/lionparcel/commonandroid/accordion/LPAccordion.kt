package com.lionparcel.commonandroid.accordion

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.databinding.LpLayoutAccordionTextBinding

class LPAccordion @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: LpLayoutAccordionTextBinding = LpLayoutAccordionTextBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    private var isCollapsed: Boolean = false

    private var size: Int
    private var margin: Int
    private var style: Int

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPAccordion,
            0,
            0
        ).apply {
            try {
                size = getInt(R.styleable.LPAccordion_accordionSize, 0)
                margin = getInt(R.styleable.LPAccordion_accordionMargin, 0)
                style = getInt(R.styleable.LPAccordion_accordionStyle, 0)
            } finally {
                recycle()
            }
        }
        setAccordionStyle()
        setAccordionSize()
        changeIcon()
    }

    private fun setAccordionStyle() {
        when (style) {
            0 -> {
                binding.root
                binding.vwLineTop.visibility = VISIBLE
                binding.vwLineBottom.visibility = VISIBLE
            }
            1 -> {
                binding.root
                binding.vwLineTop.visibility = GONE
                binding.vwLineBottom.visibility = GONE
            }
        }
    }

    private fun setAccordionSize() {
        val margin = when (this.margin) {
            0 -> 16
            1 -> 20
            2 -> 24
            else -> 16
        }
        val scale = resources.displayMetrics.density
        when (size) {
            0 -> {
                binding.clAccordionText.setPadding(
                    (margin * scale).toInt(),
                    (16 * scale).toInt(),
                    (margin * scale).toInt(),
                    (16 * scale).toInt()
                )
                binding.tvAccordion.textSize = 16F
                binding.ivAccordion.apply {
                    val params = this.layoutParams as ConstraintLayout.LayoutParams
                    params.height = (24 * scale).toInt()
                    params.width = (24 * scale).toInt()
                    layoutParams = params
                }
            }
            1 -> {
                binding.clAccordionText.setPadding(
                    (margin * scale).toInt(),
                    (12 * scale).toInt(),
                    (margin * scale).toInt(),
                    (12 * scale).toInt()
                )
                binding.tvAccordion.textSize = 14F
                binding.ivAccordion.apply {
                    val params = this.layoutParams as ConstraintLayout.LayoutParams
                    params.height = (20 * scale).toInt()
                    params.width = (20 * scale).toInt()
                    layoutParams = params
                }
            }
            2 -> {
                binding.clAccordionText.setPadding(
                    (margin * scale).toInt(),
                    (8 * scale).toInt(),
                    (margin * scale).toInt(),
                    (8 * scale).toInt()
                )
                binding.tvAccordion.textSize = 12F
                binding.ivAccordion.apply {
                    val params = this.layoutParams as ConstraintLayout.LayoutParams
                    params.height = (16 * scale).toInt()
                    params.width = (16 * scale).toInt()
                    layoutParams = params
                }
            }
        }
    }

    private fun changeIcon() {
        if (isCollapsed) {
            binding.ivAccordion.setImageResource(R.drawable.ics_o_chevron_up)
        } else {
            binding.ivAccordion.setImageResource(R.drawable.ics_o_chevron_down)
        }
    }

    fun setTitle(title: String) {
        binding.tvAccordion.text = title
    }

    fun setAccordionOnClickListener(listener: (View) -> Unit) {
        this.setOnClickListener {
            isCollapsed = !isCollapsed
            listener.invoke(it).run {
                changeIcon()
            }
        }
    }
}