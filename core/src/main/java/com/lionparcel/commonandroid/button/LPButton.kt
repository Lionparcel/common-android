package com.lionparcel.commonandroid.button

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.lionparcel.commonandroid.R

class LPButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {

    private var enabledBackground: Drawable? = null
    private var disabledBackground: Drawable? = null
    private var txtColor: Int = 0

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPButton,
            0,
            0
        ).apply {

            try {
                
            } finally {
                recycle()
            }

        }
    }

}