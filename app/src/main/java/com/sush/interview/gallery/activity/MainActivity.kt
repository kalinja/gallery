package com.sush.interview.gallery.activity

import android.app.Fragment
import com.sush.interview.gallery.R
import com.sush.interview.gallery.core.SingleFragmentActivity
import com.sush.interview.gallery.fragment.MainFragment

/**
 * Main Activity which displays users and theirs albums.
 *
 * @author Jakub Kalina (kalina.kuba@gmail.com)
 */
class MainActivity : SingleFragmentActivity() {
    override fun getToolbarTitle(): String {
        return getString(R.string.app_name)
    }

    override fun getFragment(): Fragment {
        return MainFragment()
    }
}
