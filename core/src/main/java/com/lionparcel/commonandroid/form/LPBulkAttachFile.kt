package com.lionparcel.commonandroid.form

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.provider.Settings
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.form.utils.BulkAttachFileAdapter
import com.lionparcel.commonandroid.form.utils.HorizontalSpaceItemDecoration
import com.lionparcel.commonandroid.form.utils.ImageUtils
import com.lionparcel.commonandroid.form.utils.PermissionHelper
import java.io.File

class LPBulkAttachFile : ConstraintLayout {

    private val adapter : BulkAttachFileAdapter
    private val clBulkAttachFile : ConstraintLayout
    private val txtBulkAttachFile : TextView
    private val llBulkAttachFile : LinearLayout
    private val rvBulkAttachFile : RecyclerView
    private val ivAddBulkAttachFile : ImageView
    private val txtErrorBulkAttachFile : TextView
    private var textLabel : String
    private var enableView : Boolean
    private var errorEnabled : Boolean
    private var errorText : String
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
                enableView = getBoolean(R.styleable.LPBulkAttachFile_enabledView, true)
                errorEnabled = getBoolean(R.styleable.LPBulkAttachFile_errorEnabled, false)
                errorText = getString(R.styleable.LPBulkAttachFile_errorText).setString()
            } finally {
                recycle()
            }
        }
        clBulkAttachFile = findViewById(R.id.clBulkAttachFile)
        txtBulkAttachFile = findViewById(R.id.txtBulkAttachFileLabel)
        llBulkAttachFile = findViewById(R.id.llBulkAttachFile)
        rvBulkAttachFile = findViewById(R.id.rvBulkAttachFile)
        ivAddBulkAttachFile = findViewById(R.id.ivAddBulkAttachFile)
        txtErrorBulkAttachFile = findViewById(R.id.txtErrorBulkAttachFile)
        // form label
        txtBulkAttachFile.text = textLabel
        // recyclerview setup
        adapter = BulkAttachFileAdapter(listImage, enableView, errorEnabled){setVisibilityImagePicker(it)}
        rvBulkAttachFile.adapter = adapter
        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.HORIZONTAL
        rvBulkAttachFile.addItemDecoration(HorizontalSpaceItemDecoration(12))
        rvBulkAttachFile.layoutManager = llm
        // enable or disable view
        setEnableView(enableView)
        //error text
        txtErrorBulkAttachFile.text = errorText
        // enable or disable error view
        setError(errorEnabled)


        ivAddBulkAttachFile.setOnClickListener{
            addPackagePhotoOptional()
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
                ).setType("image/*").putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true), Settings.Global.getString(
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

    fun setImageFromGallery(data : Intent?, maxPhoto : Int? = 4){
        if (data != null){
            if (data.clipData != null){
                if (data.clipData!!.itemCount <= maxPhoto!! && listImage.size + data.clipData!!.itemCount <= maxPhoto){
                    val x = data!!.clipData!!.itemCount
                    for (i in 0 until x){
                        val imageUri = data.clipData!!.getItemAt(i).uri
                        listImage.add(imageUri)
                    }
                    if (listImage.size == maxPhoto){
                        setVisibilityImagePicker(false)
                    }
                }
            } else {
                val imageUri = data.data!!
                listImage.add(imageUri)
            }
            adapter.notifyDataSetChanged()
        }
    }

    fun setImageFromCamera() {
        listImage.add(fileUri!!)
        adapter.notifyDataSetChanged()
    }

    fun permissionHelper(activity: Activity, requestCode : Int, permissions: Array<out String>, grantResults: IntArray) {
        return PermissionHelper().onRequestPermissionsResult(activity, requestCode, permissions, grantResults)
    }

    fun setVisibilityImagePicker(visibility : Boolean) {
       ivAddBulkAttachFile.isVisible = visibility
    }

    fun setEnableView(isEnable : Boolean) {
        llBulkAttachFile.isEnabled = isEnable
        ivAddBulkAttachFile.isEnabled = isEnable
        rvBulkAttachFile.isEnabled = isEnable
        val opacity = if(isEnable) 1f else 0.5f
        llBulkAttachFile.alpha = opacity
        adapter.enableClose(isEnable)
        invalidate()
        requestLayout()
    }

    fun setError(isError : Boolean) {
        errorEnabled = isError
        ivAddBulkAttachFile.isSelected = isError
        txtErrorBulkAttachFile.isVisible = isError
        adapter.errorView(isError)
    }
}