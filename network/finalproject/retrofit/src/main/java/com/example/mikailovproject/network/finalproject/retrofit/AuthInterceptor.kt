package com.example.mikailovproject.network.finalproject.retrofit

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor() : Interceptor {
    private var authToken = ""

    fun updateToken(token: String) {
        authToken = token
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder().addHeader("Authorization", authToken)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
