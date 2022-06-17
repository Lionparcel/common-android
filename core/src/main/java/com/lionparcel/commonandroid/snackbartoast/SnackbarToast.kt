package com.lionparcel.commonandroid.snackbartoast

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
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

enum class MessageType {
    DEFAULT,
    SUCCESS,
    ERROR
}


fun Context.showToastLargeIconWithClose(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageStartResource: Int = R.drawable.ics_f_info_circle,
    @DrawableRes imageEndResource: Int = R.drawable.ics_o_close_shades5,
    messageType: MessageType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastSmallIconWithClose(
        viewGroup,
        message,
        imageStartResource,
        imageEndResource,
        messageType,
        callbackOnDismiss
    ).apply {
        val snackView = (view as Snackbar.SnackbarLayout)[0]
        snackView.findViewById<ImageView>(R.id.imgStart)
            .updateLayoutParams<LinearLayout.LayoutParams> {
                gravity = Gravity.CENTER
            }
    }
}

fun Context.showSnackbarLargeIconWithClose(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageStartResource: Int = R.drawable.ics_f_info_circle,
    @DrawableRes imageEndResource: Int = R.drawable.ics_o_close_shades5,
    messageType: MessageType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastLargeIconWithClose(
        viewGroup,
        message,
        imageStartResource,
        imageEndResource,
        messageType,
        callbackOnDismiss
    ).showSnackbarFromTheTop()
}

fun Context.showToastSmallIconWithClose(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageStartResource: Int = R.drawable.ics_f_check_circle,
    @DrawableRes imageEndResource: Int = R.drawable.ics_o_close_shades5,
    messageType: MessageType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastBasicWithClose(
        viewGroup,
        message,
        imageEndResource,
        messageType,
        callbackOnDismiss
    ).apply {
        val snackView = (view as Snackbar.SnackbarLayout)[0]
        snackView.findViewById<ImageView>(R.id.imgStart).let {
            it.isVisible = true
            it.setImageResource(imageStartResource)
        }
    }
}

fun Context.showSnackbarSmallIconWithClose(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageStartResource: Int = R.drawable.ics_f_check_circle,
    @DrawableRes imageEndResource: Int = R.drawable.ics_o_close_shades5,
    messageType: MessageType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastSmallIconWithClose(
        viewGroup,
        message,
        imageStartResource,
        imageEndResource,
        messageType,
        callbackOnDismiss
    ).showSnackbarFromTheTop()
}

fun Context.showToastBasicWithClose(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageEndResource: Int = R.drawable.ics_o_close_shades5,
    messageType: MessageType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastBasicNoClose(viewGroup, message, messageType, callbackOnDismiss).apply {
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

fun Context.showSnackbarBasicWithClose(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageEndResource: Int = R.drawable.ics_o_close_shades5,
    messageType: MessageType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastBasicWithClose(
        viewGroup,
        message,
        imageEndResource,
        messageType,
        callbackOnDismiss
    ).showSnackbarFromTheTop()
}

fun Context.showToastLargeIconButtonText(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageStartResource: Int = R.drawable.ics_f_info_circle,
    messageButton: String,
    callbackMessageButton: () -> Unit,
    messageType: MessageType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastSmallIconButtonText(
        viewGroup,
        message,
        imageStartResource,
        messageButton,
        callbackMessageButton,
        messageType,
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

fun Context.showSnackbarLargeIconButtonText(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageStartResource: Int = R.drawable.ics_f_info_circle,
    messageButton: String,
    callbackMessageButton: () -> Unit,
    messageType: MessageType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastLargeIconButtonText(
        viewGroup,
        message,
        imageStartResource,
        messageButton,
        callbackMessageButton,
        messageType,
        callbackOnDismiss
    ).showSnackbarFromTheTop()
}

fun Context.showToastSmallIconButtonText(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageStartResource: Int = R.drawable.ics_f_check_circle,
    messageButton: String,
    callbackMessageButton: () -> Unit,
    messageType: MessageType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastButtonText(
        viewGroup,
        message,
        messageButton,
        callbackMessageButton,
        messageType,
        callbackOnDismiss
    ).apply {
        val snackView = (view as Snackbar.SnackbarLayout)[0]
        snackView.findViewById<ImageView>(R.id.imgStart).let {
            it.isVisible = true
            it.setImageResource(imageStartResource)
        }
    }
}

fun Context.showSnackbarSmallIconButtonText(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageStartResource: Int = R.drawable.ics_f_check_circle,
    messageButton: String,
    callbackMessageButton: () -> Unit,
    messageType: MessageType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastSmallIconButtonText(
        viewGroup,
        message,
        imageStartResource,
        messageButton,
        callbackMessageButton,
        messageType,
        callbackOnDismiss
    ).showSnackbarFromTheTop()
}

fun Context.showToastButtonText(
    viewGroup: ViewGroup,
    message: String,
    messageButton: String,
    callbackMessageButton: () -> Unit,
    messageType: MessageType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastBasicNoClose(viewGroup, message, messageType, callbackOnDismiss).apply {
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

fun Context.showSnackbarButtonText(
    viewGroup: ViewGroup,
    message: String,
    messageButton: String,
    callbackMessageButton: () -> Unit,
    messageType: MessageType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastButtonText(
        viewGroup,
        message,
        messageButton,
        callbackMessageButton,
        messageType,
        callbackOnDismiss
    ).showSnackbarFromTheTop()
}

fun Context.showToastLargeIconNoClose(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageStartResource: Int = R.drawable.ics_f_info_circle,
    messageType: MessageType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    val snackView = inflateToastItemView(viewGroup, message).apply {
        setVisibleItemToast(isImgStartVisible = true)
        setBackgroundItemToast(messageType)
        findViewById<ImageView>(R.id.imgStart).apply {
            updateLayoutParams<LinearLayout.LayoutParams> {
                gravity = Gravity.CENTER
            }
            setImageResource(imageStartResource)
        }
    }
    return showToast(viewGroup, snackView, callbackOnDismiss)
}

fun Context.showSnackbarLargeIconNoClose(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageStartResource: Int = R.drawable.ics_f_info_circle,
    messageType: MessageType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastLargeIconNoClose(
        viewGroup,
        message,
        imageStartResource,
        messageType,
        callbackOnDismiss
    ).showSnackbarFromTheTop()
}

fun Context.showToastSmallIconNoClose(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageStartResource: Int = R.drawable.ics_f_check_circle,
    messageType: MessageType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    val snackView = inflateToastItemView(viewGroup, message).apply {
        setVisibleItemToast(isImgStartVisible = true)
        setBackgroundItemToast(messageType)
        findViewById<ImageView>(R.id.imgStart).setImageResource(imageStartResource)
    }
    return showToast(viewGroup, snackView, callbackOnDismiss)
}

fun Context.showSnackbarSmallIconNoClose(
    viewGroup: ViewGroup,
    message: String,
    @DrawableRes imageStartResource: Int = R.drawable.ics_f_check_circle,
    messageType: MessageType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastSmallIconNoClose(
        viewGroup,
        message,
        imageStartResource,
        messageType,
        callbackOnDismiss
    ).showSnackbarFromTheTop()
}

fun Context.showToastBasicNoClose(
    viewGroup: ViewGroup,
    message: String,
    messageType: MessageType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    val snackView = inflateToastItemView(viewGroup, message).apply {
        setVisibleItemToast()
        setBackgroundItemToast(messageType)
    }
    return showToast(viewGroup, snackView, callbackOnDismiss)
}

fun Context.showSnackbarBasicNoClose(
    viewGroup: ViewGroup,
    message: String,
    messageType: MessageType,
    callbackOnDismiss: (() -> Unit)? = null
): Snackbar {
    return showToastBasicNoClose(
        viewGroup,
        message,
        messageType,
        callbackOnDismiss
    ).showSnackbarFromTheTop()
}

private fun Snackbar.showSnackbarFromTheTop(): Snackbar {
    return apply {
        view.updateLayoutParams<FrameLayout.LayoutParams> {
            gravity = Gravity.TOP
        }
    }
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

private fun View.setBackgroundItemToast(messageType: MessageType) {
    findViewById<LinearLayout>(R.id.llParent).background =
        when (messageType) {
            MessageType.DEFAULT -> R.drawable.bg_toast_item_default_rounded
            MessageType.SUCCESS -> R.drawable.bg_toast_item_success_rounded
            MessageType.ERROR -> R.drawable.bg_toast_item_error_rounded
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
