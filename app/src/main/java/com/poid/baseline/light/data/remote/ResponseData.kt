package com.poid.baseline.light.data.remote

import com.squareup.moshi.Json

data class ResponseData(
    @Json(name = "some_field") val field: String?
)