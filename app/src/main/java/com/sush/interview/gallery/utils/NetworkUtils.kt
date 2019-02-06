package com.sush.interview.gallery.utils

import android.content.Context
import android.net.ConnectivityManager


/**
 * Network utils functions.
 *
 * @author Jakub Kalina (kalina.kuba@gmail.com)
 */
class NetworkUtils {
    companion object {

        /**
         * Checks if network is available.
         *
         * @param context A Context.
         */
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }
}