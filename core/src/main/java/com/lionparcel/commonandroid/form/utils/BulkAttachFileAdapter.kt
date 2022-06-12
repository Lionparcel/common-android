package com.lionparcel.commonandroid.form.utils

import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.form.LPBulkAttachFile
import com.lionparcel.commonandroid.modal.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.lp_bulk_attach_file_image_list.*
import kotlinx.android.synthetic.main.lp_bulk_attach_file_view.*
import kotlin.math.acosh

class BulkAttachFileAdapter(val listImage : ArrayList<Uri>,private var onItemClicked : ((visibility : Boolean) -> Unit)) :
    RecyclerView.Adapter<BulkAttachFileAdapter.BulkAttachFileViewHolder>() {

//    private var listImage = mutableListOf<Uri>()

    private var selectedImage : Int = 1

    private var activity : Activity? = null

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
        holder.bind(listImage[position], position)
    }

    override fun getItemCount(): Int = listImage.size


    inner class BulkAttachFileViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer{

            fun bind(data : Uri, position: Int){
//                val bitmap = BitmapFactory.decodeFile(data.path)
                ivPreviewBulkAttachFile.setImageURI(data)
                ibDeletePreviewBulkAttachFile.setOnClickListener {
                    deleteImage(position)
                    onItemClicked(true)
                    notifyDataSetChanged()
                }
            }

    }
}