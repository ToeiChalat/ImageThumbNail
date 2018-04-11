package com.chalat.imagethumbnail

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.stfalcon.frescoimageviewer.ImageViewer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.Collections.emptyList

/**
 *
 * Created by Chalat Chansima on 12/25/17.
 *
 */
internal class ImageThumbNailAdapter
@JvmOverloads constructor(private val context: Context//,
//                          private val imageUrlConverterAsync: ((List<String>) -> Observable<List<String>>)? = null,
//                          private val imageUrlConverterSync: ((List<String>) -> List<String>)? = null
)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val thumbNailType = 1
    private val thumbNailExtraType = 2

    private var imageList = emptyList<String>()
//    private var imageUrlList = ArrayList<String>()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val action = Action { showImageViewer(position) }
        if (getItemViewType(position) == thumbNailType) {
            (holder as ImageThumbNailViewHolder)
                    .bindView(imageList[position], action)
        } else {
            (holder as ImageThumbNailExtraViewHolder)
                    .bindView(imageList[position], imageList.size, action)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == thumbNailType) {
            ImageThumbNailViewHolder.getViewHolder(parent)
        } else {
            ImageThumbNailExtraViewHolder.getViewHolder(parent)
        }
    }

    override fun getItemCount(): Int {
        return if (isImageSizeLessThanThree()) imageList.size else 3
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0, 1 -> {
                thumbNailType
            }
            else -> {
                if (isImageSizeLessThanThree()) {
                    thumbNailType
                } else {
                    thumbNailExtraType
                }
            }
        }
    }

    internal fun replaceData(imageList: List<String>) {
        this.imageList = imageList
//        convertImageKeyToUrl(imageList)
        notifyDataSetChanged()
    }

//    private fun convertImageKeyToUrl(imageList: List<String>) {
//        imageUrlConverterAsync?.let { convert ->
//            convert(imageList)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(
//                            { this.imageUrlList.addAll(it) },
//                            { Timber.e(it) }
//                    )
//        }
//        imageUrlConverterSync?.let { convert ->
//            this.imageUrlList = ArrayList(convert(imageList))
//        }
//    }

    private fun showImageViewer(position: Int) {
        ImageViewer.Builder(context, imageList)
                .setStartPosition(position)
                .show()
    }

    private fun isImageSizeLessThanThree() = imageList.size <= 3
}