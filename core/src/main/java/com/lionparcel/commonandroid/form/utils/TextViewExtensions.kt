package com.lionparcel.commonandroid.form.utils

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.lionparcel.commonandroid.R


fun TextView.setBoldSpannable(
    content: CharSequence,
    vararg targetStrings: String,
    fontArg: Typeface? = null
) {
    val spannableString = SpannableString(content)
    val font = fontArg ?: ResourcesCompat.getFont(context, R.font.poppins_semi_bold) ?: return
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