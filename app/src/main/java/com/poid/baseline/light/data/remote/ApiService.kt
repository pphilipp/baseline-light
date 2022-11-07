package com.poid.baseline.light.data.remote

import com.poid.baseline.light.data.remote.dto.ResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // FIXME: replace a url
    @GET("test/test/{amount}/test.json")
    suspend fun fetchAlbums(
        @Path(value = "amount", encoded = true) amount: Int
    ): ResponseDto

}