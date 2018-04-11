package com.chalat.imagethumbnail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.reactivex.functions.Action
import kotlinx.android.synthetic.main.view_image_thumbnail_extra.view.*

/**
 *
 * Created by Chalat Chansima on 12/25/17.
 *
 */
internal class ImageThumbNailExtraViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    private fun defaultShowSize() = 2

    internal fun bindView(imageUrl: String, size: Int, imageClickAction: Action) {
        Glide.with(itemView)
                .load(imageUrl)
                .apply(
                        RequestOptions()
                                .placeholder(R.drawable.image_placeholder)
                )
                .into(itemView.imageThumbnailExtraImageView)
        val extraSize = size - defaultShowSize()
        itemView.imageThumbnailExtraTextView.text =
                itemView.context.getString(
                        R.string.image_thumbnail_extra_text_place_holder,
                        extraSize
                )
        itemView.setOnClickListener {
            imageClickAction.run()
        }
    }

    companion object {
        fun getViewHolder(parent: ViewGroup?): ImageThumbNailExtraViewHolder {
            val itemView = LayoutInflater.from(parent?.context)
                    .inflate(R.layout.view_image_thumbnail_extra, parent, false)
            return ImageThumbNailExtraViewHolder(itemView)
        }
    }

}