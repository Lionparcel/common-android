package com.lionparcel.commonandroid.form.utils

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lionparcel.commonandroid.common.BaseViewBinding
import com.lionparcel.commonandroid.databinding.LpBulkAttachFileImageListBinding

class BulkAttachFileAdapter(
    private val listImage: ArrayList<Uri>,
    var isEnable: Boolean,
    var isError: Boolean,
    private var onItemClicked: ((visibility: Boolean) -> Unit),
    private var onPhotoClicked: ((Uri) -> Unit),
    private var onPhotoDismiss: ((Uri) -> Unit)
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    BaseViewBinding<LpBulkAttachFileImageListBinding> {

    override lateinit var binding: LpBulkAttachFileImageListBinding

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): LpBulkAttachFileImageListBinding {
        binding = LpBulkAttachFileImageListBinding.inflate(inflater, container, false)
        return binding
    }

    private fun deleteImage(position: Int) {
        listImage.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, listImage.size)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BulkAttachFileViewHolder(
            getViewBinding(
                inflater = LayoutInflater.from(parent.context),
                container = parent
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BulkAttachFileViewHolder -> holder.bind(
                listImage[position],
                position,
                isEnable,
                isError
            )
        }
    }

    override fun getItemCount(): Int = listImage.size


    inner class BulkAttachFileViewHolder(private val itemBinding: LpBulkAttachFileImageListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(data: Uri, position: Int, isEnable: Boolean, isError: Boolean) {
            itemBinding.vwOutlinedBulkAttachFile.isSelected = isError
            itemBinding.ivPreviewBulkAttachFile.setImageURI(data)
            itemBinding.ibDeletePreviewBulkAttachFile.isEnabled = isEnable
            itemBinding.ibDeletePreviewBulkAttachFile.setOnClickListener {
                deleteImage(position)
                onItemClicked(true)
                onPhotoDismiss.invoke(data)
                notifyDataSetChanged()
            }
            itemBinding.ivPreviewBulkAttachFile.setOnClickListener {
                onPhotoClicked.invoke(data)
            }
        }

    }

    fun enableClose(isEnabled: Boolean) {
        this.isEnable = isEnabled
        notifyDataSetChanged()
    }

    fun errorView(isError: Boolean) {
        this.isError = isError
        notifyDataSetChanged()
    }
}