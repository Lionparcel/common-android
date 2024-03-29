package com.lionparcel.commonandroid.modal

import android.content.DialogInterface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.modal.base.BaseSheetDialogFragment


class LPModalDialogFragment(private val typeModal: TypeModal) : BaseSheetDialogFragment() {

    override fun getContentResource() = R.layout.lp_custom_modal_dialog_fragment

    private var primaryListener: View.OnClickListener? = null
    private var secondListener: View.OnClickListener? = null
    private var resetListener: View.OnClickListener? = null
    private var backListener: View.OnClickListener? = null
    private var callbackOnDismiss: (() -> Unit)? = null
    private var customList: ViewGroup? = null
    private var listItem: List<String> = listOf()
    private var selectedItem: Int = 0
    private var dismissAfterClickButtonPrimary: Boolean = true
    private var dismissAfterClickButtonSecondary: Boolean = true
    private var cancelableTouchOutSide: Boolean = true

    private val adapter by lazy {
        ExampleModalListAdapter()
    }

    companion object {

        private const val BASIC_TITLE_GENERAL = "BASIC_TITLE_GENERAL"
        private const val BASIC_SUB_TITLE_GENERAL = "BASIC_SUB_TITLE_GENERAL"
        private const val ILLUSTRATION_IMAGE = "ILLUSTRATION_IMAGE"
        private const val BASIC_PRIMARY_BUTTON = "BASIC_PRIMARY_BUTTON"
        private const val BASIC_SECONDARY_BUTTON = "BASIC_SECONDARY_BUTTON"

        fun lpModalBasicTextOnly(title: String, content: String, cancelableTouchOutside: Boolean = true, callbackOnDismissListener: (() -> Unit)? = null) =
            LPModalDialogFragment(TypeModal.BASIC_TEXT_ONLY).apply {
                this.callbackOnDismiss = callbackOnDismissListener
                this.cancelableTouchOutSide = cancelableTouchOutside
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content
                )
            }

        fun lpModalBasic1Button(title: String, content: String, primaryButtonTitle: String, primaryButtonDismissAfterClick: Boolean = true, btnPrimaryListener: View.OnClickListener? = null, cancelableTouchOutside: Boolean = true, callbackOnDismissListener: (() -> Unit)? = null) =
            LPModalDialogFragment(TypeModal.BASIC_1_BUTTON).apply {
                this.primaryListener = btnPrimaryListener?: View.OnClickListener{ dialog?.dismiss() }
                this.callbackOnDismiss = callbackOnDismissListener
                this.dismissAfterClickButtonPrimary = primaryButtonDismissAfterClick
                this.cancelableTouchOutSide = cancelableTouchOutside
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content,
                    BASIC_PRIMARY_BUTTON to primaryButtonTitle
                )
            }

        fun lpModalBasic2Button(title: String, content: String, primaryButtonTitle: String, secondaryButtonTitle: String, primaryButtonDismissAfterClick: Boolean = true, secondaryButtonDismissAfterClick: Boolean = true, btnPrimaryListener: View.OnClickListener? = null, btnSecondListener: View.OnClickListener? = null, cancelableTouchOutside: Boolean = true, callbackOnDismissListener: (() -> Unit)? = null) =
            LPModalDialogFragment(TypeModal.BASIC_2_BUTTON).apply {
                this.primaryListener = btnPrimaryListener?: View.OnClickListener{ dialog?.dismiss() }
                this.secondListener = btnSecondListener?: View.OnClickListener{ dialog?.dismiss() }
                this.callbackOnDismiss = callbackOnDismissListener
                this.dismissAfterClickButtonPrimary = primaryButtonDismissAfterClick
                this.dismissAfterClickButtonSecondary = secondaryButtonDismissAfterClick
                this.cancelableTouchOutSide = cancelableTouchOutside
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content,
                    BASIC_PRIMARY_BUTTON to primaryButtonTitle,
                    BASIC_SECONDARY_BUTTON to secondaryButtonTitle
                )
            }

        fun lpModalBasic2ButtonAlt(title: String, content: String, primaryButtonTitle: String, secondaryButtonTitle: String, primaryButtonDismissAfterClick: Boolean = true, secondaryButtonDismissAfterClick: Boolean = true, btnPrimaryListener: View.OnClickListener? = null, btnSecondListener: View.OnClickListener? = null, cancelableTouchOutside: Boolean = true, callbackOnDismissListener: (() -> Unit)? = null) =
            LPModalDialogFragment(TypeModal.BASIC_2_BUTTON_ALT).apply {
                this.primaryListener = btnPrimaryListener?: View.OnClickListener{ dialog?.dismiss() }
                this.secondListener = btnSecondListener?: View.OnClickListener{ dialog?.dismiss() }
                this.callbackOnDismiss = callbackOnDismissListener
                this.dismissAfterClickButtonPrimary = primaryButtonDismissAfterClick
                this.dismissAfterClickButtonSecondary = secondaryButtonDismissAfterClick
                this.cancelableTouchOutSide = cancelableTouchOutside
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content,
                    BASIC_PRIMARY_BUTTON to primaryButtonTitle,
                    BASIC_SECONDARY_BUTTON to secondaryButtonTitle
                )
            }

        fun lpModalBasicBackIcon(title: String, content: String, primaryButtonTitle: String, primaryButtonDismissAfterClick: Boolean = true, btnPrimaryListener: View.OnClickListener? = null, backListener: View.OnClickListener? = null, cancelableTouchOutside: Boolean = true, callbackOnDismissListener: (() -> Unit)? = null) =
            LPModalDialogFragment(TypeModal.BASIC_BACK_ICON).apply {
                this.backListener = backListener?: View.OnClickListener{ dialog?.dismiss() }
                this.primaryListener = btnPrimaryListener?: View.OnClickListener { dialog?.dismiss() }
                this.callbackOnDismiss = callbackOnDismissListener
                this.dismissAfterClickButtonPrimary = primaryButtonDismissAfterClick
                this.cancelableTouchOutSide = cancelableTouchOutside
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content,
                    BASIC_PRIMARY_BUTTON to primaryButtonTitle
                )
            }

        fun lpModalBasicFilter(title: String, content: String, primaryButtonTitle: String, primaryButtonDismissAfterClick: Boolean = true, btnPrimaryListener: View.OnClickListener? = null, resetListener: View.OnClickListener? = null, listItem: List<String>, selectedItem: Int, cancelableTouchOutside: Boolean = true, callbackOnDismissListener: (() -> Unit)? = null) =
            LPModalDialogFragment(TypeModal.BASIC_FILTER_MODAL).apply {
                this.resetListener = resetListener?: View.OnClickListener{ dialog?.dismiss() }
                this.primaryListener = btnPrimaryListener?: View.OnClickListener { dialog?.dismiss() }
                this.callbackOnDismiss = callbackOnDismissListener
                this.listItem = listItem
                this.selectedItem = selectedItem
                this.cancelableTouchOutSide = cancelableTouchOutside
                dismissAfterClickButtonPrimary = primaryButtonDismissAfterClick
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content,
                    BASIC_PRIMARY_BUTTON to primaryButtonTitle
                )
            }

        fun lpModalIllustration1Button(title: String, content: String, primaryButtonTitle: String, primaryButtonDismissAfterClick: Boolean = true, btnPrimaryListener: View.OnClickListener? = null, image: Int, cancelableTouchOutside: Boolean = true, callbackOnDismissListener: (() -> Unit)? = null) =
            LPModalDialogFragment(TypeModal.ILLUSTRATION_1_BUTTON).apply {
                this.primaryListener = btnPrimaryListener?: View.OnClickListener{ dialog?.dismiss() }
                this.dismissAfterClickButtonPrimary = primaryButtonDismissAfterClick
                this.callbackOnDismiss = callbackOnDismissListener
                this.cancelableTouchOutSide = cancelableTouchOutside
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content,
                    ILLUSTRATION_IMAGE to image,
                    BASIC_PRIMARY_BUTTON to primaryButtonTitle
                )
            }

        fun lpModalIllustration2Button(title: String, content: String, primaryButtonTitle: String, secondaryButtonTitle: String, primaryButtonDismissAfterClick: Boolean = true, secondaryButtonDismissAfterClick: Boolean = true, btnPrimaryListener: View.OnClickListener? = null, btnSecondListener: View.OnClickListener? = null, image: Int, cancelableTouchOutside: Boolean = true, callbackOnDismissListener: (() -> Unit)? = null) =
            LPModalDialogFragment(TypeModal.ILLUSTRATION_2_BUTTON).apply {
                this.primaryListener = btnPrimaryListener?: View.OnClickListener{ dialog?.dismiss() }
                this.secondListener = btnSecondListener?: View.OnClickListener{ dialog?.dismiss() }
                this.callbackOnDismiss = callbackOnDismissListener
                this.dismissAfterClickButtonPrimary = primaryButtonDismissAfterClick
                this.dismissAfterClickButtonSecondary = secondaryButtonDismissAfterClick
                this.cancelableTouchOutSide = cancelableTouchOutside
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content,
                    ILLUSTRATION_IMAGE to image,
                    BASIC_PRIMARY_BUTTON to primaryButtonTitle,
                    BASIC_SECONDARY_BUTTON to secondaryButtonTitle
                )
            }

        fun lpModalIllustration2ButtonAlt(title: String, content: String, primaryButtonTitle: String, secondaryButtonTitle: String, primaryButtonDismissAfterClick: Boolean = true, secondaryButtonDismissAfterClick: Boolean = true, btnPrimaryListener: View.OnClickListener? = null, btnSecondListener: View.OnClickListener? = null, image: Int, cancelableTouchOutside: Boolean = true, callbackOnDismissListener: (() -> Unit)? = null) =
            LPModalDialogFragment(TypeModal.ILLUSTRATION_2_BUTTON_ALT).apply {
                this.primaryListener = btnPrimaryListener?: View.OnClickListener{ dialog?.dismiss() }
                this.secondListener = btnSecondListener?: View.OnClickListener{ dialog?.dismiss() }
                this.callbackOnDismiss = callbackOnDismissListener
                this.dismissAfterClickButtonPrimary = primaryButtonDismissAfterClick
                this.dismissAfterClickButtonSecondary = secondaryButtonDismissAfterClick
                this.cancelableTouchOutSide = cancelableTouchOutside
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content,
                    ILLUSTRATION_IMAGE to image,
                    BASIC_PRIMARY_BUTTON to primaryButtonTitle,
                    BASIC_SECONDARY_BUTTON to secondaryButtonTitle
                )
            }

        fun lpModalBasicBackIcon2Button(title: String, content: String, primaryButtonTitle: String, secondaryButtonTitle: String, primaryButtonDismissAfterClick: Boolean = true, secondaryButtonDismissAfterClick: Boolean = true, btnPrimaryListener: View.OnClickListener? = null, btnSecondListener: View.OnClickListener? = null, backListener: View.OnClickListener? = null, cancelableTouchOutside: Boolean = true, callbackOnDismissListener: (() -> Unit)? = null) =
            LPModalDialogFragment(TypeModal.BASIC_BACK_ICON_2_BUTTON).apply {
                this.backListener = backListener?: View.OnClickListener{ dialog?.dismiss() }
                this.primaryListener = btnPrimaryListener?: View.OnClickListener { dialog?.dismiss() }
                this.secondListener = btnSecondListener?: View.OnClickListener{ dialog?.dismiss() }
                this.callbackOnDismiss = callbackOnDismissListener
                this.dismissAfterClickButtonPrimary = primaryButtonDismissAfterClick
                this.dismissAfterClickButtonSecondary = secondaryButtonDismissAfterClick
                this.cancelableTouchOutSide = cancelableTouchOutside
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content,
                    BASIC_PRIMARY_BUTTON to primaryButtonTitle,
                    BASIC_SECONDARY_BUTTON to secondaryButtonTitle
                )
            }

        fun lpModalBasicBackIcon2ButtonAlt(title: String, content: String, primaryButtonTitle: String, secondaryButtonTitle: String, primaryButtonDismissAfterClick: Boolean = true, secondaryButtonDismissAfterClick: Boolean = true, btnPrimaryListener: View.OnClickListener? = null, btnSecondListener: View.OnClickListener? = null, backListener: View.OnClickListener? = null, cancelableTouchOutside: Boolean = true, callbackOnDismissListener: (() -> Unit)? = null) =
            LPModalDialogFragment(TypeModal.BASIC_BACK_ICON_2_BUTTON_ALT).apply {
                this.backListener = backListener?: View.OnClickListener{ dialog?.dismiss() }
                this.primaryListener = btnPrimaryListener?: View.OnClickListener { dialog?.dismiss() }
                this.secondListener = btnSecondListener?: View.OnClickListener{ dialog?.dismiss() }
                this.callbackOnDismiss = callbackOnDismissListener
                this.dismissAfterClickButtonPrimary = primaryButtonDismissAfterClick
                this.dismissAfterClickButtonSecondary = secondaryButtonDismissAfterClick
                this.cancelableTouchOutSide = cancelableTouchOutside
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content,
                    BASIC_PRIMARY_BUTTON to primaryButtonTitle,
                    BASIC_SECONDARY_BUTTON to secondaryButtonTitle
                )
            }

        fun lpModalIllustrationBackButtonIcon1Button(title: String, content: String, primaryButtonTitle: String, primaryButtonDismissAfterClick: Boolean = true, btnPrimaryListener: View.OnClickListener? = null, image: Int, backListener: View.OnClickListener? = null, cancelableTouchOutside: Boolean = true, callbackOnDismissListener: (() -> Unit)? = null) =
            LPModalDialogFragment(TypeModal.ILLUSTRATION_BACK_ICON_1_BUTTON).apply {
                this.backListener = backListener?: View.OnClickListener{ dialog?.dismiss() }
                this.primaryListener = btnPrimaryListener?: View.OnClickListener{ dialog?.dismiss() }
                this.dismissAfterClickButtonPrimary = primaryButtonDismissAfterClick
                this.callbackOnDismiss = callbackOnDismissListener
                this.cancelableTouchOutSide = cancelableTouchOutside
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content,
                    ILLUSTRATION_IMAGE to image,
                    BASIC_PRIMARY_BUTTON to primaryButtonTitle
                )
            }

        fun lpModalIllustrationBackButtonIcon2Button(title: String, content: String, primaryButtonTitle: String, secondaryButtonTitle: String, primaryButtonDismissAfterClick: Boolean = true, secondaryButtonDismissAfterClick: Boolean = true, btnPrimaryListener: View.OnClickListener? = null, btnSecondListener: View.OnClickListener? = null, image: Int, backListener: View.OnClickListener? = null, cancelableTouchOutside: Boolean = true, callbackOnDismissListener: (() -> Unit)? = null) =
            LPModalDialogFragment(TypeModal.ILLUSTRATION_BACK_ICON_2_BUTTON).apply {
                this.backListener = backListener?: View.OnClickListener{ dialog?.dismiss() }
                this.primaryListener = btnPrimaryListener?: View.OnClickListener{ dialog?.dismiss() }
                this.secondListener = btnSecondListener?: View.OnClickListener{ dialog?.dismiss() }
                this.callbackOnDismiss = callbackOnDismissListener
                this.dismissAfterClickButtonPrimary = primaryButtonDismissAfterClick
                this.dismissAfterClickButtonSecondary = secondaryButtonDismissAfterClick
                this.cancelableTouchOutSide = cancelableTouchOutside
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content,
                    ILLUSTRATION_IMAGE to image,
                    BASIC_PRIMARY_BUTTON to primaryButtonTitle,
                    BASIC_SECONDARY_BUTTON to secondaryButtonTitle
                )
            }

        fun lpModalIllustrationBackButtonIcon2ButtonAlt(title: String, content: String, primaryButtonTitle: String, secondaryButtonTitle: String, primaryButtonDismissAfterClick: Boolean = true, secondaryButtonDismissAfterClick: Boolean = true, btnPrimaryListener: View.OnClickListener? = null, btnSecondListener: View.OnClickListener? = null, image: Int, cancelableTouchOutside: Boolean = true, backListener: View.OnClickListener? = null, callbackOnDismissListener: (() -> Unit)? = null) =
            LPModalDialogFragment(TypeModal.ILLUSTRATION_BACK_ICON_2_BUTTON_ALT).apply {
                this.backListener = backListener?: View.OnClickListener{ dialog?.dismiss() }
                this.primaryListener = btnPrimaryListener?: View.OnClickListener{ dialog?.dismiss() }
                this.secondListener = btnSecondListener?: View.OnClickListener{ dialog?.dismiss() }
                this.callbackOnDismiss = callbackOnDismissListener
                this.dismissAfterClickButtonPrimary = primaryButtonDismissAfterClick
                this.dismissAfterClickButtonSecondary = secondaryButtonDismissAfterClick
                this.cancelableTouchOutSide = cancelableTouchOutside
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content,
                    ILLUSTRATION_IMAGE to image,
                    BASIC_PRIMARY_BUTTON to primaryButtonTitle,
                    BASIC_SECONDARY_BUTTON to secondaryButtonTitle
                )
            }

    }

    override fun initViews() {
        super.initViews()
        getLayoutBehaviour().peekHeight = setMaxDefaultHeight(requireContext())
        when (this.typeModal) {
            TypeModal.BASIC_TEXT_ONLY -> prepareViewBasicTextOnly()
            TypeModal.BASIC_1_BUTTON -> prepareViewBasic1Button()
            TypeModal.BASIC_2_BUTTON -> prepareViewBasic2Button()
            TypeModal.BASIC_2_BUTTON_ALT -> prepareViewBasic2ButtonAlt()
            TypeModal.BASIC_BACK_ICON -> prepareViewBasicBackIcon()
            TypeModal.BASIC_FILTER_MODAL -> prepareViewBasicFilter()
            TypeModal.ILLUSTRATION_1_BUTTON -> prepareViewIllustration1Button()
            TypeModal.ILLUSTRATION_2_BUTTON -> prepareViewIllustration2Button()
            TypeModal.ILLUSTRATION_2_BUTTON_ALT -> prepareViewIllustration2ButtonAlt()
            TypeModal.BASIC_BACK_ICON_2_BUTTON -> prepareViewBasicBackIcon2Button()
            TypeModal.BASIC_BACK_ICON_2_BUTTON_ALT -> prepareViewBasicBackIcon2ButtonAlt()
            TypeModal.ILLUSTRATION_BACK_ICON_1_BUTTON -> prepareViewBackButtonIconIllustration1Button()
            TypeModal.ILLUSTRATION_BACK_ICON_2_BUTTON -> prepareViewBackButtonIconIllustration2Button()
            TypeModal.ILLUSTRATION_BACK_ICON_2_BUTTON_ALT -> prepareViewBackButtonIconIllustration2ButtonAlt()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        callbackOnDismiss?.invoke()
    }

    private fun prepareViewBasicTextOnly() {
        val title = view?.findViewById<TextView>(R.id.tvBasicTitleGeneral)
        val content = view?.findViewById<TextView>(R.id.tvSubtitleGeneral)
        val close = view?.findViewById<ImageView>(R.id.ivCloseModal)
        title?.isVisible = true
        content?.isVisible = true
        close?.isVisible = true
        content?.gravity = Gravity.START
        close?.setOnClickListener {
            dismiss()
        }
        arguments?.getString(BASIC_TITLE_GENERAL)?.let { data ->
            title?.text = data
        }
        arguments?.getString(BASIC_SUB_TITLE_GENERAL)?.let { data ->
            content?.text = data
        }
        dialog?.setCanceledOnTouchOutside(cancelableTouchOutSide)
    }

    private fun prepareViewBasic1Button() {
        val title = view?.findViewById<TextView>(R.id.tvBasicTitleGeneral)
        val content = view?.findViewById<TextView>(R.id.tvSubtitleGeneral)
        val close = view?.findViewById<ImageView>(R.id.ivCloseModal)
        val primaryButton = view?.findViewById<Button>(R.id.btnSinglePrimary)
        title?.isVisible = true
        content?.isVisible = true
        close?.isVisible = true
        primaryButton?.isVisible = true
        content?.gravity = Gravity.START
        primaryButton?.setOnClickListener {
            primaryListener?.onClick(it)
            if (dismissAfterClickButtonPrimary) {
                dismiss()
            }
        }
        close?.setOnClickListener {
            dismiss()
        }
        arguments?.getString(BASIC_TITLE_GENERAL)?.let { data ->
            title?.text = data
        }
        arguments?.getString(BASIC_SUB_TITLE_GENERAL)?.let { data ->
            content?.text = data
        }
        arguments?.getString(BASIC_PRIMARY_BUTTON)?.let { data ->
            primaryButton?.text = data
        }
        dialog?.setCanceledOnTouchOutside(cancelableTouchOutSide)
    }

    private fun prepareViewBasic2Button() {
        val title = view?.findViewById<TextView>(R.id.tvBasicTitleGeneral)
        val content = view?.findViewById<TextView>(R.id.tvSubtitleGeneral)
        val close = view?.findViewById<ImageView>(R.id.ivCloseModal)
        val layout2ButtonHorizontal = view?.findViewById<LinearLayout>(R.id.ll2ButtonHorizontal)
        val primaryButton = view?.findViewById<Button>(R.id.btnPrimaryHorizontal)
        val secondButton = view?.findViewById<Button>(R.id.btnSecHorizontal)
        title?.isVisible = true
        content?.isVisible = true
        close?.isVisible = true
        layout2ButtonHorizontal?.isVisible = true
        content?.gravity = Gravity.START
        primaryButton?.setOnClickListener {
            primaryListener?.onClick(it)
            if (dismissAfterClickButtonPrimary) {
                dismiss()
            }
        }
        secondButton?.setOnClickListener {
            secondListener?.onClick(it)
            if (dismissAfterClickButtonSecondary) {
                dismiss()
            }
        }
        close?.setOnClickListener {
            dismiss()
        }
        arguments?.getString(BASIC_TITLE_GENERAL)?.let { data ->
            title?.text = data
        }
        arguments?.getString(BASIC_SUB_TITLE_GENERAL)?.let { data ->
            content?.text = data
        }
        arguments?.getString(BASIC_PRIMARY_BUTTON)?.let { data ->
            primaryButton?.text = data
        }
        arguments?.getString(BASIC_SECONDARY_BUTTON)?.let { data ->
            secondButton?.text = data
        }
        dialog?.setCanceledOnTouchOutside(cancelableTouchOutSide)
    }

    private fun prepareViewBasic2ButtonAlt() {
        val title = view?.findViewById<TextView>(R.id.tvBasicTitleGeneral)
        val content = view?.findViewById<TextView>(R.id.tvSubtitleGeneral)
        val close = view?.findViewById<ImageView>(R.id.ivCloseModal)
        val layout2ButtonVertical = view?.findViewById<LinearLayout>(R.id.ll2ButtonVertical)
        val primaryButton = view?.findViewById<Button>(R.id.btnPrimaryVertical)
        val secondButton = view?.findViewById<Button>(R.id.btnSecVertical)
        title?.isVisible = true
        content?.isVisible = true
        close?.isVisible = true
        layout2ButtonVertical?.isVisible = true
        content?.gravity = Gravity.START
        primaryButton?.setOnClickListener {
            primaryListener?.onClick(it)
            if (dismissAfterClickButtonPrimary) {
                dismiss()
            }
        }
        secondButton?.setOnClickListener {
            secondListener?.onClick(it)
            if (dismissAfterClickButtonSecondary) {
                dismiss()
            }
        }
        close?.setOnClickListener {
            dismiss()
        }
        arguments?.getString(BASIC_TITLE_GENERAL)?.let { data ->
            title?.text = data
        }
        arguments?.getString(BASIC_SUB_TITLE_GENERAL)?.let { data ->
            content?.text = data
        }
        arguments?.getString(BASIC_PRIMARY_BUTTON)?.let { data ->
            primaryButton?.text = data
        }
        arguments?.getString(BASIC_SECONDARY_BUTTON)?.let { data ->
            secondButton?.text = data
        }
        dialog?.setCanceledOnTouchOutside(cancelableTouchOutSide)
    }

    private fun prepareViewBasicBackIcon() {
        val title = view?.findViewById<TextView>(R.id.tvBasicTitleGeneral)
        val content = view?.findViewById<TextView>(R.id.tvSubtitleGeneral)
        val back = view?.findViewById<ImageView>(R.id.ivBasicBackIcon)
        val primaryButton = view?.findViewById<Button>(R.id.btnSinglePrimary)
        title?.isVisible = true
        content?.isVisible = true
        back?.isVisible = true
        primaryButton?.isVisible = true
        content?.gravity = Gravity.START
        primaryButton?.setOnClickListener {
            primaryListener?.onClick(it)
            if (dismissAfterClickButtonPrimary) {
                dismiss()
            }
        }
        back?.setOnClickListener {
            backListener?.onClick(it)
        }
        arguments?.getString(BASIC_TITLE_GENERAL)?.let { data ->
            title?.text = data
        }
        arguments?.getString(BASIC_SUB_TITLE_GENERAL)?.let { data ->
            content?.text = data
        }
        arguments?.getString(BASIC_PRIMARY_BUTTON)?.let { data ->
            primaryButton?.text = data
        }
        dialog?.setCanceledOnTouchOutside(cancelableTouchOutSide)
    }

    private fun prepareViewBasicFilter() {
        val title = view?.findViewById<TextView>(R.id.tvBasicTitleGeneral)
        val reset = view?.findViewById<TextView>(R.id.tvBasicFilterReset)
        val primaryButton = view?.findViewById<Button>(R.id.btnSinglePrimary)
        val list = view?.findViewById<RecyclerView>(R.id.rvBasicFilterList)
        title?.isVisible = true
        reset?.isVisible = true
        primaryButton?.isVisible = true
        list?.isVisible = true
        list?.adapter = adapter
        adapter.setData(listItem, selectedItem)
        reset?.setOnClickListener {
            resetListener?.onClick(it)
        }
        primaryButton?.setOnClickListener {
            primaryListener?.onClick(it)
            if (dismissAfterClickButtonPrimary) {
                dismiss()
            }
        }
        arguments?.getString(BASIC_TITLE_GENERAL)?.let { data ->
            title?.text = data
        }
        arguments?.getString(BASIC_PRIMARY_BUTTON)?.let { data ->
            primaryButton?.text = data
        }
        dialog?.setCanceledOnTouchOutside(cancelableTouchOutSide)
    }

    private fun prepareViewIllustration1Button() {
        val title = view?.findViewById<TextView>(R.id.tvIllustrationTitleGeneral)
        val subtitle = view?.findViewById<TextView>(R.id.tvSubtitleGeneral)
        val close = view?.findViewById<ImageView>(R.id.ivCloseModal)
        val primaryButton = view?.findViewById<Button>(R.id.btnSinglePrimary)
        val image = view?.findViewById<ImageView>(R.id.ivIllustrationGeneral)
        title?.isVisible = true
        subtitle?.isVisible = true
        image?.isVisible = true
        close?.isVisible = true
        subtitle?.gravity = Gravity.CENTER
        primaryButton?.isVisible = true
        primaryButton?.setOnClickListener {
            primaryListener?.onClick(it)
            if (dismissAfterClickButtonPrimary) {
                dismiss()
            }
        }
        close?.setOnClickListener {
            dismiss()
        }
        arguments?.getString(BASIC_TITLE_GENERAL)?.let { data ->
            title?.text = data
        }
        arguments?.getString(BASIC_SUB_TITLE_GENERAL)?.let { data ->
            subtitle?.text = data
        }
        arguments?.getInt(ILLUSTRATION_IMAGE, -1).takeIf { value -> value != -1 }
            ?.let { value ->
                image?.setImageResource(value)
            }
        arguments?.getString(BASIC_PRIMARY_BUTTON)?.let { data ->
            primaryButton?.text = data
        }
        dialog?.setCanceledOnTouchOutside(cancelableTouchOutSide)
    }

    private fun prepareViewIllustration2Button() {
        val title = view?.findViewById<TextView>(R.id.tvIllustrationTitleGeneral)
        val subtitle = view?.findViewById<TextView>(R.id.tvSubtitleGeneral)
        val close = view?.findViewById<ImageView>(R.id.ivCloseModal)
        val layout2ButtonHorizontal = view?.findViewById<LinearLayout>(R.id.ll2ButtonHorizontal)
        val primaryButton = view?.findViewById<Button>(R.id.btnPrimaryHorizontal)
        val secondButton = view?.findViewById<Button>(R.id.btnSecHorizontal)
        val image = view?.findViewById<ImageView>(R.id.ivIllustrationGeneral)
        subtitle?.gravity = Gravity.CENTER
        title?.isVisible = true
        subtitle?.isVisible = true
        close?.isVisible = true
        layout2ButtonHorizontal?.isVisible = true
        image?.isVisible = true
        primaryButton?.setOnClickListener {
            primaryListener?.onClick(it)
            if (dismissAfterClickButtonPrimary) {
                dismiss()
            }
        }
        secondButton?.setOnClickListener {
            secondListener?.onClick(it)
            if (dismissAfterClickButtonSecondary) {
                dismiss()
            }
        }
        close?.setOnClickListener {
            dismiss()
        }
        arguments?.getString(BASIC_TITLE_GENERAL)?.let { data ->
            title?.text = data
        }
        arguments?.getString(BASIC_SUB_TITLE_GENERAL)?.let { data ->
            subtitle?.text = data
        }
        arguments?.getInt(ILLUSTRATION_IMAGE, -1).takeIf { value -> value != -1 }
            ?.let { value ->
                image?.setImageResource(value)
            }
        arguments?.getString(BASIC_PRIMARY_BUTTON)?.let { data ->
            primaryButton?.text = data
        }
        arguments?.getString(BASIC_SECONDARY_BUTTON)?.let { data ->
            secondButton?.text = data
        }
        dialog?.setCanceledOnTouchOutside(cancelableTouchOutSide)
    }

    private fun prepareViewIllustration2ButtonAlt() {
        val title = view?.findViewById<TextView>(R.id.tvIllustrationTitleGeneral)
        val subtitle = view?.findViewById<TextView>(R.id.tvSubtitleGeneral)
        val close = view?.findViewById<ImageView>(R.id.ivCloseModal)
        val layout2ButtonVertical = view?.findViewById<LinearLayout>(R.id.ll2ButtonVertical)
        val primaryButton = view?.findViewById<Button>(R.id.btnPrimaryVertical)
        val secondButton = view?.findViewById<Button>(R.id.btnSecVertical)
        val image = view?.findViewById<ImageView>(R.id.ivIllustrationGeneral)
        title?.isVisible = true
        subtitle?.isVisible = true
        close?.isVisible = true
        layout2ButtonVertical?.isVisible = true
        image?.isVisible = true
        subtitle?.gravity = Gravity.CENTER
        primaryButton?.setOnClickListener {
            primaryListener?.onClick(it)
            if (dismissAfterClickButtonPrimary) {
                dismiss()
            }
        }
        secondButton?.setOnClickListener {
            secondListener?.onClick(it)
            if (dismissAfterClickButtonSecondary) {
                dismiss()
            }
        }
        close?.setOnClickListener {
            dismiss()
        }
        arguments?.getString(BASIC_TITLE_GENERAL)?.let { data ->
            title?.text = data
        }
        arguments?.getString(BASIC_SUB_TITLE_GENERAL)?.let { data ->
            subtitle?.text = data
        }
        arguments?.getInt(ILLUSTRATION_IMAGE, -1).takeIf { value -> value != -1 }
            ?.let { value ->
                image?.setImageResource(value)
            }
        arguments?.getString(BASIC_PRIMARY_BUTTON)?.let { data ->
            primaryButton?.text = data
        }
        arguments?.getString(BASIC_SECONDARY_BUTTON)?.let { data ->
            secondButton?.text = data
        }
        dialog?.setCanceledOnTouchOutside(cancelableTouchOutSide)
    }

    private fun prepareViewBasicBackIcon2Button() {
        val title = view?.findViewById<TextView>(R.id.tvBasicTitleGeneral)
        val content = view?.findViewById<TextView>(R.id.tvSubtitleGeneral)
        val back = view?.findViewById<ImageView>(R.id.ivBasicBackIcon)
        val layout2ButtonHorizontal = view?.findViewById<LinearLayout>(R.id.ll2ButtonHorizontal)
        val primaryButton = view?.findViewById<Button>(R.id.btnPrimaryHorizontal)
        val secondButton = view?.findViewById<Button>(R.id.btnSecHorizontal)
        title?.isVisible = true
        content?.isVisible = true
        back?.isVisible = true
        content?.gravity = Gravity.START
        layout2ButtonHorizontal?.isVisible = true
        primaryButton?.setOnClickListener {
            primaryListener?.onClick(it)
            if (dismissAfterClickButtonPrimary) {
                dismiss()
            }
        }
        secondButton?.setOnClickListener {
            secondListener?.onClick(it)
            if (dismissAfterClickButtonSecondary) {
                dismiss()
            }
        }
        back?.setOnClickListener {
            dismiss()
            backListener?.onClick(it)
        }
        arguments?.getString(BASIC_TITLE_GENERAL)?.let { data ->
            title?.text = data
        }
        arguments?.getString(BASIC_SUB_TITLE_GENERAL)?.let { data ->
            content?.text = data
        }
        arguments?.getString(BASIC_PRIMARY_BUTTON)?.let { data ->
            primaryButton?.text = data
        }
        arguments?.getString(BASIC_SECONDARY_BUTTON)?.let { data ->
            secondButton?.text = data
        }
        dialog?.setCanceledOnTouchOutside(cancelableTouchOutSide)
    }

    private fun prepareViewBasicBackIcon2ButtonAlt() {
        val title = view?.findViewById<TextView>(R.id.tvBasicTitleGeneral)
        val content = view?.findViewById<TextView>(R.id.tvSubtitleGeneral)
        val back = view?.findViewById<ImageView>(R.id.ivBasicBackIcon)
        val layout2ButtonVertical = view?.findViewById<LinearLayout>(R.id.ll2ButtonVertical)
        val primaryButton = view?.findViewById<Button>(R.id.btnPrimaryHorizontal)
        val secondButton = view?.findViewById<Button>(R.id.btnSecHorizontal)
        title?.isVisible = true
        content?.isVisible = true
        back?.isVisible = true
        content?.gravity = Gravity.START
        layout2ButtonVertical?.isVisible = true
        primaryButton?.setOnClickListener {
            primaryListener?.onClick(it)
            if (dismissAfterClickButtonPrimary) {
                dismiss()
            }
        }
        secondButton?.setOnClickListener {
            secondListener?.onClick(it)
            if (dismissAfterClickButtonSecondary) {
                dismiss()
            }
        }
        back?.setOnClickListener {
            dismiss()
            backListener?.onClick(it)
        }
        arguments?.getString(BASIC_TITLE_GENERAL)?.let { data ->
            title?.text = data
        }
        arguments?.getString(BASIC_SUB_TITLE_GENERAL)?.let { data ->
            content?.text = data
        }
        arguments?.getString(BASIC_PRIMARY_BUTTON)?.let { data ->
            primaryButton?.text = data
        }
        arguments?.getString(BASIC_SECONDARY_BUTTON)?.let { data ->
            secondButton?.text = data
        }
        dialog?.setCanceledOnTouchOutside(cancelableTouchOutSide)
    }

    private fun prepareViewBackButtonIconIllustration1Button() {
        val title = view?.findViewById<TextView>(R.id.tvIllustrationTitleGeneral)
        val subtitle = view?.findViewById<TextView>(R.id.tvSubtitleGeneral)
        val back = view?.findViewById<ImageView>(R.id.ivBasicBackIcon)
        val primaryButton = view?.findViewById<Button>(R.id.btnSinglePrimary)
        val image = view?.findViewById<ImageView>(R.id.ivIllustrationGeneral)
        title?.isVisible = true
        subtitle?.isVisible = true
        image?.isVisible = true
        back?.isVisible = true
        subtitle?.gravity = Gravity.CENTER
        primaryButton?.isVisible = true
        primaryButton?.setOnClickListener {
            primaryListener?.onClick(it)
            if (dismissAfterClickButtonPrimary) {
                dismiss()
            }
        }
        back?.setOnClickListener {
            dismiss()
            backListener?.onClick(it)
        }
        arguments?.getString(BASIC_TITLE_GENERAL)?.let { data ->
            title?.text = data
        }
        arguments?.getString(BASIC_SUB_TITLE_GENERAL)?.let { data ->
            subtitle?.text = data
        }
        arguments?.getInt(ILLUSTRATION_IMAGE, -1).takeIf { value -> value != -1 }
            ?.let { value ->
                image?.setImageResource(value)
            }
        arguments?.getString(BASIC_PRIMARY_BUTTON)?.let { data ->
            primaryButton?.text = data
        }
        dialog?.setCanceledOnTouchOutside(cancelableTouchOutSide)
    }

    private fun prepareViewBackButtonIconIllustration2Button() {
        val title = view?.findViewById<TextView>(R.id.tvIllustrationTitleGeneral)
        val subtitle = view?.findViewById<TextView>(R.id.tvSubtitleGeneral)
        val back = view?.findViewById<ImageView>(R.id.ivBasicBackIcon)
        val layout2ButtonHorizontal = view?.findViewById<LinearLayout>(R.id.ll2ButtonHorizontal)
        val primaryButton = view?.findViewById<Button>(R.id.btnPrimaryHorizontal)
        val secondButton = view?.findViewById<Button>(R.id.btnSecHorizontal)
        val image = view?.findViewById<ImageView>(R.id.ivIllustrationGeneral)
        subtitle?.gravity = Gravity.CENTER
        title?.isVisible = true
        subtitle?.isVisible = true
        back?.isVisible = true
        layout2ButtonHorizontal?.isVisible = true
        image?.isVisible = true
        primaryButton?.setOnClickListener {
            primaryListener?.onClick(it)
            if (dismissAfterClickButtonPrimary) {
                dismiss()
            }
        }
        secondButton?.setOnClickListener {
            secondListener?.onClick(it)
            if (dismissAfterClickButtonSecondary) {
                dismiss()
            }
        }
        back?.setOnClickListener {
            dismiss()
            backListener?.onClick(it)
        }
        arguments?.getString(BASIC_TITLE_GENERAL)?.let { data ->
            title?.text = data
        }
        arguments?.getString(BASIC_SUB_TITLE_GENERAL)?.let { data ->
            subtitle?.text = data
        }
        arguments?.getInt(ILLUSTRATION_IMAGE, -1).takeIf { value -> value != -1 }
            ?.let { value ->
                image?.setImageResource(value)
            }
        arguments?.getString(BASIC_PRIMARY_BUTTON)?.let { data ->
            primaryButton?.text = data
        }
        arguments?.getString(BASIC_SECONDARY_BUTTON)?.let { data ->
            secondButton?.text = data
        }
        dialog?.setCanceledOnTouchOutside(cancelableTouchOutSide)
    }

    private fun prepareViewBackButtonIconIllustration2ButtonAlt() {
        val title = view?.findViewById<TextView>(R.id.tvIllustrationTitleGeneral)
        val subtitle = view?.findViewById<TextView>(R.id.tvSubtitleGeneral)
        val back = view?.findViewById<ImageView>(R.id.ivBasicBackIcon)
        val layout2ButtonVertical = view?.findViewById<LinearLayout>(R.id.ll2ButtonVertical)
        val primaryButton = view?.findViewById<Button>(R.id.btnPrimaryVertical)
        val secondButton = view?.findViewById<Button>(R.id.btnSecVertical)
        val image = view?.findViewById<ImageView>(R.id.ivIllustrationGeneral)
        title?.isVisible = true
        subtitle?.isVisible = true
        back?.isVisible = true
        layout2ButtonVertical?.isVisible = true
        image?.isVisible = true
        subtitle?.gravity = Gravity.CENTER
        primaryButton?.setOnClickListener {
            primaryListener?.onClick(it)
            if (dismissAfterClickButtonPrimary) {
                dismiss()
            }
        }
        secondButton?.setOnClickListener {
            secondListener?.onClick(it)
            if (dismissAfterClickButtonSecondary) {
                dismiss()
            }
        }
        back?.setOnClickListener {
            dismiss()
            backListener?.onClick(it)
        }
        arguments?.getString(BASIC_TITLE_GENERAL)?.let { data ->
            title?.text = data
        }
        arguments?.getString(BASIC_SUB_TITLE_GENERAL)?.let { data ->
            subtitle?.text = data
        }
        arguments?.getInt(ILLUSTRATION_IMAGE, -1).takeIf { value -> value != -1 }
            ?.let { value ->
                image?.setImageResource(value)
            }
        arguments?.getString(BASIC_PRIMARY_BUTTON)?.let { data ->
            primaryButton?.text = data
        }
        arguments?.getString(BASIC_SECONDARY_BUTTON)?.let { data ->
            secondButton?.text = data
        }
        dialog?.setCanceledOnTouchOutside(cancelableTouchOutSide)
    }

}

enum class TypeModal{
    BASIC_TEXT_ONLY,
    BASIC_1_BUTTON,
    BASIC_2_BUTTON,
    BASIC_2_BUTTON_ALT,
    BASIC_BACK_ICON,
    BASIC_FILTER_MODAL,
    ILLUSTRATION_1_BUTTON,
    ILLUSTRATION_2_BUTTON,
    ILLUSTRATION_2_BUTTON_ALT,
    BASIC_BACK_ICON_2_BUTTON,
    BASIC_BACK_ICON_2_BUTTON_ALT,
    ILLUSTRATION_BACK_ICON_1_BUTTON,
    ILLUSTRATION_BACK_ICON_2_BUTTON,
    ILLUSTRATION_BACK_ICON_2_BUTTON_ALT
}
