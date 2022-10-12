package com.lionparcel.commonandroid.modal

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
    private var customList: ViewGroup? = null
    private var listItem: List<String> = listOf()
    private var selectedItem: Int = 0

    private val adapter by lazy {
        ExampleModalListAdapter()
    }

    companion object {

        private const val BASIC_TITLE_GENERAL = "BASIC_TITLE_GENERAL"
        private const val BASIC_SUB_TITLE_GENERAL = "BASIC_SUB_TITLE_GENERAL"
        private const val ILLUSTRATION_IMAGE = "ILLUSTRATION_IMAGE"
        private const val BASIC_PRIMARY_BUTTON = "BASIC_PRIMARY_BUTTON"
        private const val BASIC_SECONDARY_BUTTON = "BASIC_SECONDARY_BUTTON"

        fun lpModalBasicTextOnly(title: String, content: String) =
            LPModalDialogFragment(TypeModal.BASIC_TEXT_ONLY).apply {
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content
                )
            }

        fun lpModalBasic1Button(title: String, content: String, primaryButtonTitle: String, btnPrimaryListener: View.OnClickListener? = null) =
            LPModalDialogFragment(TypeModal.BASIC_1_BUTTON).apply {
                primaryListener = btnPrimaryListener?: View.OnClickListener{ dialog?.dismiss() }
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content,
                    BASIC_PRIMARY_BUTTON to primaryButtonTitle
                )
            }

        fun lpModalBasic2Button(title: String, content: String, primaryButtonTitle: String, secondaryButtonTitle: String, btnPrimaryListener: View.OnClickListener? = null, btnSecondListener: View.OnClickListener? = null) =
            LPModalDialogFragment(TypeModal.BASIC_2_BUTTON).apply {
                primaryListener = btnPrimaryListener?: View.OnClickListener{ dialog?.dismiss() }
                secondListener = btnSecondListener?: View.OnClickListener{ dialog?.dismiss() }
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content,
                    BASIC_PRIMARY_BUTTON to primaryButtonTitle,
                    BASIC_SECONDARY_BUTTON to secondaryButtonTitle
                )
            }

        fun lpModalBasic2ButtonAlt(title: String, content: String, primaryButtonTitle: String, secondaryButtonTitle: String, btnPrimaryListener: View.OnClickListener? = null, btnSecondListener: View.OnClickListener? = null) =
            LPModalDialogFragment(TypeModal.BASIC_2_BUTTON_ALT).apply {
                primaryListener = btnPrimaryListener?: View.OnClickListener{ dialog?.dismiss() }
                secondListener = btnSecondListener?: View.OnClickListener{ dialog?.dismiss() }
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content,
                    BASIC_PRIMARY_BUTTON to primaryButtonTitle,
                    BASIC_SECONDARY_BUTTON to secondaryButtonTitle
                )
            }

        fun lpModalBasicBackIcon(title: String, content: String, btnPrimaryListener: View.OnClickListener? = null, backListener: View.OnClickListener? = null) =
            LPModalDialogFragment(TypeModal.BASIC_BACK_ICON).apply {
                this.backListener = backListener?: View.OnClickListener{ dialog?.dismiss() }
                primaryListener = btnPrimaryListener?: View.OnClickListener { dialog?.dismiss() }
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content
                )
            }

        fun lpModalBasicFilter(title: String, content: String, primaryButtonTitle: String, btnPrimaryListener: View.OnClickListener? = null, resetListener: View.OnClickListener? = null, listItem: List<String>, selectedItem: Int) =
            LPModalDialogFragment(TypeModal.BASIC_FILTER_MODAL).apply {
                this.resetListener = resetListener?: View.OnClickListener{ dialog?.dismiss() }
                primaryListener = btnPrimaryListener?: View.OnClickListener { dialog?.dismiss() }
                this.listItem = listItem
                this.selectedItem = selectedItem
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content,
                    BASIC_PRIMARY_BUTTON to primaryButtonTitle
                )
            }

        fun lpModalIllustration1Button(title: String, content: String, primaryButtonTitle: String, btnPrimaryListener: View.OnClickListener? = null, image: Int) =
            LPModalDialogFragment(TypeModal.ILLUSTRATION_1_BUTTON).apply {
                primaryListener = btnPrimaryListener?: View.OnClickListener{ dialog?.dismiss() }
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content,
                    ILLUSTRATION_IMAGE to image,
                    BASIC_PRIMARY_BUTTON to primaryButtonTitle
                )
            }

        fun lpModalIllustration2Button(title: String, content: String, primaryButtonTitle: String, secondaryButtonTitle: String, btnPrimaryListener: View.OnClickListener? = null, btnSecondListener: View.OnClickListener? = null, image: Int) =
            LPModalDialogFragment(TypeModal.ILLUSTRATION_2_BUTTON).apply {
                primaryListener = btnPrimaryListener?: View.OnClickListener{ dialog?.dismiss() }
                secondListener = btnSecondListener?: View.OnClickListener{ dialog?.dismiss() }
                arguments = bundleOf(
                    BASIC_TITLE_GENERAL to title,
                    BASIC_SUB_TITLE_GENERAL to content,
                    ILLUSTRATION_IMAGE to image,
                    BASIC_PRIMARY_BUTTON to primaryButtonTitle,
                    BASIC_SECONDARY_BUTTON to secondaryButtonTitle
                )
            }

        fun lpModalIllustration2ButtonAlt(title: String, content: String, primaryButtonTitle: String, secondaryButtonTitle: String, btnPrimaryListener: View.OnClickListener? = null, btnSecondListener: View.OnClickListener? = null, image: Int) =
            LPModalDialogFragment(TypeModal.ILLUSTRATION_2_BUTTON_ALT).apply {
                primaryListener = btnPrimaryListener?: View.OnClickListener{ dialog?.dismiss() }
                secondListener = btnSecondListener?: View.OnClickListener{ dialog?.dismiss() }
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
        }
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
        }
        secondButton?.setOnClickListener {
            primaryListener?.onClick(it)
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
        }
        secondButton?.setOnClickListener {
            primaryListener?.onClick(it)
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
        }
        arguments?.getString(BASIC_TITLE_GENERAL)?.let { data ->
            title?.text = data
        }
        arguments?.getString(BASIC_PRIMARY_BUTTON)?.let { data ->
            primaryButton?.text = data
        }
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
        }
        close?.setOnClickListener {
            dismiss()
        }
        arguments?.getString(BASIC_TITLE_GENERAL)?.let { data ->
            title?.text = data
        }
        arguments?.getInt(ILLUSTRATION_IMAGE, -1).takeIf { value -> value != -1 }
            ?.let { value ->
                image?.setImageResource(value)
            }
        arguments?.getString(BASIC_PRIMARY_BUTTON)?.let { data ->
            primaryButton?.text = data
        }
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
        }
        secondButton?.setOnClickListener {
            primaryListener?.onClick(it)
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
        }
        secondButton?.setOnClickListener {
            primaryListener?.onClick(it)
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
    ILLUSTRATION_2_BUTTON_ALT
}
