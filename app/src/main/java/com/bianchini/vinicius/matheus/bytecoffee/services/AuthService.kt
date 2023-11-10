package com.bianchini.vinicius.matheus.bytecoffee.services

import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.data.response.LoginResponse
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.model.LoginCredential
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.model.NewAccountForm
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.data.response.ProfileResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("auth/login")
    fun login(
        @Body loginCredential: LoginCredential
    ): Call<LoginResponse>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("auth/register")
    fun register(
        @Body newAccountForm: NewAccountForm
    ): Call<ProfileResponse>
}