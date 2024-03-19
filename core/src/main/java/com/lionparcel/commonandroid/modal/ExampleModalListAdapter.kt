package com.lionparcel.commonandroid.modal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.lionparcel.commonandroid.common.BaseViewBinding
import com.lionparcel.commonandroid.databinding.ItemListExampleModalBinding

class ExampleModalListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    BaseViewBinding<ItemListExampleModalBinding> {

    override lateinit var binding: ItemListExampleModalBinding

    private val list = mutableListOf<String>()

    private var selectedItem: Int = 0

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ItemListExampleModalBinding {
        binding = ItemListExampleModalBinding.inflate(inflater, container, false)
        return binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            getViewBinding(
                inflater = LayoutInflater.from(parent.context),
                container = parent
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> holder.bind(list[position])
        }
    }

    fun setData(data: List<String>, selectedItem: Int) {
        list.clear()
        list.addAll(data)
        this.selectedItem = selectedItem
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemBinding: ItemListExampleModalBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: String) {
            binding.ivCheckItem.isVisible = bindingAdapterPosition == selectedItem
            binding.tvSampleList.text = item
            binding.llExampleList.setOnClickListener {
                selectedItem = bindingAdapterPosition
                notifyDataSetChanged()
            }
        }
    }
}
