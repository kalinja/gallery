package com.sush.interview.gallery.model.data

import com.google.gson.annotations.SerializedName

/**
 * Holds album parameters.
 *
 * @author Jakub Kalina (kalina.kuba@gmail.com)
 */
data class Album(
    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String
) : UserAlbum()