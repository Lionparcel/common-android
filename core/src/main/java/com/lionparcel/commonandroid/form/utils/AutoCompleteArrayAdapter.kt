package com.lionparcel.commonandroid.form.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.form.LPAutoCompleteForm
import kotlinx.android.synthetic.main.lp_layout_item_autocomplete.view.*
import java.util.*
import kotlin.collections.ArrayList

class AutoCompleteArrayAdapter<T>(
    context: Context,
    var item : List<T>,
    val toName : (T) -> String,
) : ArrayAdapter<T>(context, R.layout.lp_layout_item_autocomplete, R.id.txtAutoComplete, arrayListOf()) {

    var selectedItem: T? = null

    var suggestions = mutableListOf<T>()

    private var inputString: String = String()

    override fun getCount(): Int {
        return suggestions.size
    }

    override fun getItem(position: Int): T? {
        return suggestions[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.lp_auto_complete_form,
            parent,
            false
        )
        val text = getItem(position)?.let(toName).toString()
        when {
            text.contains(inputString, false) ->
                view.txtAutoComplete.setBoldSpannable(text, inputString)
            text.contains(inputString.lowercase(Locale.ROOT), false) ->
                view.txtAutoComplete.setBoldSpannable(text, inputString.lowercase(Locale.ROOT))
            text.contains(inputString.uppercase(Locale.ROOT), false) ->
                view.txtAutoComplete.setBoldSpannable(text, inputString.uppercase(Locale.ROOT))
            text.contains(inputString.toTitleCase(), false) ->
                view.txtAutoComplete.setBoldSpannable(text, inputString.toTitleCase())
        }
        return view
    }


    override fun getFilter(): Filter = object : Filter() {

        override fun convertResultToString(resultValue: Any?): CharSequence {
            return (resultValue as T).let(toName)
        }

        override fun performFiltering(constraint: CharSequence?): FilterResults? {
            return if (constraint != null) {
                inputString = constraint.toString().toTitleCase()
                val suggestions = item.filter { toName(it).contains(constraint, true) }.toMutableList()
                val filterResult = FilterResults()
                filterResult.values = suggestions
                filterResult.count = suggestions.size
                filterResult
            } else null
        }

        override fun publishResults(constraint: CharSequence?, filterResult: FilterResults?) {
            val filterList = filterResult?.values as ArrayList<*>? ?: item
            val sortedList = mutableListOf<T>()
            suggestions.clear()
            try {
                (filterList as List<T>).let {
                    sortedList.addAll(LPAutoCompleteForm(context).sortDataList(constraint.toString(), it))
                }
                filterList.forEach {
                    suggestions.add(it)
                }
            } catch (_: Exception){

            }
            notifyDataSetChanged()
        }
    }



//    private fun createViewFromResource(inflater: LayoutInflater, position: Int, convertView: View?, parent: ViewGroup, resource: Int) : View{
//        val view : View
//        val textView : TextView
//
//        if (convertView == null){
//            view = inflater.inflate(resource, parent, false)
//        } else {
//            view = convertView
//        }
//        if (textResId == 0){
//            textView = view as TextView
//        } else {
//            textView = view.findViewById(textResId)
//        }
//        val item = getItem(position)
//        textView.setText(highlight(query, item.toString()))
//        return view
//    }

//    private fun highlight(search : String, originalText : CharSequence) : CharSequence{
//        if (search.isEmpty()) return originalText
//        val normalizedText = Normalizer
//            .normalize(originalText, Normalizer.Form.NFD)
//            .replace("\\p{InCombiningDiacriticalMarks}+","")
//        var start = normalizedText.indexOf(search)
//        if (start < 0){
//            return originalText
//        } else {
//            val highlighted = SpannableString(originalText)
//            while ( start >= 0) {
//                val spanStart = Math.min(start, originalText.length)
//                val spanEnd = Math.min(start + search.length, originalText.length)
//                highlighted.setSpan(StyleSpan(Typeface.BOLD), spanStart, spanEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//                start = normalizedText.indexOf(search, spanEnd)
//            }
//            return highlighted
//        }
//    }

}