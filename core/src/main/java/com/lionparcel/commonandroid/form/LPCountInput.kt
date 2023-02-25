package com.lionparcel.commonandroid.form

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import com.lionparcel.commonandroid.R

class LPCountInput : LinearLayout {

    private var imgBtnCountInputMin: ImageButton
    private var txtCountInputValue: EditText
    private var imgBtnCountInputPlus: ImageButton
    private var minQtyValue = 0
    private var maxQtyValue = 99
    private var qtyValue = minQtyValue
    private var withDivider = true
    private var countEditable = true
    private var enabled = true

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_count_input_view, this, true)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPCountInput,
            0,
            0
        ).apply {
            try {
                minQtyValue = getInt(R.styleable.LPCountInput_minQtyValue, minQtyValue)
                maxQtyValue = getInt(R.styleable.LPCountInput_maxQtyValue, maxQtyValue)
                withDivider = getBoolean(R.styleable.LPCountInput_withDivider, withDivider)
                countEditable = getBoolean(R.styleable.LPCountInput_countEditable, countEditable)
            } finally {
                recycle()
            }
        }
        imgBtnCountInputMin = findViewById(R.id.imgBtnCountInputMin)
        txtCountInputValue = findViewById(R.id.txtCountInputValue)
        imgBtnCountInputPlus = findViewById(R.id.imgBtnCountInputPlus)

        initImageButton()
        initTxtCountInput()
        setEnablePlusMinusButton(this.qtyValue)
        setViewEnabledState()
    }

    override fun setEnabled(enabled: Boolean) {
        this.enabled = enabled
        setViewEnabledColor()
        setViewEnabledState()
    }

    override fun isEnabled() = this.enabled

    private fun initTxtCountInput() {
        if (!this.withDivider) txtCountInputValue.background = null
        txtCountInputValue.setText(this.qtyValue.toString())
        txtCountInputValue.addTextChangedListener {
            val newQty = it.toString().toIntOrNull()
            newQty?.let {
                if (newQty > this.maxQtyValue) {
                    txtCountInputValue.setText(this.maxQtyValue.toString())
                } else if (newQty < this.minQtyValue) {
                    txtCountInputValue.setText(this.minQtyValue.toString())
                }

                this.qtyValue = newQty
                setEnablePlusMinusButton(newQty)
            } ?: run {
                txtCountInputValue.setText(this.minQtyValue.toString())
            }
        }
    }

    private fun initImageButton() {
        imgBtnCountInputMin.setOnClickListener {
            val newQty = (txtCountInputValue.text.toString().toIntOrNull() ?: 0) - 1
            txtCountInputValue.setText(newQty.toString())
        }
        imgBtnCountInputPlus.setOnClickListener {
            val newQty = (txtCountInputValue.text.toString().toIntOrNull() ?: 0) + 1
            txtCountInputValue.setText(newQty.toString())
        }
    }

    private fun setEnablePlusMinusButton(qty: Int) {
        imgBtnCountInputPlus.isEnabled = qty < this.maxQtyValue
        imgBtnCountInputMin.isEnabled = qty > this.minQtyValue
    }

    private fun setViewEnabledState() {
        imgBtnCountInputPlus.isClickable = this.enabled
        imgBtnCountInputMin.isClickable = this.enabled
        txtCountInputValue.isEnabled = this.enabled && this.countEditable
    }

    private fun setViewEnabledColor() {
        txtCountInputValue.alpha = if (this.enabled) 1F else 0.5F
        imgBtnCountInputMin.alpha = if (this.enabled) 1F else 0.5F
        imgBtnCountInputPlus.alpha = if (this.enabled) 1F else 0.5F
    }

    fun setCurrentQty(qty: Int) {
        this.qtyValue = qty
        initTxtCountInput()
    }

    fun setMinQtyValue(minValue: Int) {
        this.minQtyValue = minValue
        initTxtCountInput()
    }

    fun setMaxQtyValue(maxValue: Int) {
        this.maxQtyValue = maxValue
        initTxtCountInput()
    }

    fun setEditCountDivider(divider: Boolean) {
        this.withDivider = divider
        initTxtCountInput()
    }

    fun setEditCountEditable(editable: Boolean) {
        this.countEditable = editable
        setViewEnabledState()
    }

    fun getCountValue() = this.qtyValue

    fun getEditText() = txtCountInputValue
}