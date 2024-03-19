package com.lionparcel.commonandroid.loading

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.FloatRange
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.common.BaseViewBinding
import com.lionparcel.commonandroid.databinding.LpCustomLoadingDaBinding
import com.lionparcel.commonandroid.popup.base.BaseDialogFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class LPCustomLoadingDA : BaseDialogFragment(), BaseViewBinding<LpCustomLoadingDaBinding> {

    companion object {

        const val BACKGROUND_TRANSPARENT = 0f
        const val BACKGROUND_DEFAULT_ALPHA = 0.5f

        /**
         * Call when you need loading with only progressBar and title/content below progressBar
         */
        fun newInstance(
            @DrawableRes loadingDrawable: Int = R.drawable.custom_progressbar,
            content: String? = null,
            backCancelDisable: Boolean = true,
            cancelAbleTouchOutside: Boolean = false,
            @FloatRange(from = 0.0, to = 1.0) backgroundTransparency: Float = BACKGROUND_DEFAULT_ALPHA
        ) = LPCustomLoadingDA().apply {
            this.loadingDrawable = loadingDrawable
            this.content = content
            this.backCancelDisable = backCancelDisable
            this.cancelAbleTouchOutside = cancelAbleTouchOutside
            this.backgroundTransparency = backgroundTransparency
        }


        /**
         * Call when you need loading with progressBar, illustration and title above progressBar
         */
        fun newInstance(
            @DrawableRes loadingDrawable: Int = R.drawable.custom_progressbar,
            title: String? = null,
            @DrawableRes illustrationImage: Int? = null,
            backCancelDisable: Boolean = true,
            cancelAbleTouchOutside: Boolean = false,
            @FloatRange(from = 0.0, to = 1.0) backgroundTransparency: Float = BACKGROUND_DEFAULT_ALPHA
        ) = LPCustomLoadingDA().apply {
            this.loadingDrawable = loadingDrawable
            this.headerTitle = title
            this.illustration = illustrationImage
            this.backCancelDisable = backCancelDisable
            this.cancelAbleTouchOutside = cancelAbleTouchOutside
            this.backgroundTransparency = backgroundTransparency
        }


        fun dismiss() = LPCustomLoadingDA().apply {
            this.dismissLoading()
        }
    }

    override lateinit var binding: LpCustomLoadingDaBinding


    private var loadingDrawable: Int = R.drawable.custom_progressbar
    private var content: String? = null
    private var headerTitle: String? = null
    private var illustration: Int? = null
    private var backCancelDisable: Boolean = true
    private var cancelAbleTouchOutside: Boolean = false
    private var progressSuccess: Boolean = false
    private var onDismissListener: DialogInterface.OnDismissListener? = null
    private var loadSuccessCallBack: (() -> Unit)? = null
    private var backgroundTransparency: Float = 0.5F

    private val LOADING_PROGRESS_MAX_VALUE = 1000
    private val LOADING_PROGRESS_PERIOD_VALUE = TimeUnit.SECONDS.toMillis(3) / LOADING_PROGRESS_MAX_VALUE
    private val LOADING_PROGRESS_TIME_OUT_VALUE = 900

    private fun dismissLoading() = dismiss()

    override fun getContentResource(): Int = -1

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): LpCustomLoadingDaBinding {
        binding = LpCustomLoadingDaBinding.inflate(inflater, container, false)
        return binding
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawableResource(R.color.transparent)
        parentView = getViewBinding(inflater, container).root
        return parentView
    }

    override fun initViews() {
        super.initViews()
        prepareView()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        val window = dialog.window

        dialog.setCanceledOnTouchOutside(cancelAbleTouchOutside)
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
        binding.pbLoadingDa.indeterminateDrawable = ResourcesCompat.getDrawable(resources, this.loadingDrawable, null)
        binding.txtLoadingDaTitle.isVisible = this.content != null
        if (this.content != null) {
            binding.txtLoadingDaTitle.text = this.content
            binding.txtLoadingDaTitle.visibility = View.VISIBLE
        }
        if (this.headerTitle != null) {
            binding.txtLoadingDaHeadingTitle.text = this.headerTitle
            binding.txtLoadingDaHeadingTitle.visibility = View.VISIBLE
        }
        if (this.illustration != null) {
            binding.ivLoadingDaIllustration.setImageResource(this.illustration!!)
            binding.ivLoadingDaIllustration.visibility = View.VISIBLE
        }


    }

    private fun Disposable.collect() = compositeDisposable.add(this)

    private fun startHandleLoading() {
        Observable.interval(0, LOADING_PROGRESS_PERIOD_VALUE, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (binding.pbLoadingDa.progress == LOADING_PROGRESS_MAX_VALUE) {
                    stopHandleLoading()
                    dismiss()
                    loadSuccessCallBack?.invoke()
                } else if (progressSuccess || binding.pbLoadingDa.progress <= LOADING_PROGRESS_TIME_OUT_VALUE) {
                    binding.pbLoadingDa.progress++
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