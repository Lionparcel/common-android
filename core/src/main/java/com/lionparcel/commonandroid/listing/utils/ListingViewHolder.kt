package com.lionparcel.commonandroid.listing.utils

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.form.utils.setRegularFont
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.lp_layout_listing_items.*

class ListingViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    companion object {
        fun newInstance(parent: ViewGroup): ListingViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.lp_layout_listing_items, parent, false)
            return ListingViewHolder(view)
        }
    }
    private fun setListingStyle(listingStyle: Int){
        when (listingStyle) {
            0 -> {
                ll_listing_thumbnail.isVisible = false
                txt_listing_sub_title.isVisible = false
                txt_listing_title.setRegularFont()
            }
            1 -> {
                ll_listing_icon_start.updateLayoutParams<ConstraintLayout.LayoutParams> {
                    this.bottomToBottom = ConstraintLayout.LayoutParams.UNSET
                }
                ll_listing_thumbnail.isVisible = false
                txt_listing_sub_title.isVisible = true
            }
            2 -> {
                ll_listing_thumbnail.isVisible = true
                txt_listing_sub_title.isVisible = true
            }
        }
    }

    private fun setIconStartVisibility(iconStart: Int) {
        when (iconStart) {
            0 -> {
                ll_listing_icon_start.isVisible = false
            }
            1 -> {
                ll_listing_icon_start.isVisible = true
                rb_listing_start.isVisible = true
                cb_listing_start.isVisible = false
                iv_close_listing_start.isVisible = false
                iv_icon_listing_start.isVisible = false
            }
            2 -> {
                ll_listing_icon_start.isVisible = true
                rb_listing_start.isVisible = false
                cb_listing_start.isVisible = true
                iv_close_listing_start.isVisible = false
                iv_icon_listing_start.isVisible = false
            }
            3 -> {
                ll_listing_icon_start.isVisible = true
                rb_listing_start.isVisible = false
                cb_listing_start.isVisible = false
                iv_close_listing_start.isVisible = true
                iv_icon_listing_start.isVisible = false
            }
            4 -> {
                ll_listing_icon_start.isVisible = true
                rb_listing_start.isVisible = false
                cb_listing_start.isVisible = false
                iv_close_listing_start.isVisible = false
                iv_icon_listing_start.isVisible = true
            }
        }
    }

    private fun setListOutline(listDivider: Boolean) {
        vw_listing_divider_bottom.isVisible = listDivider
    }

    private fun setIconEndVisibility(iconEnd: Int) {
        when (iconEnd) {
            0 -> {
                sw_listing_end.isVisible = false
                iv_close_listing_end.isVisible = false
                iv_icon_listing_end.isVisible = false
                btn_listing_end.isVisible = false
            }
            1 -> {
                sw_listing_end.isVisible = true
                iv_close_listing_end.isVisible = false
                iv_icon_listing_end.isVisible = false
                btn_listing_end.isVisible = false
            }
            2 -> {
                sw_listing_end.isVisible = false
                iv_close_listing_end.isVisible = true
                iv_icon_listing_end.isVisible = false
                btn_listing_end.isVisible = false
            }
            3 -> {
                sw_listing_end.isVisible = false
                iv_close_listing_end.isVisible = false
                iv_icon_listing_end.isVisible = true
                btn_listing_end.isVisible = false
            }
            4 -> {
                sw_listing_end.isVisible = false
                iv_close_listing_end.isVisible = false
                iv_icon_listing_end.isVisible = false
                btn_listing_end.isVisible = true
            }
        }
    }

    fun render(
        item: ListData,
        listingStyle: Int,
        iconStart: Int,
        listDivider: Boolean,
        iconEnd: Int,
        buttonText : String,
        iconStartImage : Drawable,
        iconEndImage : Drawable
    ) {
        setListingStyle(listingStyle)
        setIconStartVisibility(iconStart)
        setIconEndVisibility(iconEnd)
        setListOutline(listDivider)
        iv_icon_listing_start.setImageDrawable(iconStartImage)
        iv_thumbnail_listing.setImageResource(item.thumnail)
        iv_icon_listing_end.setImageDrawable(iconEndImage)
        btn_listing_end.text = buttonText
        txt_listing_title.text = item.title
        txt_listing_sub_title.text = item.subtitle
    }

}