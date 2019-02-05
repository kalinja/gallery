package com.sush.interview.gallery.fragment

import android.app.Fragment
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sush.interview.gallery.GalleryApplication
import com.sush.interview.gallery.R
import com.sush.interview.gallery.adapter.UserAlbumsAdapter
import com.sush.interview.gallery.model.GalleryRepository
import com.sush.interview.gallery.model.data.Album
import com.sush.interview.gallery.model.data.User
import com.sush.interview.gallery.model.viewmodel.UserAlbumsViewModel
import kotlinx.android.synthetic.main.fragment_user_list.*
import javax.inject.Inject

/**
 * Loads and shows list of users with theirs albums.
 *
 * @author Jakub Kalina (kalina.kuba@gmail.com)
 */
class MainFragment : Fragment() {

    @Inject
    lateinit var galleryRepo: GalleryRepository
    private lateinit var userAlbumsViewModel: UserAlbumsViewModel
    private val userAlbumsAdapter: UserAlbumsAdapter = UserAlbumsAdapter()
    private var usersLoaded = false
    private var albumsLoaded = false

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = userAlbumsAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        GalleryApplication.instance.getApplicationComponent().inject(this)

        userAlbumsViewModel =
                ViewModelProviders.of(activity as AppCompatActivity, UserAlbumsViewModel.Factory(galleryRepo)).get(
                    UserAlbumsViewModel::class.java
                )
        userAlbumsViewModel.userList.observeForever {
            if (it != null) {
                usersLoaded(it)
            }
        }

        userAlbumsViewModel.albumList.observeForever {
            if (it != null) {
                albumsLoaded(it)
            }
        }
    }

    private fun usersLoaded(users: List<User>) {
        usersLoaded = true
        userAlbumsAdapter.usersLoaded(users)
        if (albumsLoaded) {
            loadingFinished()
        }
    }

    private fun albumsLoaded(albums: List<Album>) {
        albumsLoaded = true
        userAlbumsAdapter.albumsLoaded(albums)
        if (usersLoaded) {
            loadingFinished()
        }
    }

    private fun loadingFinished() {
        progress.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        userAlbumsAdapter.loadingFinished()
        userAlbumsAdapter.notifyDataSetChanged()
    }
}