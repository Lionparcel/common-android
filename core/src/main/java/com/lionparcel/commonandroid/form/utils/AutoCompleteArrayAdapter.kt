package com.lionparcel.commonandroid.form.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import com.lionparcel.commonandroid.R
import kotlinx.android.synthetic.main.lp_layout_item_autocomplete.view.*
import java.util.*
import kotlin.collections.ArrayList

class AutoCompleteArrayAdapter<T>(
    context: Context,
    var item : List<T>
) : ArrayAdapter<T>(context, R.layout.lp_layout_item_autocomplete, R.id.txtAutoComplete, arrayListOf()) {

    var suggestions = listOf<T>()

    private var inputString: String = String()

    override fun getCount(): Int {
        return suggestions.size
    }

    override fun getItem(position: Int): T? {
        return suggestions[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.lp_layout_item_autocomplete,
            parent,
            false
        )
        val text = getItem(position).toString()
        when {
            text.contains(inputString, false) ->
                view.txtAutoComplete.setSemiBoldSpannable(text, inputString)
            text.contains(inputString.lowercase(Locale.ROOT), false) ->
                view.txtAutoComplete.setSemiBoldSpannable(text, inputString.lowercase(Locale.ROOT))
            text.contains(inputString.uppercase(Locale.ROOT), false) ->
                view.txtAutoComplete.setSemiBoldSpannable(text, inputString.uppercase(Locale.ROOT))
            text.contains(inputString.toTitleCase(), false) ->
                view.txtAutoComplete.setSemiBoldSpannable(text, inputString.toTitleCase())
        }
        return view
    }


    override fun getFilter(): Filter = object : Filter() {

        override fun convertResultToString(resultValue: Any?): CharSequence {
            return (resultValue as T).toString()
        }

        override fun performFiltering(constraint: CharSequence?): FilterResults? {
            return if (constraint != null) {
                inputString = constraint.toString().toTitleCase()
                val suggestions = item.filter { it.toString().contains(constraint, true) }
                val filterResult = FilterResults()
                filterResult.values = suggestions
                filterResult.count = suggestions.size
                filterResult
            } else null
        }

        override fun publishResults(constraint: CharSequence?, filterResult: FilterResults?) {
            try {
                suggestions = if (filterResult != null) {
                    filterResult.values as ArrayList<T>
                } else {
                    item
                }
            } catch (_: Exception) {
                suggestions = if (filterResult != null) {
                    filterResult.values as ArrayList<T>
                } else {
                    item
                }
            }
            notifyDataSetChanged()
        }
    }

}