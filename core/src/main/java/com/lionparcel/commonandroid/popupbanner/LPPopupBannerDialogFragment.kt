package com.lionparcel.commonandroid.popupbanner

import android.content.DialogInterface
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.popup.base.BaseDialogFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.lp_layout_popup_banner.*

class LPPopupBannerDialogFragment : BaseDialogFragment() {

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

    override fun getContentResource() = R.layout.lp_layout_popup_banner

    override fun initViews() {
        super.initViews()
        val imageUrl = this.imageUrl?: ""
        if (imageUrl.isEmpty()) dismiss() else Picasso.with(requireContext())
            .load(imageUrl)
            .into(ivPopupBanner)
        ivClose.setOnClickListener { dismiss() }
        clPopupBannerFragment.setOnClickListener {
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