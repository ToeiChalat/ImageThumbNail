package com.chalat.imagethumbnail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.reactivex.functions.Action
import kotlinx.android.synthetic.main.view_image_thumbnail.view.*

/**
 *
 * Created by Chalat Chansima on 12/25/17.
 *
 */
internal class ImageThumbNailViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    internal fun bindView(imageUrl: String, action: Action) {
        Glide.with(itemView)
                .load(imageUrl)
                .apply(
                        RequestOptions()
                                .placeholder(R.drawable.image_placeholder)
                )
                .into(itemView.imageThumbnailImageView)
        itemView.setOnClickListener { action.run() }
    }

    companion object {
        fun getViewHolder(parent: ViewGroup?) : ImageThumbNailViewHolder {
            val itemView = LayoutInflater.from(parent?.context)
                    .inflate(R.layout.view_image_thumbnail, parent, false)
            return ImageThumbNailViewHolder(itemView)
        }
    }

}
