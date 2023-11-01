package com.bianchini.vinicius.matheus.bytecoffee.di.interceptor

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.profile.TokenLocalDataSource
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val tokenLocalDataSource: TokenLocalDataSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest: Request = chain.request().newBuilder()
            .addHeader(
                "Authorization",
                "Bearer ${"token"}"
            ).build()

        return chain.proceed(newRequest)
    }
}