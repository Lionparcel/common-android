@file:Suppress("DEPRECATION")

package com.lionparcel.commonandroid.card

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.lionparcel.commonandroid.R

class LPCard : LinearLayout {

    private var cardStyle: Int
    private var cardTitle: String
    private var cardSubtitle: String
    private var cardContent: String
    private var btnText: String
    private var solidStyleColor : Int
    private var bannerStyleBackground : Drawable

    private val llCard: LinearLayout
    private val txtCardTitle: TextView
    private val txtCardSubtitle: TextView
    private val txtCardContent: TextView
    private val btnCardRed: Button
    private val vwCardBottomBorder: View

    private fun String?.setString() = this ?: ""

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_layout_card_view, this, true)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPCard,
            0,
            0
        ).apply {
            try {
                cardStyle = getInt(R.styleable.LPCard_cardStyle, 0)
                cardTitle = getString(R.styleable.LPCard_cardTitle).setString()
                cardSubtitle = getString(R.styleable.LPCard_cardSubtitle).setString()
                cardContent = getString(R.styleable.LPCard_cardsContent).setString()
                btnText = getString(R.styleable.LPCard_buttonText).setString()
                solidStyleColor = getColor(R.styleable.LPCard_solidStyleColor, Color.WHITE)
                bannerStyleBackground = getDrawable(R.styleable.LPCard_bannerStyleBackground)?: resources.getDrawable(R.drawable.bg_card_banner)
            } finally {
                recycle()
            }
        }

        llCard = findViewById(R.id.ll_card_parent)
        txtCardTitle = findViewById(R.id.txt_card_title)
        txtCardSubtitle = findViewById(R.id.txt_card_subtitle)
        txtCardContent = findViewById(R.id.txt_card_content)
        btnCardRed = findViewById(R.id.btn_card_red)
        vwCardBottomBorder = findViewById(R.id.vw_card_border_bottom)

        setCardStyle()
        setCardTitle()
        setCardSubtitle()
        setCardContent()
        setButtonText()
    }

    private fun setCardStyle(color : Int? = null, bannerBg : Int? = null) {
        when (this.cardStyle) {
            0 -> {
                llCard.background = ContextCompat.getDrawable(context, R.drawable.bg_card_shadow)
            }
            1 -> {
                if (color != null) {
                    llCard.background.setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.ADD)
                } else {
                    llCard.background.setColorFilter(this.solidStyleColor, PorterDuff.Mode.ADD)
                }
            }
            2 -> {
                if (bannerBg != null) {
                    llCard.background = ContextCompat.getDrawable(context, bannerBg)
                } else {
                    llCard.background = this.bannerStyleBackground
                }
            }
            3 -> {
                llCard.background = ContextCompat.getDrawable(context, R.drawable.bg_card_border)
            }
            4 -> {
                llCard.background = ContextCompat.getDrawable(context, R.color.transparent)
                vwCardBottomBorder.isVisible = true
            }
        }
    }

    fun setCardTitle(title: String? = null) {
        txtCardTitle.text = title ?: this.cardTitle
    }

    fun setCardSubtitle(subtitle: String? = null) {
        txtCardSubtitle.text = subtitle ?: this.cardSubtitle
    }

    fun setCardContent(content: String? = null) {
        txtCardContent.text = content ?: this.cardContent
    }

    fun setButtonText(text: String? = null) {
        btnCardRed.text = text ?: this.btnText
    }

    fun buttonListener(listener : ((View) -> Unit)){
        btnCardRed.setOnClickListener {
            listener.invoke(it)
        }
    }

    fun solidStyleColor(color: Int){
        setCardStyle(color = color)
    }

    fun bannerStyleBackground(drawable: Int) {
        setCardStyle(bannerBg = drawable)
    }
}