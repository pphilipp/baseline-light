package com.poid.baseline.light.domain.abstraction

interface IUseCase<out T, in P> {

    fun execute(params: P): T
}