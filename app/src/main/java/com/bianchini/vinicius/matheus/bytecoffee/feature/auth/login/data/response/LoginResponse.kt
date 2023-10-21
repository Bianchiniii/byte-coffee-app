package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.data.response

import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.model.ProfileToken
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token: String
)

fun LoginResponse.toProfileToken() = ProfileToken(
    token = token
)