package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.usecase

import androidx.annotation.StringRes

data class RegisterValidatorResult(
    @StringRes
    val errorMessage: Int? = null,
    val isSuccess: Boolean = false,
)