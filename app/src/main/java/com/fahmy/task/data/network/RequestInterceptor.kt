package com.fahmy.task.data.network

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain
            .request().url.newBuilder()
            .build()

        val newRequest = chain
            .request()
            .newBuilder()
            .url(original)
            .build()
        return chain.proceed(newRequest)
    }
}
