package com.lionparcel.commonandroid.label

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.core.widget.ImageViewCompat
import com.lionparcel.commonandroid.R

@Suppress("DEPRECATION")
@SuppressLint("UseCompatLoadingForDrawables")
class LPLabel : LinearLayout {

    private var textTitle: String
    private var backgroundType: Int
    private var backgroundProductType: Int
    private var backgroundBasicType: Int
    private var backgroundMembershipType : Int
    private var backgroundStyle: Int
    private var enableImageAfter: Boolean
    private var enableImageBefore: Boolean
    private var imageIconAfter: Drawable
    private var imageIconBefore: Drawable
    private var labelSize: Int

    private val llLabelParent: LinearLayout
    private val txtLabel: TextView
    private val imgIconLabelAfter: ImageView
    private val imgIconLabelBefore: ImageView

    private fun String?.setString() = this ?: ""

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_layout_label, this, true)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPLabel,
            0,
            0
        ).apply {
            try {
                textTitle = getString(R.styleable.LPLabel_textTitle).setString()
                backgroundType = getInt(R.styleable.LPLabel_labelType, 0)
                backgroundProductType = getInt(R.styleable.LPLabel_backgroundProductType, 0)
                backgroundBasicType = getInt(R.styleable.LPLabel_backgroundBasicType, 8)
                backgroundMembershipType = getInt(R.styleable.LPLabel_backgroundMembershipType, 0)
                backgroundStyle = getInt(R.styleable.LPLabel_labelBackgroundStyle, 0)
                enableImageAfter = getBoolean(R.styleable.LPLabel_enableIconAfter, false)
                enableImageBefore = getBoolean(R.styleable.LPLabel_enableIconBefore, false)
                imageIconAfter = getDrawable(R.styleable.LPLabel_imageIconAfter)
                    ?: resources.getDrawable(R.drawable.ics_f_notification)
                imageIconBefore = getDrawable(R.styleable.LPLabel_imageIconBefore)
                    ?: resources.getDrawable(R.drawable.ics_f_notification)
                labelSize = getInt(R.styleable.LPLabel_labelSize, 0)
            } finally {
                recycle()
            }
        }

        llLabelParent = findViewById(R.id.ll_label_parent)
        txtLabel = findViewById(R.id.txt_label)
        imgIconLabelAfter = findViewById(R.id.img_icon_label_after)
        imgIconLabelBefore = findViewById(R.id.img_icon_label_before)

        // set text label title
        setTextTitle()
        // set label background
        setBackgroundType()
        // set image visibility
        enableIconAfter()
        enableIconBefore()
        // set image drawable
        setImageIconAfter()
        setImageIconBefore()
        // set label size
        setLabelSize()
    }

    private fun setBackgroundType() {
        when (backgroundType) {
            0 -> setBackgroundBasicType()
            1 -> setBackgroundProductType()
            2 -> setBackgroundMembershipType()
        }
    }

    @SuppressLint("UseCompatLoadingForColorStateLists")
    private fun setBackgroundProductType() {
        when (backgroundProductType) {
            0 -> {
                llLabelParent.background =
                    resources.getDrawable(R.drawable.bg_selector_label_onepack)
                when (backgroundStyle) {
                    0 -> {
                        txtLabel.setTextColor(resources.getColor(R.color.white))
                        ImageViewCompat.setImageTintList(
                            imgIconLabelAfter,
                            resources.getColorStateList(R.color.white)
                        )
                    }
                    1 -> {
                        txtLabel.setTextColor(resources.getColor(R.color.purple4))
                        ImageViewCompat.setImageTintList(
                            imgIconLabelAfter,
                            resources.getColorStateList(R.color.purple4)
                        )
                    }
                }
            }
            1 -> {
                llLabelParent.background =
                    resources.getDrawable(R.drawable.bg_selector_label_regpack)
                txtLabel.setTextColor(resources.getColor(R.color.blue4))
                ImageViewCompat.setImageTintList(
                    imgIconLabelAfter,
                    resources.getColorStateList(R.color.blue4)
                )
            }
            2 -> {
                llLabelParent.background =
                    resources.getDrawable(R.drawable.bg_selector_label_jagopack)
                txtLabel.setTextColor(resources.getColor(R.color.green6))
                ImageViewCompat.setImageTintList(
                    imgIconLabelAfter,
                    resources.getColorStateList(R.color.green6)
                )
            }
            3 -> {
                llLabelParent.background =
                    resources.getDrawable(R.drawable.bg_selector_label_docupack)
                txtLabel.setTextColor(resources.getColor(R.color.brown6))
                ImageViewCompat.setImageTintList(
                    imgIconLabelAfter,
                    resources.getColorStateList(R.color.brown6)
                )
            }
            4 -> {
                llLabelParent.background =
                    resources.getDrawable(R.drawable.bg_selector_label_sameday)
                txtLabel.setTextColor(resources.getColor(R.color.interpack4))
                ImageViewCompat.setImageTintList(
                    imgIconLabelAfter,
                    resources.getColorStateList(R.color.interpack4)
                )
            }
            5 -> {
                llLabelParent.background =
                    resources.getDrawable(R.drawable.bg_selector_label_deliv_order)
                txtLabel.setTextColor(resources.getColor(R.color.aqua5))
                ImageViewCompat.setImageTintList(
                    imgIconLabelAfter,
                    resources.getColorStateList(R.color.aqua5)
                )
            }
            6 -> {
                llLabelParent.background =
                    resources.getDrawable(R.drawable.bg_selector_label_bundle)
                txtLabel.setTextColor(resources.getColor(R.color.shades6))
                ImageViewCompat.setImageTintList(
                    imgIconLabelAfter,
                    resources.getColorStateList(R.color.shades6)
                )
            }
            7 -> {
                llLabelParent.background =
                    resources.getDrawable(R.drawable.bg_selector_label_big_pack)
                txtLabel.setTextColor(resources.getColor(R.color.orange5))
                ImageViewCompat.setImageTintList(
                    imgIconLabelAfter,
                    resources.getColorStateList(R.color.orange5)
                )
            }
            8 -> {
                llLabelParent.background =
                    resources.getDrawable(R.drawable.bg_selector_label_inactive)
                when (backgroundStyle) {
                    0 -> {
                        txtLabel.setTextColor(resources.getColor(R.color.white))
                        ImageViewCompat.setImageTintList(
                            imgIconLabelAfter,
                            resources.getColorStateList(R.color.white)
                        )
                    }
                    1 -> {
                        txtLabel.setTextColor(resources.getColor(R.color.shades3))
                        ImageViewCompat.setImageTintList(
                            imgIconLabelAfter,
                            resources.getColorStateList(R.color.shades3)
                        )
                    }
                }
            }
        }
        when (backgroundStyle) {
            0 -> llLabelParent.isSelected = false
            1 -> llLabelParent.isSelected = true
        }
    }

    @SuppressLint("UseCompatLoadingForColorStateLists")
    private fun setBackgroundBasicType() {
        val scale = resources.displayMetrics.density
        val cornerRadius = (4 * scale)
        val strokeWidth = (1 * scale)
        val backgroundPadding = (1 * scale).toInt()
        when (backgroundBasicType) {
            0 -> {
                when (backgroundStyle) {
                    0 -> {
                        llLabelParent.background = resources.getDrawable(R.drawable.bg_selector_label_basic)
                        ViewCompat.setBackgroundTintList(llLabelParent, resources.getColorStateList(R.color.blue3))
                        txtLabel.setTextColor(resources.getColorStateList(R.color.white))
                        ImageViewCompat.setImageTintList(
                            imgIconLabelAfter,
                            resources.getColorStateList(R.color.white)
                        )
                    }
                    1 -> {
                        txtLabel.setTextColor(resources.getColorStateList(R.color.blue3))
                        ImageViewCompat.setImageTintList(
                            imgIconLabelBefore,
                            resources.getColorStateList(R.color.blue3)
                        )
                        val shapeDrawable = ShapeDrawable().apply {
                            shape = RoundRectShape(floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius), null, null)
                            paint.style = Paint.Style.STROKE
                            paint.strokeWidth = strokeWidth
                            paint.color = resources.getColor(R.color.blue3)
                        }
                        val shapeFill = ShapeDrawable().apply {
                            shape = RoundRectShape(floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius), null, null)
                            setPadding(backgroundPadding, backgroundPadding, backgroundPadding, backgroundPadding)
                            paint.style = Paint.Style.FILL_AND_STROKE
                            paint.color = resources.getColor(R.color.white)
                        }
                        val composite = LayerDrawable(arrayOf(shapeFill, shapeDrawable))
                        llLabelParent.background = composite
                    }
                }
            }
            1 -> {
                when (backgroundStyle) {
                    0 -> {
                        llLabelParent.background = resources.getDrawable(R.drawable.bg_selector_label_basic)
                        ViewCompat.setBackgroundTintList(llLabelParent, resources.getColorStateList(R.color.yellow6))
                        txtLabel.setTextColor(resources.getColorStateList(R.color.white))
                        ImageViewCompat.setImageTintList(
                            imgIconLabelAfter,
                            resources.getColorStateList(R.color.white)
                        )
                    }
                    1 -> {
                        txtLabel.setTextColor(resources.getColorStateList(R.color.yellow6))
                        ImageViewCompat.setImageTintList(
                            imgIconLabelBefore,
                            resources.getColorStateList(R.color.yellow6)
                        )
                        val shapeDrawable = ShapeDrawable().apply {
                            shape = RoundRectShape(floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius), null, null)
                            paint.style = Paint.Style.STROKE
                            paint.strokeWidth = strokeWidth
                            paint.color = resources.getColor(R.color.yellow6)
                        }
                        val shapeFill = ShapeDrawable().apply {
                            shape = RoundRectShape(floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius), null, null)
                            setPadding(backgroundPadding, backgroundPadding, backgroundPadding, backgroundPadding)
                            paint.style = Paint.Style.FILL_AND_STROKE
                            paint.color = resources.getColor(R.color.white)
                        }
                        val composite = LayerDrawable(arrayOf(shapeFill, shapeDrawable))
                        llLabelParent.background = composite
                    }
                }
            }
            2 -> {
                when (backgroundStyle) {
                    0 -> {
                        llLabelParent.background = resources.getDrawable(R.drawable.bg_selector_label_basic)
                        ViewCompat.setBackgroundTintList(llLabelParent, resources.getColorStateList(R.color.yellow7))
                        txtLabel.setTextColor(resources.getColorStateList(R.color.white))
                        ImageViewCompat.setImageTintList(
                            imgIconLabelAfter,
                            resources.getColorStateList(R.color.white)
                        )
                    }
                    1 -> {
                        txtLabel.setTextColor(resources.getColorStateList(R.color.yellow7))
                        ImageViewCompat.setImageTintList(
                            imgIconLabelBefore,
                            resources.getColorStateList(R.color.yellow7)
                        )
                        val shapeDrawable = ShapeDrawable().apply {
                            shape = RoundRectShape(floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius), null, null)
                            paint.style = Paint.Style.STROKE
                            paint.strokeWidth = strokeWidth
                            paint.color = resources.getColor(R.color.yellow7)
                        }
                        val shapeFill = ShapeDrawable().apply {
                            shape = RoundRectShape(floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius), null, null)
                            setPadding(backgroundPadding, backgroundPadding, backgroundPadding, backgroundPadding)
                            paint.style = Paint.Style.FILL_AND_STROKE
                            paint.color = resources.getColor(R.color.white)
                        }
                        val composite = LayerDrawable(arrayOf(shapeFill, shapeDrawable))
                        llLabelParent.background = composite
                    }
                }
            }
            3 -> {
                when (backgroundStyle) {
                    0 -> {
                        llLabelParent.background = resources.getDrawable(R.drawable.bg_selector_label_basic)
                        ViewCompat.setBackgroundTintList(llLabelParent, resources.getColorStateList(R.color.green5))
                        txtLabel.setTextColor(resources.getColorStateList(R.color.white))
                        ImageViewCompat.setImageTintList(
                            imgIconLabelAfter,
                            resources.getColorStateList(R.color.white)
                        )
                    }
                    1 -> {
                        txtLabel.setTextColor(resources.getColorStateList(R.color.green5))
                        ImageViewCompat.setImageTintList(
                            imgIconLabelBefore,
                            resources.getColorStateList(R.color.green5)
                        )
                        val shapeDrawable = ShapeDrawable().apply {
                            shape = RoundRectShape(floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius), null, null)
                            paint.style = Paint.Style.STROKE
                            paint.strokeWidth = strokeWidth
                            paint.color = resources.getColor(R.color.green5)
                        }
                        val shapeFill = ShapeDrawable().apply {
                            shape = RoundRectShape(floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius), null, null)
                            setPadding(backgroundPadding, backgroundPadding, backgroundPadding, backgroundPadding)
                            paint.style = Paint.Style.FILL_AND_STROKE
                            paint.color = resources.getColor(R.color.white)
                        }
                        val composite = LayerDrawable(arrayOf(shapeFill, shapeDrawable))
                        llLabelParent.background = composite
                    }
                }
            }
            4 -> {
                when (backgroundStyle) {
                    0 -> {
                        llLabelParent.background = resources.getDrawable(R.drawable.bg_selector_label_basic)
                        ViewCompat.setBackgroundTintList(llLabelParent, resources.getColorStateList(R.color.red3))
                        txtLabel.setTextColor(resources.getColorStateList(R.color.white))
                        ImageViewCompat.setImageTintList(
                            imgIconLabelAfter,
                            resources.getColorStateList(R.color.white)
                        )
                    }
                    1 -> {
                        txtLabel.setTextColor(resources.getColorStateList(R.color.red3))
                        ImageViewCompat.setImageTintList(
                            imgIconLabelBefore,
                            resources.getColorStateList(R.color.red3)
                        )
                        val shapeDrawable = ShapeDrawable().apply {
                            shape = RoundRectShape(floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius), null, null)
                            paint.style = Paint.Style.STROKE
                            paint.strokeWidth = strokeWidth
                            paint.color = resources.getColor(R.color.red3)
                        }
                        val shapeFill = ShapeDrawable().apply {
                            shape = RoundRectShape(floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius), null, null)
                            setPadding(backgroundPadding, backgroundPadding, backgroundPadding, backgroundPadding)
                            paint.style = Paint.Style.FILL_AND_STROKE
                            paint.color = resources.getColor(R.color.white)
                        }
                        val composite = LayerDrawable(arrayOf(shapeFill, shapeDrawable))
                        llLabelParent.background = composite
                    }
                }
            }
            5 -> {
                when (backgroundStyle) {
                    0 -> {
                        llLabelParent.background = resources.getDrawable(R.drawable.bg_selector_label_basic)
                        ViewCompat.setBackgroundTintList(llLabelParent, resources.getColorStateList(R.color.interpack7))
                        txtLabel.setTextColor(resources.getColorStateList(R.color.white))
                        ImageViewCompat.setImageTintList(
                            imgIconLabelAfter,
                            resources.getColorStateList(R.color.white)
                        )
                    }
                    1 -> {
                        txtLabel.setTextColor(resources.getColorStateList(R.color.interpack7))
                        ImageViewCompat.setImageTintList(
                            imgIconLabelBefore,
                            resources.getColorStateList(R.color.interpack7)
                        )
                        val shapeDrawable = ShapeDrawable().apply {
                            shape = RoundRectShape(floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius), null, null)
                            paint.style = Paint.Style.STROKE
                            paint.strokeWidth = strokeWidth
                            paint.color = resources.getColor(R.color.interpack7)
                        }
                        val shapeFill = ShapeDrawable().apply {
                            shape = RoundRectShape(floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius), null, null)
                            setPadding(backgroundPadding, backgroundPadding, backgroundPadding, backgroundPadding)
                            paint.style = Paint.Style.FILL_AND_STROKE
                            paint.color = resources.getColor(R.color.white)
                        }
                        val composite = LayerDrawable(arrayOf(shapeFill, shapeDrawable))
                        llLabelParent.background = composite
                    }
                }
            }
            6 -> {
                when (backgroundStyle) {
                    0 -> {
                        llLabelParent.background = resources.getDrawable(R.drawable.bg_selector_label_basic)
                        ViewCompat.setBackgroundTintList(llLabelParent, resources.getColorStateList(R.color.orange5))
                        txtLabel.setTextColor(resources.getColorStateList(R.color.white))
                        ImageViewCompat.setImageTintList(
                            imgIconLabelAfter,
                            resources.getColorStateList(R.color.white)
                        )
                    }
                    1 -> {
                        txtLabel.setTextColor(resources.getColorStateList(R.color.orange5))
                        ImageViewCompat.setImageTintList(
                            imgIconLabelBefore,
                            resources.getColorStateList(R.color.orange5)
                        )
                        val shapeDrawable = ShapeDrawable().apply {
                            shape = RoundRectShape(floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius), null, null)
                            paint.style = Paint.Style.STROKE
                            paint.strokeWidth = strokeWidth
                            paint.color = resources.getColor(R.color.orange5)
                        }
                        val shapeFill = ShapeDrawable().apply {
                            shape = RoundRectShape(floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius), null, null)
                            setPadding(backgroundPadding, backgroundPadding, backgroundPadding, backgroundPadding)
                            paint.style = Paint.Style.FILL_AND_STROKE
                            paint.color = resources.getColor(R.color.white)
                        }
                        val composite = LayerDrawable(arrayOf(shapeFill, shapeDrawable))
                        llLabelParent.background = composite
                    }
                }
            }
            7 -> {
                when (backgroundStyle) {
                    0 -> {
                        llLabelParent.background = resources.getDrawable(R.drawable.bg_selector_label_basic)
                        ViewCompat.setBackgroundTintList(llLabelParent, resources.getColorStateList(R.color.orange7))
                        txtLabel.setTextColor(resources.getColorStateList(R.color.white))
                        ImageViewCompat.setImageTintList(
                            imgIconLabelAfter,
                            resources.getColorStateList(R.color.white)
                        )
                    }
                    1 -> {
                        txtLabel.setTextColor(resources.getColorStateList(R.color.orange7))
                        ImageViewCompat.setImageTintList(
                            imgIconLabelBefore,
                            resources.getColorStateList(R.color.orange7)
                        )
                        val shapeDrawable = ShapeDrawable().apply {
                            shape = RoundRectShape(floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius), null, null)
                            paint.style = Paint.Style.STROKE
                            paint.strokeWidth = strokeWidth
                            paint.color = resources.getColor(R.color.orange7)
                        }
                        val shapeFill = ShapeDrawable().apply {
                            shape = RoundRectShape(floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius), null, null)
                            setPadding(backgroundPadding, backgroundPadding, backgroundPadding, backgroundPadding)
                            paint.style = Paint.Style.FILL_AND_STROKE
                            paint.color = resources.getColor(R.color.white)
                        }
                        val composite = LayerDrawable(arrayOf(shapeFill, shapeDrawable))
                        llLabelParent.background = composite
                    }
                }
            }
            8 -> {
                when (backgroundStyle) {
                    0 -> {
                        llLabelParent.background = resources.getDrawable(R.drawable.bg_selector_label_basic)
                        ViewCompat.setBackgroundTintList(llLabelParent, resources.getColorStateList(R.color.shades3))
                        txtLabel.setTextColor(resources.getColorStateList(R.color.white))
                        ImageViewCompat.setImageTintList(
                            imgIconLabelAfter,
                            resources.getColorStateList(R.color.white)
                        )
                    }
                    1 -> {
                        txtLabel.setTextColor(resources.getColorStateList(R.color.shades3))
                        ImageViewCompat.setImageTintList(
                            imgIconLabelBefore,
                            resources.getColorStateList(R.color.shades3)
                        )
                        val shapeDrawable = ShapeDrawable().apply {
                            shape = RoundRectShape(floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius), null, null)
                            paint.style = Paint.Style.STROKE
                            paint.strokeWidth = strokeWidth
                            paint.color = resources.getColor(R.color.shades3)
                        }
                        val shapeFill = ShapeDrawable().apply {
                            shape = RoundRectShape(floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius), null, null)
                            setPadding(backgroundPadding, backgroundPadding, backgroundPadding, backgroundPadding)
                            paint.style = Paint.Style.FILL_AND_STROKE
                            paint.color = resources.getColor(R.color.white)
                        }
                        val composite = LayerDrawable(arrayOf(shapeFill, shapeDrawable))
                        llLabelParent.background = composite
                    }
                }
            }
        }
    }

    @SuppressLint("UseCompatLoadingForColorStateLists")
    private fun setBackgroundMembershipType() {
        when (backgroundMembershipType) {
            0 -> {
                llLabelParent.background = resources.getDrawable(R.drawable.bg_selector_label_membership)
                ViewCompat.setBackgroundTintList(llLabelParent, resources.getColorStateList(R.color.orange5))
                txtLabel.setTextColor(resources.getColorStateList(R.color.white))
                ImageViewCompat.setImageTintList(
                    imgIconLabelBefore,
                    resources.getColorStateList(R.color.white)
                )
            }
            1 -> {
                llLabelParent.background = resources.getDrawable(R.drawable.bg_selector_label_membership)
                ViewCompat.setBackgroundTintList(llLabelParent, resources.getColorStateList(R.color.shades5))
                txtLabel.setTextColor(resources.getColorStateList(R.color.white))
                ImageViewCompat.setImageTintList(
                    imgIconLabelBefore,
                    resources.getColorStateList(R.color.white)
                )
            }
            2 -> {
                llLabelParent.background = resources.getDrawable(R.drawable.bg_selector_label_membership)
                ViewCompat.setBackgroundTintList(llLabelParent, resources.getColorStateList(R.color.yellow6))
                txtLabel.setTextColor(resources.getColorStateList(R.color.white))
                ImageViewCompat.setImageTintList(
                    imgIconLabelBefore,
                    resources.getColorStateList(R.color.white)
                )
            }
        }
    }

    private fun setLabelSize() {
        val scale = resources.displayMetrics.density
        when (labelSize) {
            0 -> {
                txtLabel.setTextSize(
                    TypedValue.COMPLEX_UNIT_SP,
                    resources.getDimension(R.dimen.sp_14) / scale
                )
                txtLabel.layoutParams.height = (21 * scale).toInt()
                when (backgroundType) {
                    0 -> {
                        llLabelParent.setPadding(
                            (8 * scale).toInt(),
                            (1 * scale).toInt(),
                            (8 * scale).toInt(),
                            (1 * scale).toInt()
                        )
                        imgIconLabelBefore.layoutParams.height = (16 * scale).toInt()
                        imgIconLabelBefore.layoutParams.width = (16 * scale).toInt()
                        imgIconLabelBefore.setPadding((2 * scale).toInt(),(1 * scale).toInt(),(2 * scale).toInt(),(1 * scale).toInt())
                    }
                    1 -> {
                        llLabelParent.setPadding(
                            (20 * scale).toInt(),
                            (1 * scale).toInt(),
                            (20 * scale).toInt(),
                            (1 * scale).toInt()
                        )
                        imgIconLabelAfter.layoutParams.height = (16 * scale).toInt()
                        imgIconLabelAfter.layoutParams.width = (16 * scale).toInt()
                        imgIconLabelAfter.setPadding((2 * scale).toInt(),(1 * scale).toInt(),(2 * scale).toInt(),(1 * scale).toInt())
                    }
                    2 -> {
                        llLabelParent.setPadding(
                            (20 * scale).toInt(),
                            (1 * scale).toInt(),
                            (20 * scale).toInt(),
                            (1 * scale).toInt()
                        )
                        imgIconLabelAfter.layoutParams.height = (16 * scale).toInt()
                        imgIconLabelAfter.layoutParams.width = (16 * scale).toInt()
                        imgIconLabelAfter.setPadding((5 * scale).toInt(),(3 * scale).toInt(),(5 * scale).toInt(),(3 * scale).toInt())
                    }
                }
                requestLayout()
            }
            1 -> {
                txtLabel.setTextSize(
                    TypedValue.COMPLEX_UNIT_SP,
                    resources.getDimension(R.dimen.sp_12) / scale
                )
                txtLabel.layoutParams.height = (18 * scale).toInt()
                when (backgroundType){
                    0 -> {
                        llLabelParent.setPadding(
                            (4 * scale).toInt(),
                            (1 * scale).toInt(),
                            (4 * scale).toInt(),
                            (1 * scale).toInt()
                        )
                        imgIconLabelBefore.layoutParams.height = (12 * scale).toInt()
                        imgIconLabelBefore.layoutParams.width = (12 * scale).toInt()
                        imgIconLabelBefore.setPadding((1.5 * scale).toInt(),(1 * scale).toInt(),(1.5 * scale).toInt(),(1 * scale).toInt())
                    }
                    1 -> {
                        llLabelParent.setPadding(
                            (16 * scale).toInt(),
                            (1 * scale).toInt(),
                            (16 * scale).toInt(),
                            (1 * scale).toInt()
                        )
                        imgIconLabelAfter.layoutParams.height = (12 * scale).toInt()
                        imgIconLabelAfter.layoutParams.width = (12 * scale).toInt()
                        imgIconLabelAfter.setPadding((1.5 * scale).toInt(),(1 * scale).toInt(),(1.5 * scale).toInt(),(1 * scale).toInt())
                    }
                    2 -> {
                        llLabelParent.setPadding(
                            (16 * scale).toInt(),
                            (1 * scale).toInt(),
                            (16 * scale).toInt(),
                            (1 * scale).toInt()
                        )
                        imgIconLabelAfter.layoutParams.height = (12 * scale).toInt()
                        imgIconLabelAfter.layoutParams.width = (12 * scale).toInt()
                        imgIconLabelAfter.setPadding((4 * scale).toInt(),(2.5 * scale).toInt(),(4 * scale).toInt(),(2.5 * scale).toInt())
                    }
                }
                requestLayout()
            }
            2 -> {
                txtLabel.setTextSize(
                    TypedValue.COMPLEX_UNIT_SP,
                    resources.getDimension(R.dimen.sp_10) / scale
                )
                txtLabel.layoutParams.height = (15 * scale).toInt()
                when (backgroundType) {
                    0 -> {
                        llLabelParent.setPadding(
                            (4 * scale).toInt(),
                            (1 * scale).toInt(),
                            (4 * scale).toInt(),
                            (1 * scale).toInt()
                        )
                        imgIconLabelBefore.layoutParams.height = (12 * scale).toInt()
                        imgIconLabelBefore.layoutParams.width = (12 * scale).toInt()
                        imgIconLabelBefore.setPadding((1.5 * scale).toInt(),(1 * scale).toInt(),(1.5 * scale).toInt(),(1 * scale).toInt())
                    }
                    1 -> {
                        llLabelParent.setPadding(
                            (12 * scale).toInt(),
                            (1 * scale).toInt(),
                            (12 * scale).toInt(),
                            (1 * scale).toInt()
                        )
                        imgIconLabelAfter.layoutParams.height = (12 * scale).toInt()
                        imgIconLabelAfter.layoutParams.width = (12 * scale).toInt()
                        imgIconLabelAfter.setPadding((1.5 * scale).toInt(),(1 * scale).toInt(),(1.5 * scale).toInt(),(1 * scale).toInt())
                    }
                    2 -> {
                        llLabelParent.setPadding(
                            (12 * scale).toInt(),
                            (1 * scale).toInt(),
                            (12 * scale).toInt(),
                            (1 * scale).toInt()
                        )
                        imgIconLabelAfter.layoutParams.height = (12 * scale).toInt()
                        imgIconLabelAfter.layoutParams.width = (12 * scale).toInt()
                        imgIconLabelAfter.setPadding((4 * scale).toInt(),(2.5 * scale).toInt(),(4 * scale).toInt(),(2.5 * scale).toInt())
                    }
                }
                requestLayout()
            }
            3 -> {
                txtLabel.setTextSize(
                    TypedValue.COMPLEX_UNIT_SP,
                    resources.getDimension(R.dimen.sp_10) / scale
                )
                txtLabel.layoutParams.height = (15 * scale).toInt()
                when (backgroundType) {
                    0 -> {
                        llLabelParent.setPadding(
                            (4 * scale).toInt(),
                            (1 * scale).toInt(),
                            (4 * scale).toInt(),
                            (1 * scale).toInt()
                        )
                        imgIconLabelBefore.layoutParams.height = (12 * scale).toInt()
                        imgIconLabelBefore.layoutParams.width = (12 * scale).toInt()
                        imgIconLabelBefore.setPadding((1.5 * scale).toInt(),(1 * scale).toInt(),(1.5 * scale).toInt(),(1 * scale).toInt())
                    }
                    1 -> {
                        llLabelParent.setPadding(
                            (4 * scale).toInt(),
                            (2.5 * scale).toInt(),
                            (4 * scale).toInt(),
                            (2.5 * scale).toInt()
                        )
                        imgIconLabelAfter.layoutParams.height = (12 * scale).toInt()
                        imgIconLabelAfter.layoutParams.width = (12 * scale).toInt()
                        imgIconLabelAfter.setPadding((1.5 * scale).toInt(),(1 * scale).toInt(),(1.5 * scale).toInt(),(1 * scale).toInt())
                    }
                    2-> {
                        llLabelParent.setPadding(
                            (4 * scale).toInt(),
                            (2.5 * scale).toInt(),
                            (4 * scale).toInt(),
                            (2.5 * scale).toInt()
                        )
                        imgIconLabelAfter.layoutParams.height = (12 * scale).toInt()
                        imgIconLabelAfter.layoutParams.width = (12 * scale).toInt()
                        imgIconLabelAfter.setPadding((4 * scale).toInt(),(2.5 * scale).toInt(),(4 * scale).toInt(),(2.5 * scale).toInt())
                    }
                }
                requestLayout()
            }
        }
    }

    fun setTextTitle(title: String? = null) {
        txtLabel.text = title ?: this.textTitle
    }

    fun enableIconAfter(enable: Boolean? = null) {
        imgIconLabelAfter.isVisible = enable ?: this.enableImageAfter
    }

    fun enableIconBefore(enable: Boolean? = null) {
        imgIconLabelBefore.isVisible = enable ?: this.enableImageBefore
    }

    fun setImageIconAfter(image: Int? = null) {
        if (image != null) {
            imgIconLabelAfter.setImageResource(image)
        } else {
            imgIconLabelAfter.setImageDrawable(this.imageIconAfter)
        }
    }

    fun setImageIconBefore(image: Int? = null) {
        if (image != null) {
            imgIconLabelBefore.setImageResource(image)
        } else {
            imgIconLabelBefore.setImageDrawable(this.imageIconBefore)
        }
    }
}
