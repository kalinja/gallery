package com.sush.interview.gallery.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.sush.interview.gallery.model.data.Photo
import com.sush.interview.gallery.view.GridViewItem

class ImageAdapter(val context: Context) : BaseAdapter() {
    private var photos = ArrayList<Photo>()

    fun photosLoaded(photos: List<Photo>) {
        this.photos.addAll(photos)
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val imageView: GridViewItem
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = GridViewItem(context)
            imageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        } else {
            imageView = convertView as GridViewItem
        }
        Picasso.get()
            .load(photos[position].thumbnailUrl)
            .into(imageView)
        return imageView
    }

    override fun getItem(position: Int): Any? = null

    override fun getItemId(position: Int): Long = 0L

    override fun getCount(): Int {
        return photos.size
    }
}