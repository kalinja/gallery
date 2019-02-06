package com.sush.interview.gallery.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.View
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
    }

    /**
     * Called when refresh button is clicked.
     */
    protected abstract fun refresh()
}