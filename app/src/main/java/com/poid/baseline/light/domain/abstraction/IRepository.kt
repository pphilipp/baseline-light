package com.poid.baseline.light.domain.abstraction

import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun get(amount: Int): String?
    fun observeConnectivityState(): Flow<Boolean>
}