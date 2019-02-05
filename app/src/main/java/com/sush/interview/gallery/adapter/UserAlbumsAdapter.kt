package com.sush.interview.gallery.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sush.interview.gallery.R
import com.sush.interview.gallery.activity.AlbumActivity
import com.sush.interview.gallery.model.data.Album
import com.sush.interview.gallery.model.data.User
import com.sush.interview.gallery.model.data.UserAlbum
import com.sush.interview.gallery.adapter.UserAlbumsAdapter.Companion.ALBUM
import com.sush.interview.gallery.adapter.UserAlbumsAdapter.Companion.USER
import kotlinx.android.synthetic.main.album_item_view.view.*
import kotlinx.android.synthetic.main.user_item_view.view.*
import android.net.Uri
import android.widget.Toast

/**
 * Adapter which holds list of users and theirs albums.
 *
 * @author Jakub Kalina (kalina.kuba@gmail.com)
 */
class UserAlbumsAdapter : RecyclerView.Adapter<UserAlbumViewHolder>() {
    private var users: ArrayList<User> = ArrayList()
    private var albums: ArrayList<Album> = ArrayList()

    private var userAlbums: ArrayList<UserAlbum> = ArrayList()

    companion object {
        const val USER = 1
        const val ALBUM = 2
    }

    /**
     * Call this when new users are loaded.
     *
     * @param users List of new loaded users.
     */
    fun usersLoaded(users: List<User>) {
        this.users.clear()
        this.users.addAll(users)
    }

    /**
     * Call this when new albums are loaded.
     *
     * @param albums List of new loaded albums.
     */
    fun albumsLoaded(albums: List<Album>) {
        this.albums.clear()
        this.albums.addAll(albums)
    }

    /**
     * Call this when both album and users are loaded.
     * This function maps albums to their owners and creates list to show.
     */
    fun loadingFinished() {
        val userAlbumsMap = HashMap<User, ArrayList<Album>>()
        users.forEach { user ->
            albums.forEach { album ->
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
            it.value.forEach { album ->
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

    private fun createUserViewHolder(parent: ViewGroup): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item_view, parent, false)
        return UserViewHolder(view)
    }

    private fun createAlbumViewHolder(parent: ViewGroup): AlbumViewHolder {
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

/**
 * General ViewHolder for both user view and album view.
 */
abstract class UserAlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    protected abstract fun getViewType(): Int
}

/**
 * ViewHolder for User view.
 */
class UserViewHolder(private val view: View) : UserAlbumViewHolder(view) {
    private lateinit var user: User
    override fun getViewType(): Int {
        return USER
    }

    /**
     * Sets user to this ViewHolder and update its view.
     */
    fun setUser(user: User) {
        this.user = user
        view.username.text = user.name
        view.email.text = user.email
        view.navigationButton.setOnClickListener {
            startNavigation()
        }
    }

    private fun startNavigation() {
        val gmmIntentUri = Uri.parse("geo:0,0?q=${user.address.geo.lat},${user.address.geo.lng}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        if (mapIntent.resolveActivity(view.context.packageManager) != null) {
            view.context.startActivity(mapIntent)
        } else {
            Toast.makeText(view.context, R.string.navigation_app_not_found, Toast.LENGTH_LONG).show()
        }
    }
}

/**
 * ViewHolder for Album view.
 */
class AlbumViewHolder(private val view: View) : UserAlbumViewHolder(view) {
    private lateinit var album: Album
    override fun getViewType(): Int {
        return ALBUM
    }

    /**
     * Sets album to this ViewHolder and update its view.
     */
    fun setAlbum(album: Album) {
        this.album = album
        view.albumName.text = album.title
        view.setOnClickListener {
            AlbumActivity.call(view.context, album.id, album.title)
        }
    }
}