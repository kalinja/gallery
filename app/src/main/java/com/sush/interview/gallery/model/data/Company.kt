package com.sush.interview.gallery.model.data

import com.google.gson.annotations.SerializedName

/**
 * Holds Company parameters.
 *
 * @author Jakub Kalina (kalina.kuba@gmail.com)
 */
data class Company(
    @SerializedName("name") val name: String,
    @SerializedName("catchPhrase") val catchPhrase: String,
    @SerializedName("bs") val bs: String
)