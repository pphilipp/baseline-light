package com.poid.baseline.light.domain

import com.poid.baseline.light.domain.abstraction.UseCase

suspend fun <R> UseCase<R, Nothing?>.execute() = execute(null)