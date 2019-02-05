package com.sush.interview.gallery.activity

import android.app.Fragment
import android.os.Bundle
import com.sush.interview.gallery.R
import com.sush.interview.gallery.core.BaseActivity
import com.sush.interview.gallery.fragment.MainFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getToolbarTitle(): String {
        return getString(R.string.app_name)
    }

    override fun getFragment(): Fragment {
        return MainFragment()
    }
}
