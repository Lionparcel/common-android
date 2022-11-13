package com.lionparcel.commonandroid.form.utils

import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lionparcel.commonandroid.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.lp_bulk_attach_file_image_list.*
import kotlinx.android.synthetic.main.lp_bulk_attach_file_view.*

class BulkAttachFileAdapter(
    private val listImage : ArrayList<Uri>,
    var isEnable : Boolean,
    var isError: Boolean ,
    private var onItemClicked : ((visibility : Boolean) -> Unit),
    private var onPhotoClicked: ((Uri) -> Unit),
    private var onPhotoDismiss: ((Uri) -> Unit)
) : RecyclerView.Adapter<BulkAttachFileAdapter.BulkAttachFileViewHolder>() {

    private fun deleteImage( position: Int) {
        listImage.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, listImage.size)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BulkAttachFileViewHolder {
        return BulkAttachFileViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lp_bulk_attach_file_image_list, parent, false))
    }

    override fun onBindViewHolder(holder: BulkAttachFileViewHolder, position: Int) {
        holder.bind(listImage[position], position, isEnable, isError)
    }

    override fun getItemCount(): Int = listImage.size


    inner class BulkAttachFileViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer{

            fun bind(data : Uri, position: Int, isEnable: Boolean, isError : Boolean){
                vwOutlinedBulkAttachFile.isSelected = isError
                ivPreviewBulkAttachFile.setImageURI(data)
                ibDeletePreviewBulkAttachFile.isEnabled = isEnable
                ibDeletePreviewBulkAttachFile.setOnClickListener {
                    deleteImage(position)
                    onItemClicked(true)
                    onPhotoDismiss.invoke(data)
                    notifyDataSetChanged()
                }
                ivPreviewBulkAttachFile.setOnClickListener {
                    onPhotoClicked.invoke(data)
                }
            }

    }

    fun enableClose(isEnabled: Boolean) {
        this.isEnable = isEnabled
        notifyDataSetChanged()
    }

    fun errorView(isError : Boolean){
        this.isError = isError
        notifyDataSetChanged()
    }
}