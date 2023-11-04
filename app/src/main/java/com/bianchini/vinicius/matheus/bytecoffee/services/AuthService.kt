package com.bianchini.vinicius.matheus.bytecoffee.services

import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.data.response.LoginResponse
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.model.LoginRequest
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.model.NewAccount
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.data.response.ProfileResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("auth/login")
    fun login(
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("auth/register")
    fun register(
        @Body newAccount: NewAccount
    ): Call<ProfileResponse>
}