package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.usecase

import androidx.annotation.StringRes

data class LoginValidatorResult(
    @StringRes val errorMessage: Int? = null,
    val isSuccess: Boolean = false,
)