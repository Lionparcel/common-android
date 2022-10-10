package com.lionparcel.commonandroid.alert

import android.annotation.SuppressLint
import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.ImageViewCompat
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.alert.utils.AlertState
import com.lionparcel.commonandroid.databinding.LpLayoutAlertBinding
import com.lionparcel.commonandroid.form.utils.*

class LPAlert @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttrs: Int = 0
) : LinearLayout(context, attrs, defStyleAttrs) {

    private val binding: LpLayoutAlertBinding = LpLayoutAlertBinding.inflate(LayoutInflater.from(context), this, true)

    private var size : Int
    private var style: Int

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPAlert,
            0,
            0
        ).apply {
            try {
                size = getInt(R.styleable.LPAlert_alertSize, 0)
                style = getInt(R.styleable.LPAlert_alertStyle, 0)
            } finally {
                recycle()
            }
        }
        binding.root
        setAlertSize()
        setAlertStyle()
    }

    private fun setAlertSize() {
        val scale = resources.displayMetrics.density
        when (size) {
            0 -> {
                binding.llAlert.setPadding((16 * scale).toInt(), (12 * scale).toInt(), (16 * scale).toInt(), (12 * scale).toInt())
                binding.iconStartAlert.apply {
                    val params = this.layoutParams as MarginLayoutParams
                    layoutParams.height = (20 * scale).toInt()
                    layoutParams.width = (20 * scale).toInt()
                    params.setMargins(0, 0,(12 * scale).toInt(), 0)
                    layoutParams = params
                }
                binding.titleAlert.textSize = 12F
                binding.iconEndAlert.apply {
                    val params = this.layoutParams as MarginLayoutParams
                    layoutParams.height = (20 * scale).toInt()
                    layoutParams.width = (20 * scale).toInt()
                    params.setMargins((12 * scale).toInt(), 0,0, 0)
                    layoutParams = params
                }
            }
            1 -> {
                binding.llAlert.setPadding((8 * scale).toInt(), (8 * scale).toInt(), (8 * scale).toInt(), (8 * scale).toInt())
                binding.iconStartAlert.apply {
                    val params = this.layoutParams as MarginLayoutParams
                    layoutParams.height = (16 * scale).toInt()
                    layoutParams.width = (16 * scale).toInt()
                    params.setMargins(0, 0,(8 * scale).toInt(), 0)
                    layoutParams = params
                }
                binding.titleAlert.textSize = 10F
                binding.iconEndAlert.apply {
                    val params = this.layoutParams as MarginLayoutParams
                    layoutParams.height = (16 * scale).toInt()
                    layoutParams.width = (16 * scale).toInt()
                    params.setMargins((8 * scale).toInt(), 0,0, 0)
                    layoutParams = params
                }
            }
        }
    }

    private fun setAlertStyle() {
        when (style) {
            0 -> {
                binding.contentAlert.visibility = GONE
                binding.titleAlert.setRegularFont()
            }
            1 -> {
                binding.contentAlert.visibility = VISIBLE
                binding.titleAlert.setSemiBoldFont()
            }
        }
    }

    @Suppress("DEPRECATION")
    @SuppressLint("UseCompatLoadingForColorStateLists")
    fun setAlertState(state: AlertState) {
        when (state) {
            AlertState.NORMAL -> {
                binding.llAlert.background = ContextCompat.getDrawable(context, R.drawable.bg_alert_normal_dash)
                binding.titleAlert.setTextColor(resources.getColor(R.color.shades5))
                binding.contentAlert.setTextColor(resources.getColor(R.color.shades5))
                ImageViewCompat.setImageTintList(binding.iconStartAlert, resources.getColorStateList(R.color.shades5))
                ImageViewCompat.setImageTintList(binding.iconEndAlert, resources.getColorStateList(R.color.shades5))
            }
            AlertState.WARNING -> {
                binding.llAlert.background = ContextCompat.getDrawable(context, R.drawable.bg_alert_warning_dash)
                binding.titleAlert.setTextColor(resources.getColor(R.color.box7))
                binding.contentAlert.setTextColor(resources.getColor(R.color.box7))
                ImageViewCompat.setImageTintList(binding.iconStartAlert, resources.getColorStateList(R.color.yellow6))
                ImageViewCompat.setImageTintList(binding.iconEndAlert, resources.getColorStateList(R.color.yellow6))
            }
            AlertState.DANGER -> {
                binding.llAlert.background = ContextCompat.getDrawable(context, R.drawable.bg_alert_danger_dash)
                binding.titleAlert.setTextColor(resources.getColor(R.color.interpack6))
                binding.contentAlert.setTextColor(resources.getColor(R.color.interpack6))
                ImageViewCompat.setImageTintList(binding.iconStartAlert, resources.getColorStateList(R.color.interpack6))
                ImageViewCompat.setImageTintList(binding.iconEndAlert, resources.getColorStateList(R.color.interpack6))
            }
            AlertState.BLOC_INFO -> {
                binding.llAlert.background = ContextCompat.getDrawable(context, R.drawable.bg_alert_bloc_info)
                binding.titleAlert.setTextColor(resources.getColor(R.color.white))
                binding.contentAlert.setTextColor(resources.getColor(R.color.white))
                ImageViewCompat.setImageTintList(binding.iconStartAlert, resources.getColorStateList(R.color.white))
                ImageViewCompat.setImageTintList(binding.iconEndAlert, resources.getColorStateList(R.color.white))
            }
        }
    }

    fun setTextTitle(text: String) {
        binding.titleAlert.text = text
    }

    fun setTextContent(text: String) {
        binding.contentAlert.text = text
    }

    fun setStartIcon(icon: Int = R.drawable.ics_f_warning_circle, isVisible: Boolean = true, isBlocInfo: Boolean = false) {
        if (isVisible) {
            binding.iconStartAlert.visibility = VISIBLE
        } else {
            binding.iconStartAlert.visibility = GONE
        }
        if (isBlocInfo) {
            binding.iconStartAlert.setImageResource(R.drawable.ics_f_announce)
        } else {
            binding.iconStartAlert.setImageResource(icon)
        }
    }

    fun setEndIcon(icon: Int = R.drawable.ic_o_chevron_right, isVisible: Boolean = true, isBlocInfo: Boolean = false) {
        if (isVisible) {
            binding.iconEndAlert.visibility = VISIBLE
        } else {
            binding.iconEndAlert.visibility = GONE
        }
        if (isBlocInfo) {
            val scale = resources.displayMetrics.density
            binding.iconEndAlert.apply {
                val params = this.layoutParams as LayoutParams
                setImageResource(R.drawable.ics_o_close)
                layoutParams.height = (20 * scale).toInt()
                layoutParams.width = (20 * scale).toInt()
                params.setMargins((12 * scale).toInt(), 0,0, 0)
                params.gravity = Gravity.TOP
                layoutParams = params
            }
        } else {
            binding.iconEndAlert.setImageResource(icon)
        }
    }

    fun setTextBoldSpannable(content: CharSequence,vararg targetString: String) {
        when (style) {
            0 -> binding.titleAlert.setBoldSpannable(content, *targetString)
            1 -> binding.contentAlert.setBoldSpannable(content, *targetString)
        }
    }

    fun setTextClickAndBoldSpannable(text: String,vararg clickableText: String, listener: (View) -> Unit) {
        when (style) {
            0 -> {
                binding.titleAlert.setBoldClickable(text, *clickableText, listener = listener)
                binding.titleAlert.movementMethod = LinkMovementMethod.getInstance()

            }
            1 -> {
                binding.contentAlert.setBoldClickable(text, *clickableText, listener = listener)
                binding.contentAlert.movementMethod = LinkMovementMethod.getInstance()

            }
        }
    }

    fun setEndIconClickListener(listener: (View) -> Unit) {
        binding.iconEndAlert.setOnClickListener {
            listener.invoke(it)
        }
    }
}