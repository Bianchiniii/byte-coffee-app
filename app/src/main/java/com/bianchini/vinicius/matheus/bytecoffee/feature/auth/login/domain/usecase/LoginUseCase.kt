package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.usecase

data class LoginUseCase(
    val validateEmailLoginUseCase: ValidateEmailLoginUseCase,
    val validatePasswordLoginUseCase: ValidatePasswordLoginUseCase
)