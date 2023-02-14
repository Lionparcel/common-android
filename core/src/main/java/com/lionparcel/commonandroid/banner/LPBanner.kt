package com.lionparcel.commonandroid.banner

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.get
import androidx.core.view.updateLayoutParams
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.lionparcel.commonandroid.R

fun Context.showBannerOneButton(
    viewGroup: ViewGroup,
    message: String,
    textAction: String,
    actionListener: ((Snackbar) -> Unit)? = null,
    callbackOnDismiss: (() -> Unit)? = null,
    duration: Int = Snackbar.LENGTH_INDEFINITE
): Snackbar {
    val bannerView = inflateBannerOneButtonView(viewGroup, message, textAction)
    return showBanner(viewGroup, bannerView, callbackOnDismiss, duration).apply {
        showBannerFromTheTop()
        val bannerView = (view as Snackbar.SnackbarLayout)[0]
        bannerView.findViewById<TextView>(R.id.txtActionBanner).let {
            it.setOnClickListener {
                actionListener?.invoke(this)
            }
        }
        bannerView.findViewById<ImageView>(R.id.imgStartBanner).visibility = View.GONE
    }
}

fun Context.showBannerOneButtonWithIcon(
    viewGroup: ViewGroup,
    message: String,
    textAction: String,
    actionListener: ((Snackbar) -> Unit)? = null,
    callbackOnDismiss: (() -> Unit)? = null,
    duration: Int = Snackbar.LENGTH_INDEFINITE
): Snackbar {
    val bannerView = inflateBannerOneButtonView(viewGroup, message, textAction)
    return showBanner(viewGroup, bannerView, callbackOnDismiss, duration).apply {
        showBannerFromTheTop()
        val bannerView = (view as Snackbar.SnackbarLayout)[0]
        bannerView.findViewById<TextView>(R.id.txtActionBanner).let {
            it.setOnClickListener {
                actionListener?.invoke(this)
            }
        }
    }
}

fun Context.showBannerTwoButton(
    viewGroup: ViewGroup,
    message: String,
    textActionRight: String,
    textActionLeft: String,
    rightActionListener: ((Snackbar) -> Unit)? = null,
    leftActionListener: ((Snackbar) -> Unit)? = null,
    callbackOnDismiss: (() -> Unit)? = null,
    duration: Int = Snackbar.LENGTH_INDEFINITE
): Snackbar {
    val bannerView = inflateBannerTwoButtonView(viewGroup, message, textActionRight, textActionLeft)
    return showBanner(viewGroup, bannerView, callbackOnDismiss, duration).apply {
        showBannerFromTheTop()
        val bannerView = (view as Snackbar.SnackbarLayout)[0]
        bannerView.findViewById<TextView>(R.id.txtActionBannerRight).let {
            it.setOnClickListener {
                rightActionListener?.invoke(this)
            }
        }
        bannerView.findViewById<TextView>(R.id.txtActionBannerLeft).let {
            it.setOnClickListener {
                leftActionListener?.invoke(this)
            }
        }
        bannerView.findViewById<ImageView>(R.id.imgStartBanner).visibility = View.GONE
    }
}

fun Context.showBannerTwoButtonWithIcon(
    viewGroup: ViewGroup,
    message: String,
    textActionRight: String,
    textActionLeft: String,
    rightActionListener: ((Snackbar) -> Unit)? = null,
    leftActionListener: ((Snackbar) -> Unit)? = null,
    callbackOnDismiss: (() -> Unit)? = null,
    duration: Int = Snackbar.LENGTH_INDEFINITE
): Snackbar {
    val bannerView = inflateBannerTwoButtonView(viewGroup, message, textActionRight, textActionLeft)
    return showBanner(viewGroup, bannerView, callbackOnDismiss, duration).apply {
        showBannerFromTheTop()
        val bannerView = (view as Snackbar.SnackbarLayout)[0]
        bannerView.findViewById<TextView>(R.id.txtActionBannerRight).let {
            it.setOnClickListener {
                rightActionListener?.invoke(this)
            }
        }
        bannerView.findViewById<TextView>(R.id.txtActionBannerLeft).let {
            it.setOnClickListener {
                leftActionListener?.invoke(this)
            }
        }
    }
}

private fun Snackbar.showBannerFromTheTop(): Snackbar {
    return apply {
        view.updateLayoutParams<FrameLayout.LayoutParams> {
            gravity = Gravity.TOP
        }
    }
}

private fun Context.inflateBannerOneButtonView(viewGroup: ViewGroup, message: String, textAction: String): View {
    val inflater = LayoutInflater.from(this)
    return inflater.inflate(R.layout.banner_item_view_one_button, viewGroup, false).apply {
        findViewById<TextView>(R.id.txtMessageBanner).text = message
        findViewById<TextView>(R.id.txtActionBanner).text = textAction
    }
}

private fun Context.inflateBannerTwoButtonView(viewGroup: ViewGroup, message: String, textActionRight: String, textActionLeft: String): View {
    val inflater = LayoutInflater.from(this)
    return inflater.inflate(R.layout.banner_item_view_two_button, viewGroup, false).apply {
        findViewById<TextView>(R.id.txtMessageBanner).text = message
        findViewById<TextView>(R.id.txtActionBannerRight).text = textActionRight
        findViewById<TextView>(R.id.txtActionBannerLeft).text = textActionLeft
    }
}

private fun showBanner(
    viewGroup: View,
    snackbarView: View,
    callbackOnDismiss: (() -> Unit)? = null,
    duration: Int,
): Snackbar {
    val snackbar = Snackbar.make(viewGroup, "", duration)
    val layout = snackbar.view as Snackbar.SnackbarLayout
    layout.setBackgroundColor(Color.TRANSPARENT)
    layout.setPadding(0, 0, 0, 0)
    layout.addView(snackbarView, 0)
    snackbar.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
    snackbar.show()
    return callbackOnDismiss?.let {
        snackbar.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)
                it.invoke()
            }
        })
        snackbar
    } ?: snackbar
}