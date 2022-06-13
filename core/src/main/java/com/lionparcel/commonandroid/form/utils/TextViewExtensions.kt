package com.lionparcel.commonandroid.form.utils

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
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