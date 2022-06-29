package com.lionparcel.commonandroid.emptystate

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.lionparcel.commonandroid.R

class LPEmptyState : LinearLayout{

    private var showIllustration : Boolean
    private var imageIllustration : Drawable
    private var showTitle : Boolean
    private var textTitle : String
    private var textBody : String
    private var showPrimaryButton : Boolean
    private var textPrimaryButton : String
    private var showSecondaryButton : Boolean
    private var textSecondaryButton : String

    private val llEmptyState : LinearLayout
    private val ivEmptyStateIllustration : ImageView
    private val txtEmptyStateTitle : TextView
    private val txtEmptyStateBody : TextView
    private val btnEmptyStatePrimary : Button
    private val btnEmptyStateSecondary : Button

    private fun String?.setString() = this?: ""

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_layout_empty_state_view, this, true)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPEmptyState,
            0,
            0
        ).apply {
            try {
                showIllustration = getBoolean(R.styleable.LPEmptyState_showIllustration, false)
                imageIllustration = getDrawable(R.styleable.LPEmptyState_imageIllustration)?: resources.getDrawable(R.drawable.ill_spot_search)
                showTitle = getBoolean(R.styleable.LPEmptyState_showTitleText, false)
                textTitle = getString(R.styleable.LPEmptyState_textTitle).setString()
                textBody = getString(R.styleable.LPEmptyState_textBody).setString()
                showPrimaryButton = getBoolean(R.styleable.LPEmptyState_showPrimaryButton, false)
                textPrimaryButton = getString(R.styleable.LPEmptyState_textPrimaryButton).setString()
                showSecondaryButton = getBoolean(R.styleable.LPEmptyState_showSecondaryButton, false)
                textSecondaryButton = getString(R.styleable.LPEmptyState_textSecondaryButton).setString()
            } finally {
                recycle()
            }
        }

        llEmptyState = findViewById(R.id.ll_empty_state)
        ivEmptyStateIllustration = findViewById(R.id.iv_empty_state_illustration)
        txtEmptyStateTitle = findViewById(R.id.tv_empty_state_title)
        txtEmptyStateBody = findViewById(R.id.tv_empty_state_body)
        btnEmptyStatePrimary = findViewById(R.id.btn_empty_state_primary)
        btnEmptyStateSecondary = findViewById(R.id.btn_empty_state_secondary)

        showIllustration()

        setIllustrationImage()

        showTitle()

        setTextTitle()

        setTextBody()

        showPrimaryButton()

        setTextPrimaryButton()

        showSecondaryButton()

        setTextSecondaryButton()
    }

    fun showIllustration(showImage : Boolean? = null) {
        ivEmptyStateIllustration.isVisible = showImage?: this.showIllustration
    }

    fun setIllustrationImage(image : Int? = null) {
        if (image != null){
            ivEmptyStateIllustration.setImageResource(image)
        } else {
            ivEmptyStateIllustration.setImageDrawable(this.imageIllustration)
        }
    }

    fun showTitle(showTitle : Boolean? = null) {
        txtEmptyStateTitle.isVisible = showTitle?: this.showTitle
    }

    fun setTextTitle(title : String? = null) {
        txtEmptyStateTitle.text = title?: this.textTitle
    }

    fun setTextBody(body : String? = null) {
        txtEmptyStateBody.text = body?: this.textBody
    }

    fun showPrimaryButton(showButton : Boolean? = null) {
        btnEmptyStatePrimary.isVisible = showButton?: this.showPrimaryButton
    }

    fun setTextPrimaryButton(text : String? = null) {
        btnEmptyStatePrimary.text = text?: this.textPrimaryButton
    }

    fun primaryButtonListener(listener : ((View) -> Unit)){
        btnEmptyStatePrimary.setOnClickListener {
            listener.invoke(it)
        }
    }

    fun showSecondaryButton(showButton : Boolean? = null) {
        btnEmptyStateSecondary.isVisible = showButton?: this.showSecondaryButton
    }

    fun setTextSecondaryButton(text : String? = null) {
        btnEmptyStateSecondary.text = text?: this.textSecondaryButton
    }

    fun secondaryButtonListener(listener : ((View) -> Unit)){
        btnEmptyStateSecondary.setOnClickListener {
            listener.invoke(it)
        }
    }
}