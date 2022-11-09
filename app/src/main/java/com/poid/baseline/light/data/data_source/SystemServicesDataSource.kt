package com.poid.baseline.light.data.data_source

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.poid.baseline.light.domain.abstraction.IDomainMapper
import com.poid.baseline.light.domain.model.ConnectionStateModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class SystemServicesDataSource(
    private val connectivityManager: ConnectivityManager,
    private val mapper: IDomainMapper<Boolean, ConnectionStateModel>
) {

    fun observeIsNetworkConnectionExist(): Flow<ConnectionStateModel> = callbackFlow {
        val callback = networkCallback { connectionState ->
            trySend(mapper.map(connectionState))
        }

        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest, callback)

        val currentState = getCurrentConnectivityState(connectivityManager)
        trySend(mapper.map(currentState))

        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }

    private fun getCurrentConnectivityState(
        connectivityManager: ConnectivityManager,
    ): Boolean = connectivityManager.allNetworks.any { network ->
        connectivityManager.getNetworkCapabilities(network)
            ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            ?: false
    }

    private fun networkCallback(
        callback: (Boolean) -> Unit,
    ): ConnectivityManager.NetworkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            callback(true)
        }

        override fun onLost(network: Network) {
            callback(false)
        }
    }

}