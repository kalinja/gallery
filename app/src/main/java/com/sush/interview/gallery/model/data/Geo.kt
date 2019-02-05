package com.sush.interview.gallery.model.data

import com.google.gson.annotations.SerializedName

data class Geo (
    @SerializedName("lat") val lat: String,
    @SerializedName("ltg") val ltg: String
)