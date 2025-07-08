package com.mschiretech.crm_android.core.internet


import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged

fun Context.observeNetworkState(): Flow<NetworkState> = callbackFlow {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            trySend(NetworkState.Available)
        }

        override fun onLosing(network: Network, maxMsToLive: Int) {
            trySend(NetworkState.Losing)
        }

        override fun onLost(network: Network) {
            trySend(NetworkState.Lost)
        }

        override fun onUnavailable() {
            trySend(NetworkState.Unavailable)
        }
    }

    val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .build()

    connectivityManager.registerNetworkCallback(networkRequest, networkCallback)

    // Check initial state
    val activeNetwork = connectivityManager.activeNetwork
    val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
    val isConnected = capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true

    trySend(if (isConnected) NetworkState.Available else NetworkState.Unavailable)

    awaitClose {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}.distinctUntilChanged()