package com.chalat.imagethumbnail

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.util.AttributeSet
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.item_image_thumbnail.view.*

/**
 *
 * Created by Chalat Chansima on 12/25/17.
 *
 */
class ImageThumbNail @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val adapter = ImageThumbNailAdapter(context)

    init {
        inflate(context, R.layout.item_image_thumbnail, this)
        val gridLayoutManager = DisableScrollGridLayoutManager(context, 3)
        imageThumbnailRecyclerView.layoutManager = gridLayoutManager
        imageThumbnailRecyclerView.adapter = adapter
    }

    fun setImageListUrl(imageList: List<String>) {
        adapter.replaceData(imageList)
    }

    class DisableScrollGridLayoutManager(context: Context?, spanCount: Int)
        : GridLayoutManager(context, spanCount) {

        override fun canScrollVertically(): Boolean {
            return false
        }

    }

}