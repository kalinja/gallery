package com.sush.interview.gallery.core

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sush.interview.gallery.R
import kotlinx.android.synthetic.main.activity_single_fragment.*


/**
 * Base Activity containing single fragment.
 *
 * @author Jakub Kalina (kalina.kuba@gmail.com)
 */
abstract class SingleFragmentActivity : AppCompatActivity() {

    companion object {
        const val FRAGMENT_TAG = "fragment_attached"
    }

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
        if (fragmentManager.findFragmentByTag(FRAGMENT_TAG) == null) {
            val transaction = fragmentManager.beginTransaction()
            transaction.add(R.id.fragmentContainer, getFragment(), FRAGMENT_TAG).commit()
        }
    }

    /**
     * Gets toolbar title.
     *
     * @return Toolbar title.
     */
    protected abstract fun getToolbarTitle(): String

    /**
     * Gets Fragment which is attached to this Activity.
     *
     * @return Fragment which will be attached.
     */
    protected abstract fun getFragment(): Fragment
}