package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.data.response

import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.model.ProfileToken
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.data.response.ProfileResponse
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.data.response.toDomain
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("user")
    val profile: ProfileResponse
)

fun LoginResponse.toProfileToken() = ProfileToken(
    token = token,
    profile = profile.toDomain()
)