package com.sush.interview.gallery.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.View
import com.sush.interview.gallery.utils.NetworkUtils
import kotlinx.android.synthetic.main.fragment_album.*
import kotlinx.android.synthetic.main.offline_layout.*

/**
 * Fragment containing refresh button from offline state.
 *
 * @author Jakub Kalina (kalina.kuba@gmail.com)
 */
abstract class RefreshFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshButton.setOnClickListener {
            refresh()
        }

        if (!NetworkUtils.isNetworkAvailable(activity)) {
            showOffline()
        }
    }

    /**
     * Called when refresh button is clicked.
     */
    protected abstract fun refresh()

    /**
     * Shows layout when not connected to internet.
     */
    protected open fun showOffline() {
        offlineLayout.visibility = View.VISIBLE
    }

    /**
     * Hides layout which is shown when not connected to internet.
     */
    protected fun hideOffline() {
        offlineLayout.visibility = View.GONE
    }
}