package com.sush.interview.gallery.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.sush.interview.gallery.model.data.User

class UserAlbumsViewModel(private val galleryRepo: GalleryRepository) : ViewModel() {
    var userList = MutableLiveData<List<User>>()

    init {
        loadReposFromRepository()
    }

    private fun loadReposFromRepository() {
        galleryRepo.getUsers { list, error ->
            userList.value = list
        }
    }

    class Factory(private val galleryRepo: GalleryRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserAlbumsViewModel(galleryRepo) as T
        }
    }

}