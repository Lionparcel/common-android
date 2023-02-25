package com.lionparcel.commonandroid.form

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.lionparcel.commonandroid.R

class LPListCountInput : ConstraintLayout {

    private var txtLPListCountInputLabel : TextView
    private var txtLPListCountInputAssistiveText : TextView
    private var lpCountInput : LPCountInput
    private var vLPListCountInputBtmLine : View
    private var enabled = true
    private var withDivider = true
    private var minQtyValue = 0
    private var maxQtyValue = 99
    private var qtyValue = minQtyValue
    private var countDivider = true
    private var countEditable = true
    private var listTitle: String? = null
    private var listAssistiveText: String? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_list_count_input_view, this, true)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPListCountInput,
            0,
            0
        ).apply {
            try {
                withDivider = getBoolean(R.styleable.LPListCountInput_withDivider, withDivider)
                minQtyValue = getInt(R.styleable.LPListCountInput_minQtyValue, minQtyValue)
                maxQtyValue = getInt(R.styleable.LPListCountInput_maxQtyValue, maxQtyValue)
                countDivider = getBoolean(R.styleable.LPListCountInput_countDivider, countDivider)
                countEditable = getBoolean(R.styleable.LPListCountInput_countEditable, countEditable)
            } finally {
                recycle()
            }
        }
        txtLPListCountInputLabel = findViewById(R.id.txtLPListCountInputLabel)
        txtLPListCountInputAssistiveText = findViewById(R.id.txtLPListCountInputAssistiveText)
        lpCountInput = findViewById(R.id.lpCountInput)
        vLPListCountInputBtmLine = findViewById(R.id.vLPListCountInputBtmLine)

        initCountInput()
        initListView()
    }

    override fun setEnabled(enabled: Boolean) {
        this.enabled = enabled
        lpCountInput.isEnabled = enabled
    }

    override fun isEnabled() = enabled

    private fun initCountInput() {
        lpCountInput.apply {
            setCurrentQty(this@LPListCountInput.qtyValue)
            setMinQtyValue(this@LPListCountInput.minQtyValue)
            setMaxQtyValue(this@LPListCountInput.maxQtyValue)
            setEditCountDivider(this@LPListCountInput.countDivider)
            setEditCountEditable(this@LPListCountInput.countEditable)
        }
    }

    private fun initListView() {
        vLPListCountInputBtmLine.visibility = if (this.withDivider) View.VISIBLE else View.GONE
        txtLPListCountInputLabel.text = this.listTitle
        txtLPListCountInputAssistiveText.text = this.listAssistiveText
    }

    fun setListTitle(title: String) {
        this.listTitle = title
        initListView()
    }

    fun setListAssistiveText(assistiveText: String) {
        this.listAssistiveText = assistiveText
        initListView()
    }

    fun setListDivider(listDivider: Boolean) {
        this.withDivider = listDivider
        initListView()
    }

    fun setCurrentQty(qty: Int) {
        this.qtyValue = qty
        initCountInput()
    }

    fun setMinQtyValue(minValue: Int) {
        this.minQtyValue = minValue
        initCountInput()
    }

    fun setMaxQtyValue(maxValue: Int) {
        this.maxQtyValue = maxValue
        initCountInput()
    }

    fun setCountDivider(divider: Boolean) {
        this.countDivider = divider
        initCountInput()
    }

    fun setCountEditable(editable: Boolean) {
        this.countEditable = editable
        initCountInput()
    }

    fun getCountValue() = lpCountInput.getCountValue()

    fun getCountView() = lpCountInput

}