package com.lionparcel.commonandroid.form

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.button.LPButton
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class LPInsertSignatureView : ConstraintLayout{

    companion object{

        private const val TITLE = "TITLE"
        private const val IS_CACHE = "IS_CACHE"

        fun newIntent(context: Context, title: String, isCache: Boolean, toActivity: Activity): Intent {
            val bundle = Bundle().apply {
                putString(TITLE, title)
                putBoolean(IS_CACHE, isCache)
            }
            return Intent(context, toActivity::class.java).apply {
                putExtras(bundle)
            }
        }

        fun getFile(data : Intent?): File?{
            return File(data?.getStringExtra("BUNDLE_SIGNATURE"))
        }
    }

    private var activity : Activity? = context as Activity

    private var btnBackSignature : ImageButton
    private var btnSaveSignature : LPButton
    private var btnClearSignature : ImageButton
    private var signaturePad : LPSignaturePad
    private var txtHeadingInsertSignatureView : TextView

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        val layoutInflater = LayoutInflater.from(context)
        val isChace = activity?.intent!!.getBooleanExtra(IS_CACHE, false)
        layoutInflater.inflate(R.layout.lp_insert_signature_form_view, this, true)
        btnSaveSignature = findViewById(R.id.btnSaveSignature)
        btnClearSignature = findViewById(R.id.btnClearSignature)
        signaturePad = findViewById(R.id.LPSignaturePad)
        txtHeadingInsertSignatureView = findViewById(R.id.txtHeadingInputSignature)
        btnBackSignature = findViewById(R.id.btnInputSignatureBack)

        activity?.intent!!.getStringExtra(TITLE)?.let {
            txtHeadingInsertSignatureView.setText(it)
        }

        btnBackSignature.setOnClickListener {
            finishFunction()
        }

        btnClearSignature.setOnClickListener {
            btnSaveSignature.setEnable(false)
            signaturePad.clearDrawing()
        }
        btnSaveSignature.setOnClickListener {
            signaturePad.getDrawingBitmap()?.let {
                val path = saveBitmapToCache(context, it, "${System.currentTimeMillis()}.jpg", 0, isChace).path
                activity?.setResult(Activity.RESULT_OK, activity?.intent.apply {
                    this!!.putExtra("BUNDLE_SIGNATURE", path)
                })


                finishFunction()
            }
        }
        signaturePad.setOnStartDrawingListener {
            btnSaveSignature.setEnable(true)
        }

    }

    private fun LPButton.setEnable(isEnable: Boolean) {
        if (isEnabled == isEnable) return
        isEnabled = isEnable
    }

    private fun finishFunction(){
        activity?.finish()
    }

    private fun saveBitmapToCache(context: Context, bitmap: Bitmap, path: String, quality: Int = 0, isCache: Boolean = false): File {
        var file: File
        if (isCache) {
            file = File(context.externalCacheDir!!.absolutePath, path)
            if (file.parentFile?.exists() == false) {
                file.parentFile?.mkdir()
            }
            file.createNewFile()
        } else {
            file = context.getDir("photos", Context.MODE_PRIVATE)
            if (!file.mkdirs()) {
                file.mkdirs()
            }
            file = File(file, path)
            file.createNewFile()
        }

        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bos)
        val bitmapData = bos.toByteArray()
        val fos = FileOutputStream(file)
        fos.write(bitmapData)
        fos.flush()
        fos.close()

        return file
    }
}