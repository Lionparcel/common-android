package com.lionparcel.commonandroid.dropdown.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.lionparcel.commonandroid.R

class DropdownAdapter<T>(
    context: Context,
    private val values: List<T>,
    private val selectedItemPosition: () -> Int
) : ArrayAdapter<T>(context, 0, values) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return convertView ?: LayoutInflater.from(context).inflate(R.layout.lp_layout_dropdown_item, parent, false)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        if (position == values.lastIndex) return initialSelection()
        val view = LayoutInflater.from(context).inflate(R.layout.lp_layout_dropdown_spinner, parent, false)
        val lpDropdownSpinnerText = view.findViewById<TextView>(R.id.lpDropdownSpinnerText)
        val ivDropdownCheck = view.findViewById<ImageView>(R.id.ivDropdownCheck)

        lpDropdownSpinnerText.text = values[position].toString()
        lpDropdownSpinnerText.typeface = ResourcesCompat.getFont(context,
        if (values[position] == values[selectedItemPosition.invoke()])
            R.font.montserrat_semi_bold
        else
            R.font.montserrat_regular
        )
        ivDropdownCheck.isVisible = values[position] == values[selectedItemPosition.invoke()]
        return view
    }

    private fun initialSelection(): View {
        return View(context).apply { isInvisible = false }
    }
}