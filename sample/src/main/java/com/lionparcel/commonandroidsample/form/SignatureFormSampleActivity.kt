package com.lionparcel.commonandroidsample.form

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.lionparcel.commonandroid.button.LPButton
import com.lionparcel.commonandroid.form.LPInsertSignatureView
import com.lionparcel.commonandroid.form.LPSignatureForm
import com.lionparcel.commonandroidsample.R

class SignatureFormSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signature_form_sample)

        val lpSignatureForm : LPSignatureForm = findViewById(R.id.lpSignature)
        lpSignatureForm.goToInsertSignature(this, "Signature Form", REQUEST_SIGNATURE,InsertSignatureForm())

        val btnInput: LPButton = findViewById(R.id.btnInput)
        btnInput.setOnClickListener {
            startActivityForResult(LPInsertSignatureView.newIntent(this, "Input Signature", false, InsertSignatureForm()), REQUEST_SIGNATURE_2, null)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val lpSignatureForm : LPSignatureForm = findViewById(R.id.lpSignature)
        val imgPerview : ImageView = findViewById(R.id.imgPreview)
        if(requestCode == REQUEST_SIGNATURE && resultCode == Activity.RESULT_OK){
            lpSignatureForm.getImgSignature(data)
        }
        if (requestCode == REQUEST_SIGNATURE_2 && resultCode == Activity.RESULT_OK){
            LPInsertSignatureView.getFile(data).apply {
                imgPerview.setImageURI(Uri.fromFile(this))
            }
        }
    }
    companion object {
        private val REQUEST_SIGNATURE = 1
        private val REQUEST_SIGNATURE_2 = 2
    }

}