package com.lionparcel.commonandroid.form

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import com.lionparcel.commonandroid.R
import kotlinx.android.synthetic.main.lp_input_number.view.*

@RequiresApi(Build.VERSION_CODES.O)
class LPInputNumber @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : LinearLayout(context, attrs, defStyle) {

    private val itemCount: Int
    private val showCursor: Boolean
    private val inputType: Int
    private val importantForAutofillLocal: Int
    private val autofillHints: String?
    private var itemWidth: Int
    private var itemHeight: Int
    private val cursorColor: Int
    private val allCaps: Boolean
    private val marginBetween: Int
    private val isPassword: Boolean

    private val textSizeDefault: Int
    private val textColor: Int
    private val backgroundImage: Drawable?
    private val font: Typeface?

    private val highlightedTextSize: Int
    private val highlightedTextColor: Int
    private val highlightedBackgroundImage: Drawable?
    private val highlightedFont: Typeface?

    private val filledTextSize: Int
    private val filledTextColor: Int
    private val filledBackgroundImage: Drawable?
    private val filledFont: Typeface?

    private var onFinishFunction: ((String) -> Unit) = {}
    private var onCharacterUpdatedFunction: ((Boolean) -> Unit) = {}

    private val editTexts: MutableList<EditText> = mutableListOf()
    private var focusIndex = 0

    private var disableEditListener: Boolean = false

    init {
        LayoutInflater.from(context).inflate(R.layout.lp_input_number, this, true)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPInputNumber,
            0, 0
        )
            .apply {
                try {
                    itemCount = getInteger(R.styleable.LPInputNumber_in_itemCount, 1)
                    showCursor = getBoolean(R.styleable.LPInputNumber_in_showCursor, false)
                    inputType = getInteger(R.styleable.LPInputNumber_android_inputType, 0)
                    importantForAutofillLocal =
                        getInteger(R.styleable.LPInputNumber_android_importantForAutofill, 0)
                    autofillHints = getString(R.styleable.LPInputNumber_android_autofillHints)
                    itemWidth = getDimensionPixelSize(R.styleable.LPInputNumber_in_itemWidth, 42)
                    itemHeight = getDimensionPixelSize(R.styleable.LPInputNumber_in_itemHeight, 40)
                    cursorColor = getColor(R.styleable.LPInputNumber_in_cursorColor, R.style.LPInputNumberCursor)
                    allCaps = getBoolean(R.styleable.LPInputNumber_in_allcaps, false)
                    marginBetween = getDimensionPixelSize(
                        R.styleable.LPInputNumber_in_marginBetween,
                        8.dpTopx
                    )
                    isPassword = getBoolean(R.styleable.LPInputNumber_in_ispassword, false)

                    textSizeDefault =
                        getDimensionPixelSize(R.styleable.LPInputNumber_in_textSize, 14.dpTopx)
                    textColor = getInteger(R.styleable.LPInputNumber_in_textColor, Color.BLACK)
                    backgroundImage =
                        getDrawable(R.styleable.LPInputNumber_in_backgroundImage) ?: customBackground()
                    font = getFont(R.styleable.LPInputNumber_in_Font)

                    highlightedTextSize = getDimensionPixelSize(
                        R.styleable.LPInputNumber_in_highlightedTextSize,
                        textSizeDefault
                    )
                    highlightedTextColor = getInteger(
                        R.styleable.LPInputNumber_in_highlightedTextColor,
                        textColor
                    )
                    highlightedBackgroundImage =
                        getDrawable(R.styleable.LPInputNumber_in_highlightedBackgroundImage)
                            ?: backgroundImage
                    highlightedFont = getFont(R.styleable.LPInputNumber_in_highlightedFont) ?: font

                    filledTextSize = getDimensionPixelSize(
                        R.styleable.LPInputNumber_in_filledTextSize,
                        textSizeDefault
                    )
                    filledTextColor = getInteger(R.styleable.LPInputNumber_in_filledTextColor, textColor)
                    filledBackgroundImage =
                        getDrawable(R.styleable.LPInputNumber_in_filledBackgroundImage)
                            ?: backgroundImage
                    filledFont = getFont(R.styleable.LPInputNumber_in_filledFont) ?: font

                    initEditTexts()
                } finally {
                    recycle()
                }
            }
    }

    private val Int.dpTopx: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    private fun initEditTexts() {
        for (x in 0 until itemCount) {
            addEditText(x)
            addListenerForIndex(x)
        }

        styleEditTexts()
        val edt = editTexts[0]
        edt.postDelayed( {
            val editText = editTexts[focusIndex]
            editText.requestFocus()
            styleEditTexts()
            showKeyboard(true, editText)
        }, 100)
    }

    private fun addEditText(index: Int) {
        val edt = EditText(ContextThemeWrapper(context, R.style.LPInputNumberCursor))
        edt.isCursorVisible = showCursor
        edt.inputType = inputType
        edt.importantForAutofill = importantForAutofillLocal
        edt.setAutofillHints(autofillHints)

        val params = LayoutParams(itemWidth, itemHeight)

        edt.isAllCaps = allCaps

        val leftDp = if(index == 0) 8.dpTopx else 0.dpTopx

        params.setMargins(
            leftDp,
            8.dpTopx,
            marginBetween,
            8.dpTopx
        )

        edt.layoutParams = params
        edt.gravity = Gravity.CENTER

        styleDefault(edt)

        edt.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus){
                edt.post(Runnable { edt.setSelection(0) })
            }
        }

        editTexts.add(edt)
        in_wrapper.addView(edt)
    }


    private fun addListenerForIndex(index: Int) {
        editTexts[index].addTextChangedListener {
            if (!disableEditListener) {
                when {
                    editTexts[index].text.isEmpty() -> {
                        changeFocus(false)
                    }
                    editTexts[index].text.length > 1 -> {
                        editTexts[index].setText(it?.first().toString())
                    }
                    else -> {
                        changeFocus(true)
                    }
                }
            }
        }
        editTexts[index].setOnKeyListener { _, keyCode, event ->
            if(keyCode == KeyEvent.KEYCODE_DEL &&
                event.action == KeyEvent.ACTION_DOWN) {
                disableEditListener = true
                editTexts[index].setText("")
                changeFocus(false)
                //if(index-1 >= 0)
                //editTexts[index - 1].setText("")
                disableEditListener = false
            }
            if (event.action == KeyEvent.ACTION_DOWN &&
                keyCode == KeyEvent.KEYCODE_ENTER) {
                if(isFilled())
                    onFinishFunction(getStringFromFields())
            }
            return@setOnKeyListener false
        }
        editTexts[index].setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus)
                focusIndex = index
            styleEditTexts()
            v.post(Runnable {
                if (focusIndex < editTexts.size)
                    editTexts[focusIndex].setSelection(0)
            })
        }
    }

    private fun isFilled(): Boolean {
        editTexts.forEach {
            if(it.text.isNullOrBlank()) return false
        }
        return true
    }

    fun getStringFromFields():String {
        var str = ""
        editTexts.forEach {
            str += it.text.firstOrNull()
        }
        return str
    }

    private fun changeFocus(increment: Boolean) {
        if(increment) focusIndex++ else focusIndex--

        when {
            focusIndex < 0 -> focusIndex = 0
            focusIndex < editTexts.size -> {
                editTexts[focusIndex].requestFocus()
            }
            else -> {
                editTexts.forEach {
                    it.clearFocus()
                }
                showKeyboard(false, editTexts.last())
                if(isFilled())
                    onFinishFunction(getStringFromFields())
            }
        }
        onCharacterUpdatedFunction(isFilled())
        styleEditTexts()
    }

    private fun styleEditTexts() {
        for (x in 0 until editTexts.size){
            val edt = editTexts[x]
            when {
                x < focusIndex -> {
                    styleFilled(edt)
                }
                x == focusIndex -> {
                    styleHighlighted(edt)
                }
                x > focusIndex -> {
                    styleDefault(edt)
                }
            }
        }
    }

    private fun customBackground(): Drawable {
        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.cornerRadius = 8.dpTopx.toFloat()
        shape.setColor(Color.WHITE)
        shape.setStroke(2.dpTopx, Color.BLACK)
        return shape
    }


    private fun styleHighlighted(editText: EditText) {
        editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, highlightedTextSize.toFloat())
        editText.setTextColor(highlightedTextColor)
        editText.background = highlightedBackgroundImage
        editText.typeface = highlightedFont
    }

    private fun styleFilled(editText: EditText) {
        editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, filledTextSize.toFloat())
        editText.setTextColor(filledTextColor)
        editText.background = filledBackgroundImage
        editText.typeface = filledFont
    }

    private fun styleDefault(editText: EditText) {
        editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeDefault.toFloat())
        editText.setTextColor(textColor)
        editText.background = backgroundImage
        editText.typeface = font
    }

    private fun showKeyboard(show: Boolean, editText: EditText) {
        val imm: InputMethodManager? = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        if(show){
            imm?.showSoftInput(editText, 0)
        }else {
            imm?.hideSoftInputFromWindow(editText.applicationWindowToken, 0)
        }
    }

    fun setOnFinishListener(func: (String) -> Unit) {
        onFinishFunction = func
    }

    fun setOnCharacterUpdatedListener(func: (Boolean) -> Unit) {
        onCharacterUpdatedFunction = func
    }

    fun fitToWidth(width:Int){
        val outerMargin = 8.dpTopx
        var dividedSpace = (width - (outerMargin*2)) / editTexts.size
        dividedSpace -= marginBetween
        itemWidth = dividedSpace
        itemHeight = (itemWidth * 1.25f).toInt()

        val params = LayoutParams(
            itemWidth,
            itemHeight
        )

        editTexts.forEachIndexed { index, editText ->
            val leftDp = if(index == 0) 8.dpTopx else 0.dpTopx

            params.setMargins(
                leftDp,
                8.dpTopx,
                marginBetween,
                8.dpTopx
            )
            editText.layoutParams = params
        }
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        editTexts.forEach {
            it.isEnabled = enabled
        }
    }
}