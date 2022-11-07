package com.poid.baseline.light.domain

import com.poid.baseline.light.domain.abstraction.IUseCase

suspend fun <R> IUseCase<R, Nothing?>.execute() = execute(null)