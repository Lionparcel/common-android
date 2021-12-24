package com.lionparcel.commonandroid.form

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
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

class LPAttachFile :FrameLayout {

    companion object {
        private const val KTP_PHOTO_KEY = "KTP_PHOTO_FILE_URI_KEY"
        private const val PACKAGE_PHOTO_REQUIRED_KEY = "PACKAGE_PHOTO_REQUIRED_FILE_URI_KEY"
        private const val PACKAGE_PHOTO_OPTIONAL_KEY = "PACKAGE_PHOTO_OPTIONAL_FILE_URI_KEY"
        private const val PACKAGE_GALLERY_OPTIONAL_KEY = "PACKAGE_GALLERY_OPTIONAL_FILE_URI_KEY"
        private const val REQUEST_GALLERY_PACKAGE_PHOTO_OPTIONAL = 1
        private const val REQUEST_CAMERA_PACKAGE_PHOTO_OPTIONAL = 2
    }

    private var mapFileUri = mutableMapOf<String, Uri?>(
        KTP_PHOTO_KEY to null,
        PACKAGE_PHOTO_REQUIRED_KEY to null,
        PACKAGE_PHOTO_OPTIONAL_KEY to null
    )

    private var mapSelectedImage = mutableMapOf<String, File?>(
        KTP_PHOTO_KEY to null,
        PACKAGE_PHOTO_REQUIRED_KEY to null,
        PACKAGE_PHOTO_OPTIONAL_KEY to null
    )

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0 )

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
                handlePhotoActionClicked(llPackagePhotoOptional) {
                    addPackagePhotoOptional()
                }
            }
            imgPhoto.setOnClickListener {
                mapSelectedImage[PACKAGE_PHOTO_OPTIONAL_KEY]
            }
        }

    }

    private fun addPackagePhotoOptional() {
        executeSelectImage({
            gotoCamera(PACKAGE_PHOTO_OPTIONAL_KEY, REQUEST_CAMERA_PACKAGE_PHOTO_OPTIONAL)
        }, {
            gotoGallery(PACKAGE_GALLERY_OPTIONAL_KEY, REQUEST_GALLERY_PACKAGE_PHOTO_OPTIONAL)
        })
    }

    private fun gotoCamera(mapKey: String, requestCode: Int) {
        mapFileUri[mapKey] = ImageUtils.getOutputMediaFileUri(activity = Activity(), R.string.claim_form_page_photo_ktp_form.toString())
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, mapFileUri[mapKey]).putExtra(
            PACKAGE_PHOTO_OPTIONAL_KEY, requestCode)
    }

    private fun gotoGallery(mapKey: String, requestCode: Int) {
        mapFileUri[mapKey] = ImageUtils.getOutputMediaFileUri(activity = Activity(), R.string.claim_form_page_photo_ktp_form.toString())
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, mapFileUri[mapKey]).putExtra(
            PACKAGE_GALLERY_OPTIONAL_KEY, requestCode)
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

    private fun handlePhotoActionClicked(containerView: View, addPhotoCallback: () -> Unit) {
        if (containerView.txtPhotoAction.text == resources.getString(R.string.general_change)) {
            addPhotoCallback.invoke()
        } else resetViewPhotoDetail(containerView)
    }

    private fun resetViewPhotoDetail(containerView: View) {
        val mapKey = getMapKeyByContainerView(containerView)
        containerView.llEmptyPhoto.isVisible = true
        containerView.clPhotoDetail.isVisible = false
        changeStateViewPhotoField(false, containerView)
        containerView.txtPhotoName.text = String()
        containerView.txtPhotoSize.text = String()
        containerView.txtPhotoAction.text = resources.getString(R.string.general_change)
        mapFileUri[mapKey] = null
        mapSelectedImage[mapKey] = null
        changeStateViewPackagePhotoOptional()
    }

    private fun changeStateViewPackagePhotoOptional() {
        val isEnable =
            mapSelectedImage[PACKAGE_PHOTO_REQUIRED_KEY] != null || mapSelectedImage[PACKAGE_PHOTO_OPTIONAL_KEY] != null
        includePackagePhotoOptional.llEmptyPhoto.isEnabled = isEnable
        llPackagePhotoOptional.alpha = if (isEnable) 1.0F else 0.5F
    }

    private fun getMapKeyByContainerView(containerView: View): String {
        return when (containerView) {
            includeKtpPhoto -> KTP_PHOTO_KEY
            includePackagePhotoOptional -> PACKAGE_PHOTO_OPTIONAL_KEY
            else -> PACKAGE_PHOTO_REQUIRED_KEY
        }
    }

    private fun changeStateViewPhotoField(
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

            llPackagePhotoOptional -> {
                txtPackagePhotoOptionalMaxLabel.changeTextAndColor(maxPhotoLabel, textColor)
                txtOptionalPackagePhotoLabel.changeTextColor(textColor)
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