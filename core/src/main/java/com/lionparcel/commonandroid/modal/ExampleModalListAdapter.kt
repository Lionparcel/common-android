package com.lionparcel.commonandroid.modal

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.lionparcel.commonandroid.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list_example_modal.*

class ExampleModalListAdapter(
): RecyclerView.Adapter<ExampleModalListAdapter.ViewHolder>() {

    private val list = mutableListOf<String>()

    private var selectedItem: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_list_example_modal))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setData(data: List<String>, selectedItem: Int) {
        list.clear()
        list.addAll(data)
        this.selectedItem = selectedItem
        notifyDataSetChanged()
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(item: String) {
            ivCheckItem.isVisible = adapterPosition == selectedItem
            tvSampleList.text = item
            llExampleList.setOnClickListener {
                selectedItem = adapterPosition
                notifyDataSetChanged()
            }
        }
    }
}
