package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.usecase

import com.bianchini.vinicius.matheus.bytecoffee.R

class ValidateEmailLoginUseCase {

    operator fun invoke(
        email: String
    ): LoginValidatorResult {
        if (email.isEmpty()) {
            return LoginValidatorResult(
                errorMessage = R.string.sign_up_email_error,
            )
        }

        return LoginValidatorResult(
            isSuccess = true,
        )
    }
}