package com.example.moviehitapp.utils;

import android.net.NetworkInfo;

/**Class that just will globally transfer connection status and it's info*/
public final class ConnectivityUtils {

    private static NetworkInfo networkInfo;

    public static void setNetworkInfo(NetworkInfo networkInfo) {
        ConnectivityUtils.networkInfo = networkInfo;
    }

    public static NetworkInfo getNetworkInfo() {
        return networkInfo;
    }

    public static synchronized boolean isInternetConnected() {
        return (networkInfo != null
                && networkInfo.isAvailable()
                && networkInfo.isConnected());
    }
}

