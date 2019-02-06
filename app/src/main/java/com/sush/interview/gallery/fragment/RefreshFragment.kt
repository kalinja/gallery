package com.sush.interview.gallery.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.offline_layout.*

abstract class RefreshFragment : Fragment() {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshButton.setOnClickListener {
            refresh()
        }
    }

    protected abstract fun refresh()
}