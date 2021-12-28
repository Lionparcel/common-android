package com.lionparcel.commonandroid.form

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.form.utils.ImageUtils
import kotlinx.android.synthetic.main.add_photo_view.view.*
import kotlinx.android.synthetic.main.attach_file_view.view.*
import java.io.File

class LPAttachFile : FrameLayout {

    var fileUri: Uri? = null

    var selectedImage: File? = null

    var activity: Activity? = null

    var previewPhoto: ((File) -> Unit)? = null

    var gotoCamera: ((Uri) -> Unit)? = null

    var gotoGallery: ((Uri) -> Unit)? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.attach_file_view, this, true)

        with(includeKtpPhoto) {
            llEmptyPhoto.setOnClickListener {
                addPackagePhotoOptional()
            }
            txtPhotoAction.setOnClickListener {
                addPackagePhotoOptional()
            }
            imgPhoto.setOnClickListener {
                selectedImage?.let { photo ->
                    previewPhoto?.invoke(photo)
                }
            }
        }
    }

    private fun addPackagePhotoOptional() {
        executeSelectImage({
            gotoCamera()
        }, {
            gotoGallery()
        })
    }

    private fun gotoCamera() {
        activity?.let {
            fileUri = ImageUtils.getOutputMediaFileUri(
                activity = it,
                resources.getString(R.string.claim_form_page_photo_ktp_form)
            )
        }
    }

    private fun gotoGallery() {
        activity?.let {
            fileUri = ImageUtils.getOutputMediaFileUri(
                activity = it,
                resources.getString(R.string.claim_form_select_image_title)
            )
        }
    }

    private fun executeSelectImage(gotoCamera: () -> Unit, gotoGallery: () -> Unit) {
        handleSelectImage(gotoCamera, gotoGallery)
    }

    private fun handleSelectImage(gotoCamera: () -> Unit, gotoGallery: () -> Unit) {
        val options = resources.getStringArray(R.array.claim_form_select_image_array)
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.claim_form_select_image_title)
        builder.setItems(options) { dialog, item ->
            when (item) {
                0 -> {
                    gotoCamera.invoke()
                }
                1 -> {
                    gotoGallery.invoke()
                }
            }
            dialog.dismiss()
        }
        builder.show()
    }

//    private fun handlePhotoActionClicked(containerView: View, addPhotoCallback: () -> Unit) {
//        if (containerView.txtPhotoAction.text == resources.getString(R.string.general_change)) {
//            addPhotoCallback.invoke()
//        } else resetViewPhotoDetail(containerView)
//    }
//
//    private fun resetViewPhotoDetail(containerView: View) {
//        val mapKey = getMapKeyByContainerView(containerView)
//        containerView.llEmptyPhoto.isVisible = true
//        containerView.clPhotoDetail.isVisible = false
//        changeStateViewPhotoField(false, containerView)
//        containerView.txtPhotoName.text = String()
//        containerView.txtPhotoSize.text = String()
//        containerView.txtPhotoAction.text = resources.getString(R.string.general_change)
//    }
//
//    private fun getMapKeyByContainerView(containerView: View): String {
//        return when (containerView) {
//            includeKtpPhoto -> KTP_PHOTO_KEY
//            includePackagePhotoOptional -> PACKAGE_PHOTO_OPTIONAL_KEY
//            else -> PACKAGE_PHOTO_REQUIRED_KEY
//        }
//    }

    fun changeStateViewPhotoField(
        isError: Boolean,
        containerView: View
    ) {
        val textColor = if (isError) R.color.interpack6 else R.color.shades3
        val maxPhotoLabel = when {
            isError && !containerView.llEmptyPhoto.isVisible -> resources.getString(R.string.form_claim_damaged_or_lost_photo_invalid_error)
            else -> resources.getString(R.string.form_claim_damaged_or_lost_max_spec_photo_label)
        }
        val requiredLabel = when {
            isError && containerView.llEmptyPhoto.isVisible -> resources.getString(R.string.form_claim_damaged_or_lost_photo_empty_error)
            else -> resources.getString(R.string.form_claim_damaged_or_lost_required_label)
        }
        containerView.isSelected = isError
        when (containerView) {
            llKtpPhoto -> {
                txtKtpPhotoLabel.changeTextColor(textColor)
                txtKtpPhotoMaxLabel.changeTextAndColor(maxPhotoLabel, textColor)
                txtRequiredKtpPhotoLabel.changeTextAndColor(requiredLabel, textColor)
            }
        }
    }

    private fun TextView.changeTextColor(color: Int) {
        setTextColor(ContextCompat.getColor(context, color))
    }

    private fun TextView.changeTextAndColor(textString: String, color: Int) {
        text = textString
        changeTextColor(color)
    }
}