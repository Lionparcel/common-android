package com.lionparcel.commonandroid.walkthrough.utils

import android.app.Activity
import android.content.Context
import android.graphics.Insets
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager

object ScreenUtils {
    fun getScreenHeight(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.y
    }

    fun getScreenWidth(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.x
    }

    fun getAxisXpositionOfViewOnScreen(targetView: View): Int {
        val locationTarget = IntArray(2)
        targetView.getLocationOnScreen(locationTarget)
        return locationTarget[0]
    }

    fun getAxisYpositionOfViewOnScreen(targetView: View): Int {
        val locationTarget = IntArray(2)
        targetView.getLocationOnScreen(locationTarget)
        return locationTarget[1]
    }

    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    fun getActionBarHeight(context: Context): Int {
        val typedValue = TypedValue()
        var actionBarHeight = 0
        if (context.theme.resolveAttribute(android.R.attr.actionBarSize, typedValue, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(
                typedValue.data,
                context.resources.displayMetrics
            )
        }
        return actionBarHeight
    }

    fun isViewLocatedAtHalfTopOfTheScreen(activity: Activity, targetView: View): Boolean {
        val screenHeight = getScreenHeight(activity)
        val positionTargetAxisY = getAxisYpositionOfViewOnScreen(targetView)
        return screenHeight / 2 > positionTargetAxisY + (targetView.height / 2)
    }

    fun isViewLocatedAtHalfTopOfTheScreen(
        activity: Activity,
        targetView: View,
        statusBarHeight: Int
    ): Boolean {
        val screenHeight = getScreenHeight(activity) - statusBarHeight
        val positionTargetAxisY = getAxisYpositionOfViewOnScreen(targetView)
        return screenHeight / 2 > positionTargetAxisY + (targetView.height / 2)
    }

    fun isViewLocatedAtHalfLeftOfTheScreen(activity: Activity, targetView: View): Boolean {
        val screenWidth = getScreenWidth(activity)
        val positionTargetAxisX = getAxisXpositionOfViewOnScreen(targetView)
        return screenWidth / 2 > positionTargetAxisX
    }

    fun getScreenWidthCompat(activity: Activity): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = activity.windowManager.currentWindowMetrics
            val insets: Insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.width() - insets.left - insets.right
        } else {
            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.widthPixels
        }
    }

    fun getScreenHeightCompat(activity: Activity): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = activity.windowManager.currentWindowMetrics
            val insets: Insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.height() - insets.top - insets.bottom
        } else {
            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.heightPixels
        }
    }
}

