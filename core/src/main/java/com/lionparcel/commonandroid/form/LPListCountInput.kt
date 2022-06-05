package com.lionparcel.commonandroid.form

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.lionparcel.commonandroid.R

class LPListCountInput : ConstraintLayout {

    private var clLPListCountInput : ConstraintLayout
    private var txtLPListCountInputLabel : TextView
    private var txtLPListCountInputAssistiveText : TextView
    private var lpCountInput : LPCountInput
    private var vLPListCountInputBtmLine : View

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_list_count_input_view, this, true)
        clLPListCountInput = findViewById(R.id.clLPListCountInput)
        txtLPListCountInputLabel = findViewById(R.id.txtLPListCountInputLabel)
        txtLPListCountInputAssistiveText = findViewById(R.id.txtLPListCountInputAssistiveText)
        lpCountInput = findViewById(R.id.lpCountInput)
        vLPListCountInputBtmLine = findViewById(R.id.vLPListCountInputBtmLine)
    }
}