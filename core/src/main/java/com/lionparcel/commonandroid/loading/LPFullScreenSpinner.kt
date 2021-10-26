package com.lionparcel.commonandroid.loading

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.PixelFormat
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
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
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
    }

    companion object {

        fun createSpinner(context: Context): LPFullScreenSpinner {
            return LPFullScreenSpinner(context)
        }
    }

}