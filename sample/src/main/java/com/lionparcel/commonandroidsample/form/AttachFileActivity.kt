package com.lionparcel.commonandroidsample.form

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.lionparcel.commonandroid.form.LPAttachFile
import com.lionparcel.commonandroidsample.R
import java.io.File

class AttachFileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attach_file)
        findViewById<LPAttachFile>(R.id.lpAttachFile).apply {
            activity = this@AttachFileActivity
            REQUEST_CODE = REQUEST_KTP
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_KTP && resultCode == Activity.RESULT_OK)
            findViewById<LPAttachFile>(R.id.lpAttachFile).apply {
                previewPhotoCallback.apply {
                    data?.data
                }
            }
    }

    companion object {
        private val REQUEST_KTP = 1
    }
}
