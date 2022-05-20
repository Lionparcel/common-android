package com.lionparcel.commonandroid.form

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.view.isVisible
import com.lionparcel.commonandroid.R
import java.io.File

class LPSignatureForm : FrameLayout{

    private var isOptional : Boolean = false

    private var clSignatureParent : ConstraintLayout
    private var llSignature : LinearLayout
    private var txtSignatureLabel : TextView
    private var txtSignatureRequiredLabel : TextView
    private var llEmptySignature : LinearLayout
    private var llSignatureDetail : LinearLayout
    private var imgSignature : ImageView
    private var txtChangeSignature : TextView
    private var txtErrorSignature : TextView
    private var imgAddSignature : ImageView

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_signature_form_view,this, true)
        clSignatureParent = findViewById(R.id.clSignatureParent)
        llSignature = findViewById(R.id.llSignature)
        txtSignatureLabel = findViewById(R.id.txtSignatureLabel)
        txtSignatureRequiredLabel = findViewById(R.id.txtSignatureRequiredLabel)
        llEmptySignature = findViewById(R.id.llEmptySignature)
        llSignatureDetail = findViewById(R.id.llSignatureDetail)
        imgSignature = findViewById(R.id.imgSignature)
        txtChangeSignature = findViewById(R.id.txtChangeSignature)
        txtErrorSignature = findViewById(R.id.txtErrorSignature)
        imgAddSignature = findViewById(R.id.imgAddSignature)
        txtSignatureLabel.isVisible = !isOptional
    }

    private fun setVisibilitySelectedImage(){
        llSignatureDetail.isVisible = true
        llEmptySignature.isVisible = false
        txtErrorSignature.isVisible = false
    }

    fun goToInsertSignature(fromActivity: Activity,title : String, reqCode : Int,toActivity: Activity){
        llSignature.setOnClickListener {

            startActivityForResult(fromActivity, LPInsertSignatureView.newIntent(fromActivity, title, true, toActivity), reqCode, null)
        }
        txtSignatureLabel.text = title
    }

    fun getImgSignature(data : Intent?){
        LPInsertSignatureView.getFile(data)?.apply {
            val img = Uri.fromFile(this)
            imgSignature.setImageURI(img)
        }
        setVisibilitySelectedImage()
    }

    fun setTxtErrorSignature(string: String){
        txtErrorSignature.text = string
    }

    fun setTxtSignatureLabelVisibility(isOption : Boolean){
        txtSignatureLabel.isVisible = isOption
    }


}