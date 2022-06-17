package com.lionparcel.commonandroid.popup

import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.popup.base.BaseDialogFragment

class LPPopupDialogFragment(private val typeModal: PopupTypeModal) : BaseDialogFragment() {

    private var primaryListener: View.OnClickListener? = null
    private var secondaryListener: View.OnClickListener? = null

    companion object {

        private const val BASIC_TITLE_GENERAL = "BASIC_TITLE_GENERAL"
        private const val BASIC_SUB_TITLE_GENERAL = "BASIC_SUB_TITLE_GENERAL"
        private const val ILLUSTRATION_IMAGE = "ILLUSTRATION_IMAGE"
        private const val PRIMARY_BUTTON = "PRIMARY_BUTTON"
        private const val SECONDARY_BUTTON = "SECONDARY_BUTTON"

        fun lpPopupTextOnly(title: String, content: String) =
            LPPopupDialogFragment(PopupTypeModal.TEXT_ONLY).apply {
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content
                )
            }

        fun lpPopupTextIllustration(title: String, content: String, image: Int) =
            LPPopupDialogFragment(PopupTypeModal.TEXT_ILLUSTRATION).apply {
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content,
                    ILLUSTRATION_IMAGE to image
                )
            }

        fun lpPopupTextButton(
            title: String,
            content: String,
            primaryButtonText: String,
            secondaryButtonText: String,
            primaryButtonListener : View.OnClickListener? = null,
            secondaryButtonListener : View.OnClickListener? = null
        ) =
            LPPopupDialogFragment(PopupTypeModal.TEXT_BUTTON).apply {
                primaryListener = primaryButtonListener?: View.OnClickListener {dialog?.dismiss()}
                secondaryListener = secondaryButtonListener?: View.OnClickListener {dialog?.dismiss()}
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content,
                    PRIMARY_BUTTON to primaryButtonText,
                    SECONDARY_BUTTON to secondaryButtonText
                )
            }

        fun lpPopupTextButtonIllustration(
            title: String,
            content: String,
            image: Int,
            primaryButtonText: String,
            secondaryButtonText: String,
            primaryButtonListener : View.OnClickListener? = null,
            secondaryButtonListener : View.OnClickListener? = null
        ) =
            LPPopupDialogFragment(PopupTypeModal.TEXT_BUTTON_ILLUSTRATION).apply {
                primaryListener = primaryButtonListener?: View.OnClickListener {dialog?.dismiss()}
                secondaryListener = secondaryButtonListener?: View.OnClickListener {dialog?.dismiss()}
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content,
                    ILLUSTRATION_IMAGE to image,
                    PRIMARY_BUTTON to primaryButtonText,
                    SECONDARY_BUTTON to secondaryButtonText
                )
            }
    }

    override fun getContentResource(): Int = R.layout.lp_custom_popup_dialog_fragment

    override fun initViews() {
        super.initViews()
        setWidthPercent(90)
        when (this.typeModal) {
            PopupTypeModal.TEXT_ONLY -> prepareViewTextOnly()
            PopupTypeModal.TEXT_ILLUSTRATION -> prepareViewTextWithIllustration()
            PopupTypeModal.TEXT_BUTTON -> prepareViewTextWithButton()
            PopupTypeModal.TEXT_BUTTON_ILLUSTRATION -> prepareViewTextButtonIllustration()
        }
    }

    private fun prepareViewTextOnly() {
        val title = view?.findViewById<TextView>(R.id.txt_popup_title)
        val content = view?.findViewById<TextView>(R.id.txt_popup_content)
        val close = view?.findViewById<ImageButton>(R.id.img_btn_popup_close)
        val spacingTitle = view?.findViewById<View>(R.id.vw_popup_spacing_title)
        title?.isVisible = true
        content?.isVisible = true
        close?.isVisible = true
        spacingTitle?.isVisible = true
        close?.setOnClickListener {
            dismiss()
        }
        arguments?.getString(BASIC_TITLE_GENERAL)?.let { data ->
            title?.text = data
        }
        arguments?.getString(BASIC_SUB_TITLE_GENERAL)?.let { data ->
            content?.text = data
        }
    }

    private fun prepareViewTextWithIllustration() {
        val illustration = view?.findViewById<ImageView>(R.id.iv_popup_illustration)
        val title = view?.findViewById<TextView>(R.id.txt_popup_title)
        val content = view?.findViewById<TextView>(R.id.txt_popup_content)
        val close = view?.findViewById<ImageButton>(R.id.img_btn_popup_close)
        val spacingTitle = view?.findViewById<View>(R.id.vw_popup_spacing_title)
        val spacingIllustration = view?.findViewById<View>(R.id.vw_popup_spacing_illustration)
        illustration?.isVisible = true
        title?.isVisible = true
        content?.isVisible = true
        close?.isVisible = true
        spacingTitle?.isVisible = true
        spacingIllustration?.isVisible = true
        close?.setOnClickListener {
            dismiss()
        }
        arguments?.getString(BASIC_TITLE_GENERAL)?.let { data ->
            title?.text = data
        }
        arguments?.getString(BASIC_SUB_TITLE_GENERAL)?.let { data ->
            content?.text = data
        }
        arguments?.getInt(ILLUSTRATION_IMAGE, -1).takeIf { value -> value != -1 }
            ?.let { value ->
                illustration?.setImageResource(value)
            }
    }

    private fun prepareViewTextWithButton() {
        val title = view?.findViewById<TextView>(R.id.txt_popup_title)
        val content = view?.findViewById<TextView>(R.id.txt_popup_content)
        val close = view?.findViewById<ImageButton>(R.id.img_btn_popup_close)
        val spacingTitle = view?.findViewById<View>(R.id.vw_popup_spacing_title)
        val primaryButton = view?.findViewById<Button>(R.id.btn_popup_primary)
        val secondaryButton = view?.findViewById<Button>(R.id.btn_popup_secondary)
        title?.isVisible = true
        content?.isVisible = true
        close?.isVisible = true
        spacingTitle?.isVisible = true
        primaryButton?.isVisible = true
        secondaryButton?.isVisible = true
        close?.setOnClickListener {
            dismiss()
        }
        primaryButton?.setOnClickListener {
            primaryListener?.onClick(it)
        }
        secondaryButton?.setOnClickListener {
            secondaryListener?.onClick(it)
        }
        arguments?.getString(BASIC_TITLE_GENERAL)?.let { data ->
            title?.text = data
        }
        arguments?.getString(BASIC_SUB_TITLE_GENERAL)?.let { data ->
            content?.text = data
        }
        arguments?.getString(PRIMARY_BUTTON)?.let { data ->
            primaryButton?.text = data
        }
        arguments?.getString(SECONDARY_BUTTON)?.let { data ->
            secondaryButton?.text = data
        }
    }

    private fun prepareViewTextButtonIllustration() {
        val illustration = view?.findViewById<ImageView>(R.id.iv_popup_illustration)
        val title = view?.findViewById<TextView>(R.id.txt_popup_title)
        val content = view?.findViewById<TextView>(R.id.txt_popup_content)
        val close = view?.findViewById<ImageButton>(R.id.img_btn_popup_close)
        val spacingTitle = view?.findViewById<View>(R.id.vw_popup_spacing_title)
        val spacingIllustration = view?.findViewById<View>(R.id.vw_popup_spacing_illustration)
        val primaryButton = view?.findViewById<Button>(R.id.btn_popup_primary)
        val secondaryButton = view?.findViewById<Button>(R.id.btn_popup_secondary)
        illustration?.isVisible = true
        title?.isVisible = true
        content?.isVisible = true
        close?.isVisible = true
        spacingTitle?.isVisible = true
        spacingIllustration?.isVisible = true
        primaryButton?.isVisible = true
        secondaryButton?.isVisible = true
        close?.setOnClickListener {
            dismiss()
        }
        primaryButton?.setOnClickListener {
            primaryListener?.onClick(it)
        }
        secondaryButton?.setOnClickListener {
            secondaryListener?.onClick(it)
        }
        arguments?.getString(BASIC_TITLE_GENERAL)?.let { data ->
            title?.text = data
        }
        arguments?.getString(BASIC_SUB_TITLE_GENERAL)?.let { data ->
            content?.text = data
        }
        arguments?.getString(PRIMARY_BUTTON)?.let { data ->
            primaryButton?.text = data
        }
        arguments?.getString(SECONDARY_BUTTON)?.let { data ->
            secondaryButton?.text = data
        }
        arguments?.getInt(ILLUSTRATION_IMAGE, -1).takeIf { value -> value != -1 }
            ?.let { value ->
                illustration?.setImageResource(value)
            }
    }
}

enum class PopupTypeModal {
    TEXT_ONLY,
    TEXT_ILLUSTRATION,
    TEXT_BUTTON,
    TEXT_BUTTON_ILLUSTRATION
}