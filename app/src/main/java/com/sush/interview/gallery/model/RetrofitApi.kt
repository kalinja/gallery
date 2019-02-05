package com.sush.interview.gallery.model

import com.sush.interview.gallery.model.data.Album
import com.sush.interview.gallery.model.data.Photo
import com.sush.interview.gallery.model.data.User
import retrofit2.Call
import retrofit2.http.GET

/**
 * Retrofit specification of api where to download data.
 *
 * @author Jakub Kalina (kalina.kuba@gmail.com)
 */
interface RetrofitApi {

    /**
     * Gets list of all users.
     *
     * @return User list.
     */
    @GET("/users")
    fun downloadUsers(): Call<List<User>>

    /**
     * Gets list of all albums.
     *
     * @return Album list.
     */
    @GET("/albums")
    fun downloadAlbums(): Call<List<Album>>

    /**
     * Gets list of all photos.
     *
     * @return Photos list.
     */
    @GET("/photos")
    fun downloadPhotos(): Call<List<Photo>>
}