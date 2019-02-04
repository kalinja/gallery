package com.sush.interview.gallery.activity

import android.app.Fragment
import com.sush.interview.gallery.R
import com.sush.interview.gallery.core.BaseActivity
import com.sush.interview.gallery.fragment.AlbumFragment

class AlbumActivity : BaseActivity() {

    override fun getFragment(): Fragment {
        return AlbumFragment()
    }

    override fun getToolbarTitle(): String {
        return getString(R.string.album_title)
    }
}