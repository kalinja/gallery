package com.sush.interview.gallery.model.data

import com.google.gson.annotations.SerializedName

/**
 * Holds address parameters.
 *
 * @author Jakub Kalina (kalina.kuba@gmail.com)
 */
data class Address(
    @SerializedName("street") val street: String,
    @SerializedName("suite") val suite: String,
    @SerializedName("city") val city: String,
    @SerializedName("zipcode") val zipcode: String,
    @SerializedName("geo") val geo: Geo
)