package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.model

data class LoginRequest(
    val login: String,
    val password: String
)