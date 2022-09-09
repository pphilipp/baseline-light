package com.poid.baseline.light.domain

interface UseCase<out T, in P> {

    fun execute(params: P): T
}