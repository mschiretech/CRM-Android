package com.mschiretech.crm_android.core.internet
sealed class NetworkState {
    object Available : NetworkState()
    object Unavailable : NetworkState()
    object Losing : NetworkState()
    object Lost : NetworkState()
}