package com.lionparcel.commonandroid.form

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.form.utils.ImageUtils
import java.io.File

class LPAttachFile : FrameLayout {

    companion object {
        private const val KTP_PHOTO_KEY = "KTP_PHOTO_FILE_URI_KEY"
        private const val PACKAGE_PHOTO_REQUIRED_KEY = "PACKAGE_PHOTO_REQUIRED_FILE_URI_KEY"
    }

    var fileUri: Uri? = null
    var selectedImage: File? = null
    var previewPhotoCallback: ((File) -> Unit)? = null
    var gotoCameraCallback: ((Uri) -> Unit)? = null
    var gotoGalleryCallback: ((Uri) -> Unit)? = null
    var isOptional: Boolean = false
    var isPhotoEnabled: Boolean = true
    var onResetPhotoCallback: ((selectedImage: File?, fileUri: Uri?) -> Unit)? = null

    var activity: Activity? = null

    var REQUEST_CODE : Int? = null

    var clParent: ConstraintLayout
    var txtPhotoLabel: TextView
    var llPhoto: LinearLayout
    var txtPhotoInfo: TextView
    var txtPhotoRequiredLabel: TextView
    var llEmptyPhoto: LinearLayout
    var clPhotoDetail: ConstraintLayout
    var imgPhoto: ImageView
    var txtPhotoName: TextView
    var txtPhotoSize: TextView
    var txtPhotoAction: TextView

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.attach_file_view, this, true)
        clParent = findViewById(R.id.clParent)
        txtPhotoLabel = findViewById(R.id.txtPhotoLabel)
        llPhoto = findViewById(R.id.llPhoto)
        txtPhotoInfo = findViewById(R.id.txtPhotoInfo)
        txtPhotoRequiredLabel = findViewById(R.id.txtPhotoRequiredLabel)
        llEmptyPhoto = findViewById(R.id.llEmptyPhoto)
        clPhotoDetail = findViewById(R.id.clPhotoDetail)
        imgPhoto = findViewById(R.id.imgPhoto)
        txtPhotoName = findViewById(R.id.txtPhotoName)
        txtPhotoSize = findViewById(R.id.txtPhotoSize)
        txtPhotoAction = findViewById(R.id.txtPhotoAction)
        txtPhotoLabel.isVisible = !isOptional
        llEmptyPhoto.setOnClickListener {
            addPackagePhotoOptional()
        }
        txtPhotoAction.setOnClickListener {
            handlePhotoActionClicked {
                addPackagePhotoOptional()
            }
        }
        imgPhoto.setOnClickListener {
            selectedImage?.let { file ->
                previewPhotoCallback?.invoke(file)
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
                R.string.claim_form_page_photo_ktp_form.toString()
            )
            startActivityForResult(
                activity!!,
                Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    .putExtra(MediaStore.EXTRA_OUTPUT, fileUri),
                REQUEST_CODE!!, null)
        }
    }

    private fun gotoGallery() {
        activity?.let {
            fileUri = ImageUtils.getOutputMediaFileUri(
                activity = it,
                R.string.claim_form_page_photo_ktp_form.toString()
            )
            fileUri?.let { uri ->
                gotoGalleryCallback?.invoke(uri)
            }
        }
    }

    private fun executeSelectImage(gotoCamera: () -> Unit, gotoGallery: () -> Unit) {
        handleSelectImage(gotoCamera, gotoGallery)
    }

    private fun setVisibilitySelectedImage() {
        clPhotoDetail.isVisible = true
        llEmptyPhoto.isVisible = false
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

//    private fun handleImageFromCamera(uri: Uri?, imgView: ImageView): File? {
//        return try {
//            val bitmap = if (Build.VERSION.SDK_INT < 28) {
//                BitmapFactory.decodeFile(uri?.path).handleExifInterfaceRotationBitmap(uri!!)
//            } else {
//                ImageDecoder.createSource(activity!!.contentResolver, uri!!).let {
//                    ImageDecoder.decodeBitmap(it)
//                }
//            }
//            Glide.with(activity!!)
//                .load(uri.path)
//                .into(imgView)
//            ImageUtils.createTempFile(activity!!, bitmap)
//        } catch (ex: Exception) {
//            ex.printStackTrace()
//            null
//        }
//    }

//    private fun Bitmap.handleExifInterfaceRotationBitmap(path: Uri): Bitmap {
//        return try {
//            val exifInterfaceOrientation = activity!!.contentResolver.openInputStream(path).let {
//                ExifInterface(it!!).getAttributeInt(
//                    ExifInterface.TAG_ORIENTATION,
//                    ExifInterface.ORIENTATION_UNDEFINED
//                )
//            }
//            ImageRotator.exifInterfaceRotation(this, exifInterfaceOrientation)
//        } catch (ex: Exception) {
//            this
//        }
//    }

    private fun handlePhotoActionClicked(addPhotoCallback: () -> Unit) {
        if (txtPhotoAction.text == resources.getString(R.string.general_change)) {
            addPhotoCallback.invoke()
        } else resetViewPhotoDetail()
    }

    private fun resetViewPhotoDetail() {
        onResetPhotoCallback?.invoke(selectedImage, fileUri)
        llEmptyPhoto.isVisible = true
        clPhotoDetail.isVisible = false
        changeStateViewPhotoField(false)
        txtPhotoName.text = String()
        txtPhotoSize.text = String()
        txtPhotoAction.text = resources.getString(R.string.general_change)
        selectedImage = null
        fileUri = null
        changeStateViewPackagePhotoOptional()
    }

    private fun changeStateViewPackagePhotoOptional() {
        llEmptyPhoto.isEnabled = isPhotoEnabled
        clParent.alpha = if (isPhotoEnabled) 1.0F else 0.5F
    }

    fun changeStateViewPhotoField(
        isError: Boolean
    ) {
        val textColor = if (isError) R.color.interpack6 else R.color.shades3
        val maxPhotoLabel = when {
            isError && !llEmptyPhoto.isVisible -> resources.getString(R.string.form_claim_damaged_or_lost_photo_invalid_error)
            else -> resources.getString(R.string.form_claim_damaged_or_lost_max_spec_photo_label)
        }
        val requiredLabel = when {
            isError && llEmptyPhoto.isVisible -> resources.getString(R.string.form_claim_damaged_or_lost_photo_empty_error)
            else -> resources.getString(R.string.form_claim_damaged_or_lost_required_label)
        }
        llEmptyPhoto.isSelected = isError
        when (context) {
            llPhoto -> {
                txtPhotoLabel.changeTextColor(textColor)
                txtPhotoInfo.changeTextAndColor(maxPhotoLabel, textColor)
                txtPhotoRequiredLabel.changeTextAndColor(requiredLabel, textColor)
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
