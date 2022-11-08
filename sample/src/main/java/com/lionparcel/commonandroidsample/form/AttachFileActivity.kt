package com.lionparcel.commonandroidsample.form

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lionparcel.commonandroid.form.LPAttachFile
import com.lionparcel.commonandroid.form.LPBulkAttachFile
import com.lionparcel.commonandroid.form.utils.PermissionHelper
import com.lionparcel.commonandroidsample.R

class AttachFileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attach_file)
        findViewById<LPAttachFile>(R.id.lpAttachFile).apply {
            activity = this@AttachFileActivity
            requestCodeCamera = REQUEST_FROM_CAMERA
            requestCodeGallery = REQUEST_FROM_GALLERY
            executeSelectImage = { permissions, executable ->
                PermissionHelper().executeWithAllPermissions(activity = this@AttachFileActivity, permissions = permissions, executable = executable)
            }
        }
        findViewById<Button>(R.id.btn_disable_attach_file).setOnClickListener {
            findViewById<LPAttachFile>(R.id.lpAttachFile).changeStateEnableAttachFile(false)
        }
        findViewById<LPAttachFile>(R.id.lpAttachFile2).apply {
            activity = this@AttachFileActivity
            requestCodeCamera = REQUEST_FROM_CAMERA2
            requestCodeGallery = REQUEST_FROM_GALLERY2
            // On delete action button
            onResetPhotoCallback = { _, _ ->
                findViewById<LPAttachFile>(R.id.lpAttachFile2).changeErrorStateViewPhotoField(true)
            }
        }
        findViewById<LPBulkAttachFile>(R.id.lpBulkAttachFile).apply {
            activity = this@AttachFileActivity
            requestCodeCamera = REQUEST_FROM_CAMERA3
            requestCodeGallery = REQUEST_FROM_GALLERY3
            onPhotoClicked = {
                Toast.makeText(context, "Berhasil", Toast.LENGTH_LONG).show()
            }
        }
        findViewById<LPBulkAttachFile>(R.id.lpBulkAttachFile1).apply {
            activity = this@AttachFileActivity
            requestCodeCamera = REQUEST_FROM_CAMERA4
            requestCodeGallery = REQUEST_FROM_GALLERY4
        }
        findViewById<Button>(R.id.btn_disable_bulk_attach_file).setOnClickListener {
            findViewById<LPBulkAttachFile>(R.id.lpBulkAttachFile1).setEnableView(false)
        }
        findViewById<LPBulkAttachFile>(R.id.lpBulkAttachFile2).apply {
            activity = this@AttachFileActivity
            requestCodeCamera = REQUEST_FROM_CAMERA5
            requestCodeGallery = REQUEST_FROM_GALLERY5
        }
        findViewById<Button>(R.id.btn_error_bulk_attach_file).setOnClickListener {
            findViewById<LPBulkAttachFile>(R.id.lpBulkAttachFile2).setError(true)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val imgAttachFile = findViewById<ImageView>(R.id.imgAttachFile)
        if (resultCode == Activity.RESULT_OK)
            findViewById<LPAttachFile>(R.id.lpAttachFile).apply {
                setImageOnActivityResult(requestCode, data)
            }
        if (resultCode == Activity.RESULT_OK)
            findViewById<LPAttachFile>(R.id.lpAttachFile2).apply {
                setImageOnActivityResult(requestCode, data)
            }
        if (resultCode == Activity.RESULT_OK)
            findViewById<LPBulkAttachFile>(R.id.lpBulkAttachFile).apply {
                setImageOnActivityResult(requestCode, data)
            }
        if (resultCode == Activity.RESULT_OK)
            findViewById<LPBulkAttachFile>(R.id.lpBulkAttachFile1).apply {
                setImageOnActivityResult(requestCode, data)
            }
        if (resultCode == Activity.RESULT_OK)
            findViewById<LPBulkAttachFile>(R.id.lpBulkAttachFile2).apply {
                setImageOnActivityResult(requestCode, data)
            }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        findViewById<LPAttachFile>(R.id.lpAttachFile).permissionHelper(
            this,
            requestCode,
            permissions,
            grantResults
        )
        findViewById<LPBulkAttachFile>(R.id.lpBulkAttachFile).permissionHelper(
            this,
            requestCode,
            permissions,
            grantResults
        )
        findViewById<LPBulkAttachFile>(R.id.lpBulkAttachFile1).permissionHelper(
            this,
            requestCode,
            permissions,
            grantResults
        )
        findViewById<LPBulkAttachFile>(R.id.lpBulkAttachFile2).permissionHelper(
            this,
            requestCode,
            permissions,
            grantResults
        )
    }

    companion object {
        private const val REQUEST_FROM_CAMERA = 1
        private const val REQUEST_FROM_GALLERY = 2
        private const val REQUEST_FROM_CAMERA2 = 3
        private const val REQUEST_FROM_GALLERY2 = 4
        private const val REQUEST_FROM_CAMERA3 = 5
        private const val REQUEST_FROM_GALLERY3 = 6
        private const val REQUEST_FROM_CAMERA4 = 7
        private const val REQUEST_FROM_GALLERY4 = 8
        private const val REQUEST_FROM_CAMERA5 = 9
        private const val REQUEST_FROM_GALLERY5 = 10
    }
}
