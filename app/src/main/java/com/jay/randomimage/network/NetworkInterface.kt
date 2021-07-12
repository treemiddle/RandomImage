package com.jay.randomimage.network

import com.jay.randomimage.model.ImageResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkInterface {

    @GET("photos/random")
    fun getPhotos(
        @Query("query") query: String? = null
    ): Single<ImageResponse>
}