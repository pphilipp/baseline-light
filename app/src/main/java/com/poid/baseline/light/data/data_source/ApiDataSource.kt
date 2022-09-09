package com.poid.baseline.light.data.data_source

import com.poid.baseline.light.data.remote.ApiService

class ApiDataSource(
    private val apiService: ApiService
) {

    suspend fun fetchSomething(amount: Int): String? =
        apiService.fetchAlbums(amount).field
}