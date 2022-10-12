package com.lionparcel.commonandroid.dropdown

import android.content.res.Resources
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import com.aigestudio.wheelpicker.WheelPicker
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.modal.base.BaseSheetDialogFragment
import kotlinx.android.synthetic.main.lp_layout_wheel_picker.*

class LPWheelPicker: BaseSheetDialogFragment() {

    override fun getContentResource() = R.layout.lp_layout_wheel_picker

    private var btnListener: View.OnClickListener? = null
    private var listData = arrayListOf<String>()

    companion object {

        private const val BASIC_TITLE_GENERAL = "BASIC_TITLE_GENERAL"
        private const val BASIC_BUTTON_TITLE = "BASIC_BUTTON_TITLE"

        fun newInstance(
            title: String,
            btnTitle: String,
            data: ArrayList<String>,
            listener: View.OnClickListener
        ) = LPWheelPicker().apply {
            btnListener = listener
            listData.addAll(data)
            arguments = bundleOf(
                BASIC_TITLE_GENERAL to title,
                BASIC_BUTTON_TITLE to btnTitle
            )
        }
    }

    override fun initViews() {
        super.initViews()
        val maxHeight = (0.99F * Resources.getSystem().displayMetrics.heightPixels).toInt()
        getLayoutBehaviour().peekHeight = maxHeight
        initWheelPicker()
        getWheelPicker().apply {
            data = listData

        }
    }


    private fun initWheelPicker() {
        val title = view?.findViewById<TextView>(R.id.tvWheelPickerTitle)
        val wheelPicker = view?.findViewById<WheelPicker>(R.id.wheelPicker)
        val close = view?.findViewById<ImageView>(R.id.ivWheelPickerClose)
        val button = view?.findViewById<Button>(R.id.btnWheelPicker)
        close?.setOnClickListener {
            dismiss()
        }
        button?.setOnClickListener {
            btnListener?.onClick(it)
        }
        arguments?.getString(BASIC_TITLE_GENERAL)?.let { data ->
            title?.text = data
        }
        arguments?.getString(BASIC_BUTTON_TITLE)?.let { data ->
            button?.text = data
        }
    }

    fun getWheelPicker(): WheelPicker = wheelPicker
}