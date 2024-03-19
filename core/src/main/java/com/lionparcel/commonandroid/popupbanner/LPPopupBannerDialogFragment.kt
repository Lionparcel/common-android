package com.lionparcel.commonandroid.popupbanner

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.common.BaseViewBinding
import com.lionparcel.commonandroid.databinding.LpLayoutPopupBannerBinding
import com.lionparcel.commonandroid.popup.base.BaseDialogFragment
import com.squareup.picasso.Picasso

class LPPopupBannerDialogFragment : BaseDialogFragment(),
    BaseViewBinding<LpLayoutPopupBannerBinding> {

    override lateinit var binding: LpLayoutPopupBannerBinding

    private var imageUrl: String? = null
    private var bannerOnClick: ((LPPopupBannerDialogFragment) -> Unit)? = null
    private var dismissAfterClickBanner: Boolean = true
    private var cancelableTouchOutSide: Boolean = true
    private var callbackOnDismiss: (() -> Unit)? = null

    companion object {
        fun newInstance(
            imageUrl: String?,
            bannerOnClick: ((LPPopupBannerDialogFragment) -> Unit)? = null,
            dismissAfterClickBanner: Boolean = true,
            cancelableTouchOutSide: Boolean = true,
            callbackOnDismiss: (() -> Unit)? = null
        ) = LPPopupBannerDialogFragment().apply {
            this.imageUrl = imageUrl
            this.bannerOnClick = bannerOnClick
            this.dismissAfterClickBanner = dismissAfterClickBanner
            this.cancelableTouchOutSide = cancelableTouchOutSide
            this.callbackOnDismiss = callbackOnDismiss
        }
    }

    override fun getContentResource() = -1

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): LpLayoutPopupBannerBinding {
        binding = LpLayoutPopupBannerBinding.inflate(inflater, container, false)
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
        val imageUrl = this.imageUrl ?: ""
        if (imageUrl.isEmpty()) dismiss() else Picasso.with(requireContext())
            .load(imageUrl)
            .into(binding.ivPopupBanner)
        binding.ivClose.setOnClickListener { dismiss() }
        binding.clPopupBannerFragment.setOnClickListener {
            bannerOnClick?.invoke(this)
            if (dismissAfterClickBanner) dismiss()
        }
        dialog?.setCanceledOnTouchOutside(cancelableTouchOutSide)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        callbackOnDismiss?.invoke()
    }
}