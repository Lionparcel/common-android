package com.lionparcel.commonandroid.dropdown.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.lionparcel.commonandroid.R
import kotlinx.android.synthetic.main.lp_layout_dropdown_spinner.view.*

class DropdownAdapterDA(
    context: Context,
    private val values: List<DropdownData>,
    private val selectedItemPosition: () -> Int
) : ArrayAdapter<DropdownData>(context, 0, values) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return convertView ?: LayoutInflater.from(context).inflate(R.layout.lp_layout_dropdown_item, parent, false)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(R.layout.lp_layout_dropdown_spinner, parent, false)
        view.lpDropdownSpinnerText.text = values[position].text
        view.lpDropdownSpinnerText.typeface = ResourcesCompat.getFont(context,
            if (values[position] == values[selectedItemPosition.invoke()])
                R.font.poppins_semi_bold
            else
                R.font.poppins_regular
        )
        view.ivDropdownCheck.isVisible = values[position] == values[selectedItemPosition.invoke()]
        view.isEnabled = values[position].isDisable
        if (!values[position].isDisable) view.lpDropdownSpinnerText.setTextColor(ResourcesCompat.getColor(context.resources, R.color.shades3, null))
        return view
    }

    private fun initialSelection(): View {
        return View(context).apply { isInvisible = false }
    }
}