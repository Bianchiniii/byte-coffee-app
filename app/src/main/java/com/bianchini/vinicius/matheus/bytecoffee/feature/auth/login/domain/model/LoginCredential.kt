package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.model

import androidx.annotation.StringRes

data class LoginCredential(
    val login: String = "",
    val password: String = ""
)

data class LoginCredentialError(
    @StringRes val loginError: Int? = null,
    @StringRes val passwordError: Int? = null
)