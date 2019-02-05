package com.sush.interview.gallery.model

import com.sush.interview.gallery.model.data.Album
import com.sush.interview.gallery.model.data.Photo
import com.sush.interview.gallery.model.data.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET



interface RetrofitApi {

    @GET("/users")
    fun downloadUsers(): Call<List<User>>

    @GET("/albums")
    fun downloadAlbums(): Call<List<Album>>

    @GET("/photos")
    fun downloadPhotos(): Call<List<Photo>>
}