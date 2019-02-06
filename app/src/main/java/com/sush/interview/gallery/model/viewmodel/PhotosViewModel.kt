package com.sush.interview.gallery.model.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.sush.interview.gallery.model.GalleryRepository
import com.sush.interview.gallery.model.data.Photo

/**
 * ViewModel for loading photos from [GalleryRepository].
 * Holds list of loaded photos.
 *
 * @author Jakub Kalina (kalina.kuba@gmail.com)
 */
class PhotosViewModel(private val galleryRepo: GalleryRepository) : ViewModel() {
    var photoList = MutableLiveData<List<Photo>>()

    init {
        loadPhotosFromRepository()
    }

    fun loadPhotosFromRepository() {
        galleryRepo.getPhotos { list, _ ->
            photoList.value = list
        }
    }

    class Factory(private val galleryRepo: GalleryRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PhotosViewModel(galleryRepo) as T
        }
    }
}