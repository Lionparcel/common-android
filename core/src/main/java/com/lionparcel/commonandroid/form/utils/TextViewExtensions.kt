package com.lionparcel.commonandroid.form.utils

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.lionparcel.commonandroid.R


fun TextView.setSemiBoldSpannable(
    content: CharSequence,
    vararg targetStrings: String,
    fontArg: Typeface? = null
) {
    val spannableString = SpannableString(content)
    val font = fontArg ?: ResourcesCompat.getFont(context, R.font.poppins_semi_bold) ?: return
    val color = fontArg ?: ForegroundColorSpan(ResourcesCompat.getColor(resources, R.color.interpack6, null))
    targetStrings.forEach {
        val typefaceSpan = TypeFaceSpanCompat("", font)
        val index = spannableString.indexOf(it)
        spannableString.setSpan(
            typefaceSpan,
            spannableString.indexOf(it),
            index + it.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            color,
            spannableString.indexOf(it),
            index + it.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
    text = spannableString
}

fun TextView.setBoldSpannable(
    content: CharSequence,
    vararg targetStrings: String,
    fontArg: Typeface? = null
) {
    val spannableString = SpannableString(content)
    val font = fontArg ?: ResourcesCompat.getFont(context, R.font.poppins_bold) ?: return
    targetStrings.forEach {
        val typefaceSpan = TypeFaceSpanCompat("", font)
        val index = spannableString.indexOf(it)
        spannableString.setSpan(
            typefaceSpan,
            spannableString.indexOf(it),
            index + it.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
    text = spannableString
}

fun TextView.setBoldClickable(
    content: CharSequence,
    vararg targetStrings: String,
    listener: (View) -> Unit,
    fontArg: Typeface? = null
) {
    val spannableString = SpannableString(content)
    val font = fontArg ?: ResourcesCompat.getFont(context, R.font.poppins_semi_bold) ?: return
    val clickableSpan = object : ClickableSpan() {
        override fun updateDrawState(ds: TextPaint) {
            ds.isUnderlineText = false
        }
        override fun onClick(view: View) {
            listener.invoke(view)
        }
    }
    targetStrings.forEach {
        val typefaceSpan = TypeFaceSpanCompat("", font)
        val index = spannableString.indexOf(it)
        spannableString.setSpan(
            typefaceSpan,
            spannableString.indexOf(it),
            index + it.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
    targetStrings[0].apply {
        val index = spannableString.indexOf(this)
        spannableString.setSpan(
            clickableSpan,
            spannableString.indexOf(this),
            index + this.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
    text = spannableString
}

fun TextView.setRegularFont() {
    typeface = ResourcesCompat.getFont(context, R.font.poppins_regular)
}

fun TextView.setSemiBoldFont() {
    typeface = ResourcesCompat.getFont(context, R.font.poppins_semi_bold)
}