package com.lionparcel.commonandroidsample.form

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.lionparcel.commonandroid.form.LPAttachFile
import com.lionparcel.commonandroidsample.R

class AttachFileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attach_file)
        findViewById<LPAttachFile>(R.id.lpAttachFile).apply {
            activity = this@AttachFileActivity
            gotoGallery = {
                startActivity(
                    Intent.createChooser(
                        Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.INTERNAL_CONTENT_URI
                        ).setType("image/*"),
                        getString(R.string.claim_form_select_image_title)
                    )
                )
            }
        }
    }
}
