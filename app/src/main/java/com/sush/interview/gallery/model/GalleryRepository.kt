package com.sush.interview.gallery.model

import com.sush.interview.gallery.model.data.Album
import com.sush.interview.gallery.model.data.Photo
import com.sush.interview.gallery.model.data.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Handles web calls to download data.
 *
 * @author Jakub Kalina (kalina.kuba@gmail.com)
 */
@Singleton
class GalleryRepository @Inject constructor(private val retrofit: Retrofit) {

    /**
     * Gets list of all users.
     *
     * @param completion Function which is called after users are loaded, or loading failed.
     */
    fun getUsers(completion: (List<User>?, Error?) -> Unit) {

        val retrofitApi = retrofit.create(RetrofitApi::class.java)

        val call = retrofitApi.downloadUsers()
        call.enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful && response.body() != null) {
                    val users: List<User> = response.body()!!
                    completion(users, null)
                }
            }

        })
    }
    /**
     * Gets list of all albums.
     *
     * @param completion Function which is called after albums are loaded, or loading failed.
     */
    fun getAlbums(completion: (List<Album>?, Error?) -> Unit) {
        val retrofitApi = retrofit.create(RetrofitApi::class.java)

        val call = retrofitApi.downloadAlbums()
        call.enqueue(object : Callback<List<Album>> {
            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                if (response.isSuccessful && response.body() != null) {
                    val users: List<Album> = response.body()!!
                    completion(users, null)
                }
            }
        })
    }

    /**
     * Gets list of all photos.
     *
     * @param completion Function which is called after photos are loaded, or loading failed.
     */
    fun getPhotos(completion: (List<Photo>?, Error?) -> Unit) {
        val retrofitApi = retrofit.create(RetrofitApi::class.java)

        val call = retrofitApi.downloadPhotos()
        call.enqueue(object : Callback<List<Photo>> {
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if (response.isSuccessful && response.body() != null) {
                    val users: List<Photo> = response.body()!!
                    completion(users, null)
                }
            }

        })
    }
}