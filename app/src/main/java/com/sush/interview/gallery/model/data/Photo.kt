package com.sush.interview.gallery.model.data

import com.google.gson.annotations.SerializedName

/**
 * Holds photo parameters.
 *
 * @author Jakub Kalina (kalina.kuba@gmail.com)
 */
data class Photo(
    @SerializedName("albumId") val albumId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String
)