package com.lionparcel.commonandroid.loading

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.PixelFormat
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import com.lionparcel.commonandroid.R

open class LPFullScreenSpinner(
    context: Context
) : Dialog(context, R.style.LPFullScreenDialog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.lp_spinner_full)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        window?.setFormat(PixelFormat.TRANSLUCENT)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val textLoadingTitle = findViewById<TextView>(R.id.text_loading_title)
        textLoadingTitle.text = loadingTitle
    }

    companion object {

        private var loadingTitle: String = "Tunggu ya..."

        fun newInstance(context: Context, title: String = loadingTitle) = LPFullScreenSpinner(context).apply {
            loadingTitle = title
        }
    }

}