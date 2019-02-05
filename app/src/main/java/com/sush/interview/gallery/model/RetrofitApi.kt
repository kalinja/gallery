package com.sush.interview.gallery.model

import com.sush.interview.gallery.model.data.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET



interface RetrofitApi {

    @GET("/users")
    fun downloadUsers(): Call<List<User>>
}