package com.sush.interview.gallery.activity

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.sush.interview.gallery.core.SingleFragmentActivity
import com.sush.interview.gallery.fragment.AlbumFragment

/**
 * Activity which displays photos from album given by albumId in a grid.
 *
 * @author Jakub Kalina (kalina.kuba@gmail.com)
 */
class AlbumActivity : SingleFragmentActivity() {

    companion object {
        const val EXTRA_ALBUM_ID = "album_id"
        private const val EXTRA_ALBUM_NAME = "album_name"
        /**
         * Calls this activity.
         *
         * @param callingContext: A Context from which this activity is called.
         * @param albumId: Id of album to show.
         * @param albumName: Name of album to show.
         */
        fun call(callingContext: Context, albumId: Int, albumName: String) {
            val intent = Intent(callingContext, AlbumActivity::class.java)
            intent.putExtra(EXTRA_ALBUM_ID, albumId)
            intent.putExtra(EXTRA_ALBUM_NAME, albumName)
            callingContext.startActivity(intent)
        }
    }

    private lateinit var albumName: String
    private var albumId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        albumName = intent.getStringExtra(EXTRA_ALBUM_NAME)
        albumId = intent.getIntExtra(EXTRA_ALBUM_ID, 1)
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun getFragment(): Fragment {
        val fragment = AlbumFragment()
        fragment.arguments = Bundle()
        fragment.arguments.putInt(EXTRA_ALBUM_ID, albumId)
        return fragment
    }

    override fun getToolbarTitle(): String {
        return albumName
    }
}