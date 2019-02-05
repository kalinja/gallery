package com.sush.interview.gallery.core

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sush.interview.gallery.R
import kotlinx.android.synthetic.main.activity_single_fragment.*


abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_fragment)
        setupToolbar()
        addFragmentToActivity()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getToolbarTitle()
    }

    private fun addFragmentToActivity() {
        val fragmentManager = fragmentManager.beginTransaction()
        fragmentManager.add(R.id.fragmentContainer, getFragment()).commit()
    }

    protected abstract fun getToolbarTitle(): String

    protected abstract fun getFragment(): Fragment
}