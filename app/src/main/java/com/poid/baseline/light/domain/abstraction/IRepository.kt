package com.poid.baseline.light.domain.abstraction

interface IRepository {
    suspend fun get(amount: Int): String?
}