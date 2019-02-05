package com.sush.interview.gallery.model.data

import com.google.gson.annotations.SerializedName

/**
 * Holds Geo location parameters.
 *
 * @author Jakub Kalina (kalina.kuba@gmail.com)
 */
data class Geo(
    @SerializedName("lat") val lat: String,
    @SerializedName("lng") val lng: String
)