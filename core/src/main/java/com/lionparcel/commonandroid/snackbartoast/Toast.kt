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
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.lionparcel.commonandroid.R

enum class ToastType {
    DEFAULT,
    SUCCESS,
    ERROR
}

fun Context.showToastLargeIconWithClose(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageStartResource: Int = R.drawable.ics_f_info_circle,
    @DrawableRes imageEndResource: Int = R.drawable.ics_o_close,
    toastType: ToastType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastSmallIconWithClose(
        viewGroup,
        message,
        imageStartResource,
        imageEndResource,
        toastType,
        callbackOnDismiss
    ).apply {
        val snackView = (view as Snackbar.SnackbarLayout)[0]
        snackView.findViewById<ImageView>(R.id.imgStart)
            .updateLayoutParams<LinearLayout.LayoutParams> {
                gravity = Gravity.CENTER
            }
    }
}

fun Context.showToastSmallIconWithClose(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageStartResource: Int = R.drawable.ics_f_check_circle,
    @DrawableRes imageEndResource: Int = R.drawable.ics_o_close,
    toastType: ToastType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastBasicWithClose(
        viewGroup,
        message,
        imageEndResource,
        toastType,
        callbackOnDismiss
    ).apply {
        val snackView = (view as Snackbar.SnackbarLayout)[0]
        snackView.findViewById<ImageView>(R.id.imgStart).let {
            it.isVisible = true
            it.setImageResource(imageStartResource)
        }
    }
}

fun Context.showToastBasicWithClose(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageEndResource: Int = R.drawable.ics_o_close,
    toastType: ToastType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastBasicNoClose(viewGroup, message, toastType, callbackOnDismiss).apply {
        val snackView = (view as Snackbar.SnackbarLayout)[0]
        snackView.findViewById<ImageView>(R.id.imgEnd).let {
            it.isVisible = true
            it.setImageResource(imageEndResource)
            it.setOnClickListener {
                dismiss()
            }
        }
    }
}

fun Context.showToastLargeIconButtonText(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageStartResource: Int = R.drawable.ics_f_info_circle,
    messageButton: String,
    callbackMessageButton: () -> Unit,
    toastType: ToastType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastSmallIconButtonText(
        viewGroup,
        message,
        imageStartResource,
        messageButton,
        callbackMessageButton,
        toastType,
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

fun Context.showToastSmallIconButtonText(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageStartResource: Int = R.drawable.ics_f_check_circle,
    messageButton: String,
    callbackMessageButton: () -> Unit,
    toastType: ToastType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastButtonText(
        viewGroup,
        message,
        messageButton,
        callbackMessageButton,
        toastType,
        callbackOnDismiss
    ).apply {
        val snackView = (view as Snackbar.SnackbarLayout)[0]
        snackView.findViewById<ImageView>(R.id.imgStart).let {
            it.isVisible = true
            it.setImageResource(imageStartResource)
        }
    }
}

fun Context.showToastButtonText(
    viewGroup: ViewGroup,
    message: String,
    messageButton: String,
    callbackMessageButton: () -> Unit,
    toastType: ToastType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastBasicNoClose(viewGroup, message, toastType, callbackOnDismiss).apply {
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

fun Context.showToastLargeIconNoClose(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageStartResource: Int = R.drawable.ics_f_info_circle,
    toastType: ToastType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    val snackView = inflateToastItemView(viewGroup, message).apply {
        setVisibleItemToast(isImgStartVisible = true)
        setBackgroundItemToast(toastType)
        findViewById<ImageView>(R.id.imgStart).apply {
            updateLayoutParams<LinearLayout.LayoutParams> {
                gravity = Gravity.CENTER
            }
            setImageResource(imageStartResource)
        }
    }
    return showToast(viewGroup, snackView, callbackOnDismiss)
}

fun Context.showToastSmallIconNoClose(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageStartResource: Int = R.drawable.ics_f_check_circle,
    toastType: ToastType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    val snackView = inflateToastItemView(viewGroup, message).apply {
        setVisibleItemToast(isImgStartVisible = true)
        setBackgroundItemToast(toastType)
        findViewById<ImageView>(R.id.imgStart).setImageResource(imageStartResource)
    }
    return showToast(viewGroup, snackView, callbackOnDismiss)
}

fun Context.showToastBasicNoClose(
    viewGroup: ViewGroup,
    message: String,
    toastType: ToastType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    val snackView = inflateToastItemView(viewGroup, message).apply {
        setVisibleItemToast()
        setBackgroundItemToast(toastType)
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

private fun View.setBackgroundItemToast(toastType: ToastType) {
    findViewById<LinearLayout>(R.id.llParent).background =
        when (toastType) {
            ToastType.DEFAULT -> R.drawable.bg_toast_item_default_rounded
            ToastType.SUCCESS -> R.drawable.bg_toast_item_success_rounded
            ToastType.ERROR -> R.drawable.bg_toast_item_error_rounded
        }.let { ContextCompat.getDrawable(context, it) }
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
