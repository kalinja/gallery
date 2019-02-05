package com.sush.interview.gallery.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sush.interview.gallery.R
import com.sush.interview.gallery.model.data.User
import com.sush.interview.gallery.view.UserAlbumsAdapter.Companion.USER
import kotlinx.android.synthetic.main.user_item_view.view.*

class UserAlbumsAdapter : RecyclerView.Adapter<UserAlbumViewHolder>() {
    private var users: ArrayList<User> = ArrayList()

    companion object {
        const val USER = 1
        const val ALBUM = 2
    }

    fun usersLoaded(users: List<User>) {
        this.users.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAlbumViewHolder {
        return if (viewType == USER) {
            createUserViewHolder(parent)
        } else {
            createUserViewHolder(parent)
        }
    }

    private fun createUserViewHolder(parent: ViewGroup) : UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item_view, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserAlbumViewHolder, position: Int) {
        if (getItemViewType(position) == USER) {
            (holder as UserViewHolder).setUser(users[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return USER
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