package com.lionparcel.commonandroid.snackbartoast

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.lionparcel.commonandroid.R

fun Context.showToastSuccessBasicNoClose(
    viewGroup: ViewGroup,
    message: String,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastDefaultBasicNoClose(viewGroup, message, callbackOnDismiss).apply {

    }
}

fun Context.showToastDefaultBasicWithClose(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageEndResource: Int = R.drawable.ics_f_check_circle,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastDefaultBasicNoClose(viewGroup, message, callbackOnDismiss).apply {
        val snackView = (view as Snackbar.SnackbarLayout)[0]
        snackView.findViewById<ImageView>(R.id.imgEnd).let {
            it.isVisible = true
            it.setImageResource(imageEndResource)
        }
    }
}

fun Context.showToastDefaultLargeIconButtonText(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageStartResource: Int,
    messageButton: String,
    callbackMessageButton: () -> Unit,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastDefaultSmallIconButtonText(
        viewGroup,
        message,
        imageStartResource,
        messageButton,
        callbackMessageButton,
        callbackOnDismiss
    ).apply {
        val snackView = (view as Snackbar.SnackbarLayout)[0]
        snackView.findViewById<ImageView>(R.id.imgStart).let {
            it.updateLayoutParams<LinearLayout.LayoutParams> {
                gravity = Gravity.CENTER
            }
        }
    }
}

fun Context.showToastDefaultSmallIconButtonText(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageStartResource: Int,
    messageButton: String,
    callbackMessageButton: () -> Unit,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastDefaultButtonText(
        viewGroup,
        message,
        messageButton,
        callbackMessageButton,
        callbackOnDismiss
    ).apply {
        val snackView = (view as Snackbar.SnackbarLayout)[0]
        snackView.findViewById<ImageView>(R.id.imgStart).let {
            it.isVisible = true
            it.setImageResource(imageStartResource)
        }
    }
}

fun Context.showToastDefaultButtonText(
    viewGroup: ViewGroup,
    message: String,
    messageButton: String,
    callbackMessageButton: () -> Unit,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastDefaultBasicNoClose(viewGroup, message, callbackOnDismiss).apply {
        val snackView = (view as Snackbar.SnackbarLayout)[0]
        snackView.findViewById<TextView>(R.id.txtAction).let {
            it.isVisible = true
            it.text = messageButton
            it.setOnClickListener {
                callbackMessageButton.invoke()
                dismiss()
            }
        }
    }
}

fun Context.showToastDefaultLargeIconNoClose(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageStartResource: Int,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    val snackView = inflateToastItemView(viewGroup, message).apply {
        setVisibleItemToast(isImgStartVisible = true)
        findViewById<ImageView>(R.id.imgStart).apply {
            updateLayoutParams<LinearLayout.LayoutParams> {
                gravity = Gravity.CENTER
            }
            setImageResource(imageStartResource)
        }
    }
    return showToast(viewGroup, snackView, callbackOnDismiss)
}

fun Context.showToastDefaultSmallIconNoClose(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageStartResource: Int,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    val snackView = inflateToastItemView(viewGroup, message).apply {
        setVisibleItemToast(isImgStartVisible = true)
        findViewById<ImageView>(R.id.imgStart).setImageResource(imageStartResource)
    }
    return showToast(viewGroup, snackView, callbackOnDismiss)
}

fun Context.showToastDefaultBasicNoClose(
    viewGroup: ViewGroup,
    message: String,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    val snackView = inflateToastItemView(viewGroup, message).apply {
        setVisibleItemToast()
    }
    return showToast(viewGroup, snackView, callbackOnDismiss)
}

private fun Context.inflateToastItemView(viewGroup: ViewGroup, message: String): View {
    val inflater = LayoutInflater.from(this)
    return inflater.inflate(R.layout.toast_item_view, viewGroup, false).apply {
        findViewById<TextView>(R.id.txtMessage).text = message
    }
}

private fun View.setVisibleItemToast(
    isImgStartVisible: Boolean = false,
    isImgEndVisible: Boolean = false,
    isTxtActionVisible: Boolean = false
) {
    findViewById<ImageView>(R.id.imgStart).isVisible = isImgStartVisible
    findViewById<ImageView>(R.id.imgEnd).isVisible = isImgEndVisible
    findViewById<TextView>(R.id.txtAction).isVisible = isTxtActionVisible
}

private fun showToast(
    viewGroup: View,
    snackbarView: View,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    val snackbar = Snackbar.make(viewGroup, "", Snackbar.LENGTH_LONG)
    val layout = snackbar.view as Snackbar.SnackbarLayout
    layout.setBackgroundColor(Color.TRANSPARENT)
    layout.setPadding(0, 0, 0, 0)
    layout.addView(snackbarView, 0)
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
