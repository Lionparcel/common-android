package com.lionparcel.commonandroidsample.form

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.core.net.toFile
import androidx.core.view.isNotEmpty
import androidx.core.view.isVisible
import com.lionparcel.commonandroid.form.LPAttachFile
import com.lionparcel.commonandroid.form.LPBulkAttachFile
import com.lionparcel.commonandroidsample.R
import java.io.File

class AttachFileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attach_file)
        findViewById<LPAttachFile>(R.id.lpAttachFile).apply {
            activity = this@AttachFileActivity
            REQUEST_CODE_CAMERA = REQUEST_FROM_CAMERA
            REQUEST_CODE_GALLERY = REQUEST_FROM_GALLERY
            txtPhotoAction.text = "Ubah"
            txtPhotoLabel.text = "Foto 3 x 4"
        }
        findViewById<LPAttachFile>(R.id.lpAttachFile2).apply {
            activity = this@AttachFileActivity
            REQUEST_CODE_CAMERA = REQUEST_FROM_CAMERA2
            REQUEST_CODE_GALLERY = REQUEST_FROM_GALLERY2
            txtPhotoAction.text = "Hapus"
            txtPhotoLabel.text = "Foto KTP"
        }
        findViewById<LPBulkAttachFile>(R.id.lpBulkAttachFile).apply {
            activity = this@AttachFileActivity
            REQUEST_CODE_CAMERA = REQUEST_FROM_CAMERA3
            REQUEST_CODE_GALLERY = REQUEST_FROM_GALLERY3
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val imgAttachFile = findViewById<ImageView>(R.id.imgAttachFile)
        if (requestCode == REQUEST_FROM_CAMERA && resultCode == Activity.RESULT_OK)
            findViewById<LPAttachFile>(R.id.lpAttachFile).apply {
                setImageFromCamera()
            }
        if (requestCode == REQUEST_FROM_GALLERY && resultCode == Activity.RESULT_OK)
            findViewById<LPAttachFile>(R.id.lpAttachFile).apply {
                setImageFromGallery(data?.data)
            }
        if (requestCode == REQUEST_FROM_CAMERA2 && resultCode == Activity.RESULT_OK)
            findViewById<LPAttachFile>(R.id.lpAttachFile2).apply {
                setImageFromCamera()
            }
        if (requestCode == REQUEST_FROM_GALLERY2 && resultCode == Activity.RESULT_OK)
            findViewById<LPAttachFile>(R.id.lpAttachFile2).apply {
                setImageFromGallery(data?.data)
            }
        if (requestCode == REQUEST_FROM_CAMERA3 && resultCode == Activity.RESULT_OK)
            findViewById<LPBulkAttachFile>(R.id.lpBulkAttachFile).apply {
                setImageFromCamera(this@AttachFileActivity)
            }
        if (requestCode == REQUEST_FROM_GALLERY3 && resultCode == Activity.RESULT_OK && data != null)
        {
            findViewById<LPBulkAttachFile>(R.id.lpBulkAttachFile).apply {
                setImageFromGallery(data?.data, this@AttachFileActivity)
            }
        } else {
            findViewById<LPBulkAttachFile>(R.id.lpBulkAttachFile).setVisibilityImagePicker()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        findViewById<LPAttachFile>(R.id.lpAttachFile).permissionHelper(this, requestCode, permissions, grantResults)
        findViewById<LPBulkAttachFile>(R.id.lpBulkAttachFile).permissionHelper(this, requestCode, permissions, grantResults)
    }

    companion object {
        private val REQUEST_FROM_CAMERA = 1
        private val REQUEST_FROM_GALLERY = 2
        private val REQUEST_FROM_CAMERA2 = 3
        private val REQUEST_FROM_GALLERY2 = 4
        private val REQUEST_FROM_CAMERA3 = 5
        private val REQUEST_FROM_GALLERY3 = 6
    }
}
