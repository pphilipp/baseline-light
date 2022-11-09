package com.poid.baseline.light.domain.abstraction

import com.poid.baseline.light.domain.model.ConnectionStateModel
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun get(amount: Int): String?

    fun observeConnectivityState(): Flow<ConnectionStateModel>
}