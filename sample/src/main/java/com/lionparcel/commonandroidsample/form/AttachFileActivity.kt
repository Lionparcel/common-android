package com.lionparcel.commonandroidsample.form

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lionparcel.commonandroid.form.LPAttachFile
import com.lionparcel.commonandroidsample.R

class AttachFileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attach_file)
        findViewById<LPAttachFile>(R.id.lpAttachFile).apply {
            activity = this@AttachFileActivity
            gotoGallery = {

            }
        }
    }
}