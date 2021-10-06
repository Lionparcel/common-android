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

    var animationDrawable: AnimationDrawable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.lp_spinner_full)
        val mProgressBar: ImageView = findViewById(R.id.iv_frame_loading)
        animationDrawable = mProgressBar.background as AnimationDrawable;
        animationDrawable?.start()
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        window?.setFormat(PixelFormat.TRANSLUCENT)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animationDrawable?.stop()
    }

    companion object {

        fun createSpinner(context: Context): LPFullScreenSpinner {
            return LPFullScreenSpinner(context)
        }
    }

}