package com.poid.baseline.light.data.repository

import com.poid.baseline.light.data.data_source.ApiDataSource
import com.poid.baseline.light.data.data_source.SystemServicesDataSource
import com.poid.baseline.light.domain.abstraction.IRepository
import kotlinx.coroutines.flow.Flow

class Repository(
    private val apiDataSource: ApiDataSource,
    private val systemServicesDataSource: SystemServicesDataSource,
) : IRepository {

    override suspend fun get(
        amount: Int,
    ): String? = apiDataSource.fetchSomething(amount)

    override fun observeConnectivityState(): Flow<Boolean> =
        systemServicesDataSource.observeIsNetworkConnectionExist()

}