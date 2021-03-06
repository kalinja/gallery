package com.sush.interview.gallery.model.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.sush.interview.gallery.model.GalleryRepository
import com.sush.interview.gallery.model.data.Album
import com.sush.interview.gallery.model.data.User

/**
 * ViewModel for loading users and albums.
 * Holds list of loaded users and albums.
 *
 * @author Jakub Kalina (kalina.kuba@gmail.com)
 */
class UserAlbumsViewModel(private val galleryRepo: GalleryRepository) : ViewModel() {
    var userList = MutableLiveData<List<User>>()
    var albumList = MutableLiveData<List<Album>>()

    init {
        loadUsersFromRepository()
        loadAlbumsFromRepository()
    }

    fun loadUsersFromRepository() {
        galleryRepo.getUsers { list, _ ->
            userList.value = list
        }
    }

    fun loadAlbumsFromRepository() {
        galleryRepo.getAlbums { list, _ ->
            albumList.value = list
        }
    }

    class Factory(private val galleryRepo: GalleryRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserAlbumsViewModel(galleryRepo) as T
        }
    }

}