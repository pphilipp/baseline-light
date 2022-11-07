package com.poid.baseline.light.data.remote.dto

import com.squareup.moshi.Json

data class ResponseDto(
    @Json(name = "some_field") val field: String?
)