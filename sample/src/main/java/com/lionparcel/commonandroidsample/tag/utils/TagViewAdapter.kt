package com.lionparcel.commonandroidsample.tag.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lionparcel.commonandroid.tag.LPTag
import com.lionparcel.commonandroidsample.R

class TagViewAdapter(var titles : ArrayList<String>, var visible: Boolean) : RecyclerView.Adapter<TagViewAdapter.ViewHolder>() {


    inner class ViewHolder(containerView : View) : RecyclerView.ViewHolder(containerView){
        val tag : LPTag = itemView.findViewById(R.id.lp_tag_list)
        fun render(title : String, visible : Boolean) {
            tag.titleText(title)
            tag.setIconVisibility(visible)
            tag.setOnClickListener {
                tag.changeViewOnCLick()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.tag_sample_item_view, parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(titles[position], visible)
    }

    override fun getItemCount(): Int = titles.size

}