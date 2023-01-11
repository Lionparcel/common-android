package com.lionparcel.commonandroid.loading

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.KeyEvent
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.FloatRange
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.popup.base.BaseDialogFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.lp_custom_loading_da.*
import java.util.concurrent.TimeUnit

class LPCustomLoadingDA : BaseDialogFragment() {

    companion object {

        fun newInstance(
            @DrawableRes loadingDrawable: Int = R.drawable.custom_progressbar,
            title: String? = null,
            backCancelDisable: Boolean = true,
            @FloatRange(from = 0.0, to = 1.0) backgroundTransparency: Float = 0.5F
        ) = LPCustomLoadingDA().apply {
            this.loadingDrawable = loadingDrawable
            this.title = title
            this.backCancelDisable = backCancelDisable
            this.backgroundTransparency = backgroundTransparency
        }

        fun dismiss() = LPCustomLoadingDA().apply {
            this.dismissLoading()
        }
    }

    private var loadingDrawable: Int = R.drawable.custom_progressbar
    private var title: String? = null
    private var backCancelDisable: Boolean = true
    private var progressSuccess: Boolean = false
    private var onDismissListener: DialogInterface.OnDismissListener? = null
    private var loadSuccessCallBack: (() -> Unit)? = null
    private var backgroundTransparency: Float = 0.5F

    private val LOADING_PROGRESS_MAX_VALUE = 1000
    private val LOADING_PROGRESS_PERIOD_VALUE = TimeUnit.SECONDS.toMillis(3) / LOADING_PROGRESS_MAX_VALUE
    private val LOADING_PROGRESS_TIME_OUT_VALUE = 900

    private fun dismissLoading() = dismiss()

    override fun getContentResource(): Int = R.layout.lp_custom_loading_da

    override fun initViews() {
        super.initViews()
        prepareView()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        val window = dialog.window

        dialog.setCanceledOnTouchOutside(false)
        dialog.setOnKeyListener { _, i, _ -> i == KeyEvent.KEYCODE_BACK && this.backCancelDisable }

        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setGravity(Gravity.CENTER)
        dialog.setOnShowListener {
            dialog.setOnDismissListener {
                stopHandleLoading()
                onDismissListener?.onDismiss(it)
            }
            startHandleLoading()
        }
        return dialog
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.attributes?.let {
            it.dimAmount = this.backgroundTransparency
            dialog?.window?.attributes = it
        }
    }

    private fun prepareView() {

        pb_loading_da.indeterminateDrawable = ResourcesCompat.getDrawable(resources, this.loadingDrawable, null)
        txt_loading_da_title?.isVisible = this.title != null
        txt_loading_da_title?.text = this.title

    }

    private fun Disposable.collect() = compositeDisposable.add(this)

    private fun startHandleLoading() {
        Observable.interval(0, LOADING_PROGRESS_PERIOD_VALUE, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (pb_loading_da.progress == LOADING_PROGRESS_MAX_VALUE) {
                    stopHandleLoading()
                    dismiss()
                    loadSuccessCallBack?.invoke()
                } else if (progressSuccess || pb_loading_da.progress <= LOADING_PROGRESS_TIME_OUT_VALUE) {
                    pb_loading_da.progress++
                }
            }, {
                it.printStackTrace()
            }).collect()
    }

    private fun stopHandleLoading() {
        compositeDisposable.dispose()
    }

    fun setProgressSuccess() {
        this.progressSuccess = true
    }

    fun setOnDismissListener(listener: DialogInterface.OnDismissListener) {
        this.onDismissListener = listener
    }

    fun setLoadSuccessCallBack(loadSuccessCallBack: () -> Unit) {
        this.loadSuccessCallBack = loadSuccessCallBack
    }

}