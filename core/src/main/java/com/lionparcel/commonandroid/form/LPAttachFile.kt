package com.lionparcel.commonandroid.form

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.Settings.Global.getString
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.form.utils.ImageUtils
import com.lionparcel.commonandroid.form.utils.PermissionHelper
import java.io.File
import kotlin.math.roundToInt

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
    var isTextLabelVisible: Boolean = true
    var isPhotoEnabled: Boolean = true
    var onResetPhotoCallback: ((selectedImage: File?, fileUri: Uri?) -> Unit)? = null
    var photoAction : String? = null

    var activity: Activity? = null

    var REQUEST_CODE_CAMERA : Int? = null
    var REQUEST_CODE_GALLERY : Int? = null

    val permissions: Array<String> = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

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
        txtPhotoLabel.isVisible = isTextLabelVisible
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
                REQUEST_CODE_CAMERA!!, null)
        }
    }

    private fun gotoGallery() {
        startActivityForResult(
            activity!!,
            Intent.createChooser(
                Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI
                ).setType("image/*"),getString(activity!!.contentResolver,
                    R.string.claim_form_select_image_title.toString()
                )
            ) , REQUEST_CODE_GALLERY!!, null
        )

    }

    private fun executeSelectImage(gotoCamera: () -> Unit, gotoGallery: () -> Unit) {
        PermissionHelper().executeWithAllPermissions(
            activity = activity!!,
            permissions = permissions,
            executable = {
                handleSelectImage(gotoCamera, gotoGallery)
            }
        )
    }

    fun setVisibilitySelectedImage() {
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

    private fun handleImageFromGallery(path: Uri?, imgView: ImageView, textView: TextView, photoSize : TextView): File? {
        return try {
            val bitmap = if (Build.VERSION.SDK_INT < 28) {
                MediaStore.Images.Media.getBitmap(activity!!.contentResolver, path)
            } else {
                ImageDecoder.createSource(activity!!.contentResolver, path!!).let {
                    ImageDecoder.decodeBitmap(it)
                }
            }
            val fileInputStream = activity!!.contentResolver.openInputStream(path!!)
            val dataSizeRound = fileInputStream!!.available().toDouble()/1000000
            val dataSize = (dataSizeRound * 100.0).roundToInt()/100.0
            photoSize.text = "$dataSize mb"
            textView.text = path.lastPathSegment
            imgView.setImageBitmap(bitmap)
            ImageUtils.createTempFile(activity!!, bitmap)
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    fun setImageFromGallery(data : Uri?){
        selectedImage = handleImageFromGallery(data, imgPhoto, txtPhotoName, txtPhotoSize)
        setVisibilitySelectedImage()
        changeStateViewPhotoField(false)
    }


    private fun handleImageFromCamera(uri: Uri?, imgView: ImageView, textView: TextView, photoSize: TextView): File? {
        return try {
            val bitmap = if (Build.VERSION.SDK_INT < 28) {
                BitmapFactory.decodeFile(uri?.path)
            } else {
                ImageDecoder.createSource(activity!!.contentResolver, uri!!).let {
                    ImageDecoder.decodeBitmap(it)
                }
            }
            val fileInputStream = activity!!.contentResolver.openInputStream(uri!!)
            val dataSizeRound = fileInputStream!!.available().toDouble()/1000000
            val dataSize = (dataSizeRound * 100.0).roundToInt()/100.0
            photoSize.text = "$dataSize mb"
            textView.text = uri.lastPathSegment
            imgView.setImageBitmap(bitmap)
            ImageUtils.createTempFile(activity!!, bitmap)
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    fun setImageFromCamera() {
        selectedImage = handleImageFromCamera(fileUri, imgPhoto, txtPhotoName, txtPhotoSize)
        setVisibilitySelectedImage()
        changeStateViewPhotoField(false)
    }

    private fun handlePhotoActionClicked(addPhotoCallback: () -> Unit) {
        if (txtPhotoAction.text == resources.getString(R.string.general_change)) {
            addPhotoCallback.invoke()
        } else resetViewPhotoDetail()
    }

    private fun resetViewPhotoDetail() {
        onResetPhotoCallback?.invoke(selectedImage, fileUri)
        llEmptyPhoto.isVisible = true
        clPhotoDetail.isVisible = false
        changeStateViewPhotoField(true)
        txtPhotoName.text = String()
        txtPhotoSize.text = String()
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
            isError && llEmptyPhoto.isVisible -> resources.getString(R.string.form_claim_damaged_or_lost_photo_invalid_error)
            else -> resources.getString(R.string.form_claim_damaged_or_lost_max_spec_photo_label)
        }
        val requiredLabel = when {
            isError && llEmptyPhoto.isVisible -> resources.getString(R.string.form_claim_damaged_or_lost_photo_empty_error)
            else -> resources.getString(R.string.form_claim_damaged_or_lost_required_label)
        }
        llPhoto.isSelected = isError
        txtPhotoInfo.changeTextAndColor(maxPhotoLabel, textColor)
        txtPhotoRequiredLabel.changeTextAndColor(requiredLabel, textColor)
    }

    private fun TextView.changeTextColor(color: Int) {
        setTextColor(ContextCompat.getColor(context, color))
    }

    private fun TextView.changeTextAndColor(textString: String, color: Int) {
        text = textString
        changeTextColor(color)
    }

    fun permissionHelper(activity: Activity, requestCode : Int, permissions: Array<out String>, grantResults: IntArray) {
        return PermissionHelper().onRequestPermissionsResult(activity, requestCode, permissions, grantResults)
    }

}
