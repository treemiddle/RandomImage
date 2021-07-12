package com.jay.randomimage.network

import com.jay.randomimage.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val requestUrl = chain.request().url.toString()

        if (requestUrl.startsWith(BuildConfig.BASE_URL)) {
            request.addHeader(AUTHORIZATION, VALUE)
        }

        return chain.proceed(request.build())
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
        private const val VALUE = "Client-ID ${BuildConfig.ACCESS_KEY}"
    }
}