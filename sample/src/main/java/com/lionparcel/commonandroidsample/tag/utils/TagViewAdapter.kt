package com.lionparcel.commonandroidsample.tag.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lionparcel.commonandroid.tag.LPTag
import com.lionparcel.commonandroidsample.R

class TagViewAdapter(private var titles : ArrayList<String>, private var visible: Boolean, private var isSelected: ArrayList<Boolean>) : RecyclerView.Adapter<TagViewAdapter.ViewHolder>() {


    inner class ViewHolder(containerView : View) : RecyclerView.ViewHolder(containerView){
        private val tag: LPTag = itemView.findViewById(R.id.lp_tag_list)
        fun render(title : String, visible : Boolean, isSelected: Boolean) {
            tag.titleText(title)
            tag.setIconVisibility(visible)
            tag.tagStatus(isSelected)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.tag_sample_item_view, parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(titles[position], visible, isSelected[position])
        holder.itemView.setOnClickListener {
            isSelected.forEachIndexed { index, _ ->
                if (isSelected[index]) {
                    isSelected[index] = false
                    notifyItemChanged(index)
                }
            }
            isSelected[position] = !holder.itemView.findViewById<LPTag>(R.id.lp_tag_list).isSelected
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = titles.size

}
