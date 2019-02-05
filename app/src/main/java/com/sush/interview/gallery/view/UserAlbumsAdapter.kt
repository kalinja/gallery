package com.sush.interview.gallery.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sush.interview.gallery.R
import com.sush.interview.gallery.model.data.Album
import com.sush.interview.gallery.model.data.User
import com.sush.interview.gallery.model.data.UserAlbum
import com.sush.interview.gallery.view.UserAlbumsAdapter.Companion.ALBUM
import com.sush.interview.gallery.view.UserAlbumsAdapter.Companion.USER
import kotlinx.android.synthetic.main.album_item_view.view.*
import kotlinx.android.synthetic.main.user_item_view.view.*

class UserAlbumsAdapter : RecyclerView.Adapter<UserAlbumViewHolder>() {
    private var users: ArrayList<User> = ArrayList()
    private var albums: ArrayList<Album> = ArrayList()

    private var userAlbums: ArrayList<UserAlbum> = ArrayList()

    companion object {
        const val USER = 1
        const val ALBUM = 2
    }

    fun usersLoaded(users: List<User>) {
        this.users.addAll(users)
    }

    fun albumsLoaded(albums: List<Album>) {
        this.albums.addAll(albums)
    }

    fun loadingFinished() {
        val userAlbumsMap = HashMap<User, ArrayList<Album>>()
        users.forEach { user ->
            albums.forEach {album ->
                if (user.id == album.userId) {
                    if (!userAlbumsMap.containsKey(user)) {
                        userAlbumsMap[user] = ArrayList()
                    }
                    userAlbumsMap[user]?.add(album)
                }
            }
        }

        userAlbumsMap.forEach {
            userAlbums.add(it.key)
            it.value.forEach {album ->
                userAlbums.add(album)
            }
        }

        users.clear()
        albums.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAlbumViewHolder {
        return if (viewType == USER) {
            createUserViewHolder(parent)
        } else {
            createAlbumViewHolder(parent)
        }
    }

    private fun createUserViewHolder(parent: ViewGroup) : UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item_view, parent, false)
        return UserViewHolder(view)
    }

    private fun createAlbumViewHolder(parent: ViewGroup) : AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.album_item_view, parent, false)
        return AlbumViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userAlbums.size
    }

    override fun onBindViewHolder(holder: UserAlbumViewHolder, position: Int) {
        if (getItemViewType(position) == USER) {
            (holder as UserViewHolder).setUser(userAlbums[position] as User)
        } else {
            (holder as AlbumViewHolder).setAlbum(userAlbums[position] as Album)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (userAlbums[position] is Album) {
            ALBUM
        } else {
            USER
        }
    }
}

abstract class UserAlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    protected abstract fun getViewType() : Int
}

class UserViewHolder(private val view: View) : UserAlbumViewHolder(view) {
    private lateinit var user: User
    override fun getViewType(): Int {
        return USER
    }

    fun setUser(user: User) {
        this.user = user
        view.username.text = user.name
    }
}

class AlbumViewHolder(private val view: View) : UserAlbumViewHolder(view) {
    private lateinit var album: Album
    override fun getViewType(): Int {
        return ALBUM
    }

    fun setAlbum(album: Album) {
        this.album = album
        view.albumName.text = album.title
    }
}