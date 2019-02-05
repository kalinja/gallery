package com.sush.interview.gallery.fragment

import android.app.Fragment
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sush.interview.gallery.GalleryApplication
import com.sush.interview.gallery.R
import com.sush.interview.gallery.activity.AlbumActivity.Companion.EXTRA_ALBUM_ID
import com.sush.interview.gallery.adapter.ImageAdapter
import com.sush.interview.gallery.model.GalleryRepository
import com.sush.interview.gallery.model.data.Photo
import com.sush.interview.gallery.model.viewmodel.PhotosViewModel
import kotlinx.android.synthetic.main.fragment_album.*
import javax.inject.Inject

/**
 * Loads and shows photos for selected album.
 *
 * @author Jakub Kalina (kalina.kuba@gmail.com)
 */
class AlbumFragment : Fragment() {

    @Inject
    lateinit var galleryRepo: GalleryRepository
    private lateinit var photosViewModel: PhotosViewModel
    private lateinit var imageAdapter: ImageAdapter

    private var albumId = 1

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GalleryApplication.instance.getApplicationComponent().inject(this)
        imageAdapter = ImageAdapter(activity)
        galleryView.adapter = imageAdapter

        albumId = arguments.getInt(EXTRA_ALBUM_ID)

        photosViewModel = ViewModelProviders.of(
            activity as AppCompatActivity,
            PhotosViewModel.Factory(galleryRepo)
        ).get(PhotosViewModel::class.java)

        photosViewModel.photoList.observeForever {
            if (it != null) {
                photosLoaded(it)
            }
        }
    }

    private fun photosLoaded(photos: List<Photo>) {
        val filteredPhotos = photos.filter { it.albumId == albumId }
        imageAdapter.photosLoaded(filteredPhotos)
        progress.visibility = View.GONE
        galleryView.visibility = View.VISIBLE
    }
}