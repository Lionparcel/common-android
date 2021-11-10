package com.lionparcel.commonandroid.walkthrough.utils

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.ScaleAnimation

object AnimationUtils {
    fun getScaleAnimation(offset: Int, duration: Int): Animation {
        val anim = ScaleAnimation(
            0f, 1f,
            0f, 1f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f)
        anim.fillAfter = true
        anim.startOffset = offset.toLong()
        anim.duration = duration.toLong()
        return anim
    }

    fun getFadeInAnimation(offset: Int, duration: Int): Animation {
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.startOffset = offset.toLong()
        fadeIn.interpolator = DecelerateInterpolator()
        fadeIn.duration = duration.toLong()
        return fadeIn
    }

    fun setBouncingAnimation(view: View, offset: Int, duration: Int): View {
        val objAnim = ObjectAnimator.ofPropertyValuesHolder(view,
            PropertyValuesHolder.ofFloat("scaleX", 1.05f),
            PropertyValuesHolder.ofFloat("scaleY", 1.05f))
        objAnim.duration = duration.toLong()
        objAnim.startDelay = offset.toLong()
        objAnim.repeatCount = ObjectAnimator.INFINITE
        objAnim.repeatMode = ObjectAnimator.REVERSE
        objAnim.start()
        return view
    }

    fun setAnimationToView(view: View, animation: Animation): View {
        view.startAnimation(animation)
        return view
    }
}
