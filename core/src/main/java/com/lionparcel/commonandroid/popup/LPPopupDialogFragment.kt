package com.lionparcel.commonandroid.popup

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.popup.base.BaseDialogFragment

class LPPopupDialogFragment : BaseDialogFragment() {

    companion object {

        fun newPopupDialog(
            title: String?,
            content: String?,
            isImageClose : Boolean? = true,
            image: Int? = null,
            primaryButtonText: String? = null,
            secondaryButtonText: String? = null,
            primaryButtonListener : ((LPPopupDialogFragment) -> Unit)? = null,
            secondaryButtonListener : ((LPPopupDialogFragment) -> Unit)? = null,
            dismissAfterClickButtonPrimary: Boolean = true,
            dismissAfterClickButtonSecondary: Boolean = true,
            cancelableTouchOutSide: Boolean = true,
            callbackOnDismiss: (() -> Unit)? = null
        ) =
            LPPopupDialogFragment().apply {

                this.title = title
                this.content = content
                this.image = image
                this.imageClose = isImageClose
                this.primaryButtonText = primaryButtonText
                this.secondaryButtonText = secondaryButtonText
                this.primaryListener = primaryButtonListener
                this.secondaryListener = secondaryButtonListener
                this.dismissAfterClickButtonPrimary = dismissAfterClickButtonPrimary
                this.dismissAfterClickButtonSecondary = dismissAfterClickButtonSecondary
                this.cancelableTouchOutSide = cancelableTouchOutSide
                this.callbackOnDismiss = callbackOnDismiss
            }
    }

    private var title : String? = null
    private var content : String? = null
    private var imageClose : Boolean? = null
    private var image : Int? = null
    private var primaryButtonText : String? = null
    private var secondaryButtonText : String? = null
    private var primaryListener: ((LPPopupDialogFragment) -> Unit)? = null
    private var secondaryListener: ((LPPopupDialogFragment) -> Unit)? = null
    private var dismissAfterClickButtonPrimary: Boolean = true
    private var dismissAfterClickButtonSecondary: Boolean = true
    private var cancelableTouchOutSide: Boolean = true
    private var callbackOnDismiss: (() -> Unit)? = null
    private var shown: Boolean = false

    override fun getContentResource(): Int = R.layout.lp_custom_popup_dialog_fragment

    override fun initViews() {
        super.initViews()
        setWidthPercent(90)
        prepareView()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawableResource(R.color.transparent)
        parentView = inflater.inflate(getContentResource(), container, false)
        return parentView
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (shown || manager.isStateSaved) return
        super.show(manager, tag)
        shown = true
    }

    override fun onDismiss(dialog: DialogInterface) {
        if (!shown) return
        super.onDismiss(dialog)
        shown = false
        callbackOnDismiss?.invoke()
    }

    private fun prepareView() {

        val illustration = view?.findViewById<ImageView>(R.id.iv_popup_illustration)
        val title = view?.findViewById<TextView>(R.id.txt_popup_title)
        val content = view?.findViewById<TextView>(R.id.txt_popup_content)
        val close = view?.findViewById<ImageButton>(R.id.img_btn_popup_close)
        val spacingTitle = view?.findViewById<View>(R.id.vw_popup_spacing_title)
        val spacingIllustration = view?.findViewById<View>(R.id.vw_popup_spacing_illustration)
        val primaryButton = view?.findViewById<Button>(R.id.btn_popup_primary)
        val secondaryButton = view?.findViewById<Button>(R.id.btn_popup_secondary)

        illustration?.isVisible = this.image != null
        title?.isVisible = this.title != null
        content?.isVisible = this.content != null
        close?.isVisible = this.imageClose == true
        spacingTitle?.isVisible = this.image == null
        spacingIllustration?.isVisible = this.image != null
        primaryButton?.isVisible = this.primaryButtonText != null
        secondaryButton?.isVisible = this.secondaryButtonText != null
        close?.setOnClickListener {
            dismiss()
        }
        title?.text = this.title
        content?.text = this.content
        this.image?.let { illustration?.setImageResource(it) }
        primaryButton?.text = this.primaryButtonText
        secondaryButton?.text = this.secondaryButtonText
        primaryButton?.setOnClickListener {
            primaryListener?.invoke(this)
            if (dismissAfterClickButtonPrimary) {
                dismiss()
            }
        }
        secondaryButton?.setOnClickListener {
            secondaryListener?.invoke(this)
            if (dismissAfterClickButtonSecondary) {
                dismiss()
            }
        }
        dialog?.setCanceledOnTouchOutside(cancelableTouchOutSide)
    }
}
