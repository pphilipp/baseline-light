package com.poid.baseline.light.data.repository

import com.poid.baseline.light.data.data_source.ApiDataSource
import com.poid.baseline.light.domain.abstraction.IRepository

class Repository(
    private val apiDataSource: ApiDataSource
) : IRepository {

    override suspend fun get(
        amount: Int
    ): String? = apiDataSource.fetchSomething(amount)

}