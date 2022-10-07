package com.poid.baseline.light.domain.abstraction

interface UseCase<out T, in P> {

    fun execute(params: P): T
}