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
import android.provider.Settings
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.view.isNotEmpty
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.form.utils.BulkAttachFileAdapter
import com.lionparcel.commonandroid.form.utils.HorizontalSpaceItemDecoration
import com.lionparcel.commonandroid.form.utils.ImageUtils
import com.lionparcel.commonandroid.form.utils.PermissionHelper
import kotlinx.android.synthetic.main.lp_bulk_attach_file_view.*
import java.io.File

class LPBulkAttachFile : ConstraintLayout {

    private val adapter : BulkAttachFileAdapter
    private val clBulkAttachFile : ConstraintLayout
    private val txtBulkAttachFile : TextView
    private val llBulkAttachFile : LinearLayout
    private val rvBulkAttachFile : RecyclerView
    private val ivAddBulkAttachFile : ImageView
    private val textLabel : String
    private var fileUri: Uri? = null
    private var listImage = ArrayList<Uri>()
    private var selectedImage: File? = null
    var activity : Activity? = null
    var REQUEST_CODE_CAMERA : Int? = null
    var REQUEST_CODE_GALLERY : Int? = null

    val permissions: Array<String> = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    private fun String?.setString() = this ?: ""

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_bulk_attach_file_view, this, true)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPBulkAttachFile,
            0,
            0
        ).apply {
            try {
                textLabel = getString(R.styleable.LPBulkAttachFile_textLabel).setString()
            } finally {
                recycle()
            }
        }
        clBulkAttachFile = findViewById(R.id.clBulkAttachFile)
        txtBulkAttachFile = findViewById(R.id.txtBulkAttachFileLabel)
        llBulkAttachFile = findViewById(R.id.llBulkAttachFile)
        rvBulkAttachFile = findViewById(R.id.rvBulkAttachFile)
        ivAddBulkAttachFile = findViewById(R.id.ivAddBulkAttachFile)

        txtBulkAttachFile.text = textLabel


//        if (rvBulkAttachFile.isNotEmpty()){
//            val layoutParams = ivAddBulkAttachFile.layoutParams as LinearLayout.LayoutParams
//            layoutParams.setMargins(16,0,0,0)
//            ivAddBulkAttachFile.layoutParams = layoutParams
//        }

        adapter = BulkAttachFileAdapter(listImage){setVisibilityImagePicker(it) }
        rvBulkAttachFile.adapter = adapter
        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.HORIZONTAL
        rvBulkAttachFile.addItemDecoration(HorizontalSpaceItemDecoration(16))
        rvBulkAttachFile.layoutManager = llm


        ivAddBulkAttachFile.setOnClickListener{
            addPackagePhotoOptional()
            if (rvBulkAttachFile.adapter!!.itemCount > 2){
                ivAddBulkAttachFile.isVisible = false
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
            ActivityCompat.startActivityForResult(
                activity!!,
                Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    .putExtra(MediaStore.EXTRA_OUTPUT, fileUri),
                REQUEST_CODE_CAMERA!!, null
            )
        }
    }

    private fun gotoGallery() {
        ActivityCompat.startActivityForResult(
            activity!!,
            Intent.createChooser(
                Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI
                ).setType("image/*"), Settings.Global.getString(
                    activity!!.contentResolver,
                    R.string.claim_form_select_image_title.toString()
                )
            ), REQUEST_CODE_GALLERY!!, null
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

    private fun handleImageFromGallery(path: Uri?, activity: Activity): File? {
        return try {
            val bitmap = if (Build.VERSION.SDK_INT < 28) {
                MediaStore.Images.Media.getBitmap(activity.contentResolver, path)
            } else {
                ImageDecoder.createSource(activity.contentResolver, path!!).let {
                    ImageDecoder.decodeBitmap(it)
                }
            }
            this.activity = activity
//            BulkAttachFileAdapter().setData(path!!, 1, activity)
            ImageUtils.createTempFile(activity, bitmap)
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    fun setImageFromGallery(data : Uri?, activity: Activity){
        listImage.add(data!!)
        adapter.notifyDataSetChanged()
    }


    private fun handleImageFromCamera(uri: Uri?, activity: Activity): File? {
        return try {
            val bitmap = if (Build.VERSION.SDK_INT < 28) {
                BitmapFactory.decodeFile(uri?.path)
            } else {
                ImageDecoder.createSource(activity.contentResolver, uri!!).let {
                    ImageDecoder.decodeBitmap(it)
                }
            }
            this.activity = activity
//            BulkAttachFileAdapter().setData(uri!!, 1, activity)
            ImageUtils.createTempFile(activity, bitmap)
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    fun setImageFromCamera(activity: Activity) {
        selectedImage = handleImageFromCamera(fileUri, activity)
    }

    fun permissionHelper(activity: Activity, requestCode : Int, permissions: Array<out String>, grantResults: IntArray) {
        return PermissionHelper().onRequestPermissionsResult(activity, requestCode, permissions, grantResults)
    }

    fun setVisibilityImagePicker(visibility : Boolean) {
       ivAddBulkAttachFile.isVisible = visibility
    }

    private fun setVisibilityImagePickerWhen(){
        
    }
}