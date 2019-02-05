package com.sush.interview.gallery.activity

import android.app.Fragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sush.interview.gallery.GalleryApplication
import com.sush.interview.gallery.R
import com.sush.interview.gallery.core.BaseActivity
import com.sush.interview.gallery.fragment.MainFragment
import com.sush.interview.gallery.model.GalleryRepository
import com.sush.interview.gallery.model.UserAlbumsViewModel
import com.sush.interview.gallery.model.data.User
import kotlinx.android.synthetic.main.fragment_user_list.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject lateinit var galleryRepo: GalleryRepository
    private lateinit var userAlbumsViewModel: UserAlbumsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GalleryApplication.instance.getApplicationComponent().inject(this)

        userAlbumsViewModel = ViewModelProviders.of(this, UserAlbumsViewModel.Factory(galleryRepo)).get(UserAlbumsViewModel::class.java)
        userAlbumsViewModel.userList.observeForever {
            if (it != null) {
                usersLoaded(it)
            }
        }
    }

    private fun usersLoaded(users: List<User>) {
        progress.visibility = View.GONE
    }

    override fun getToolbarTitle(): String {
        return getString(R.string.app_name)
    }

    override fun getFragment(): Fragment {
        return MainFragment()
    }
}
