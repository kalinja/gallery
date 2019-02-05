package com.sush.interview.gallery.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

class GridViewItem : ImageView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // This is the key that will make the height equivalent to its width
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}