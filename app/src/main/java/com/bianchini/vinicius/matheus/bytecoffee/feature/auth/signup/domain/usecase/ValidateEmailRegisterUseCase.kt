package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.usecase

import com.bianchini.vinicius.matheus.bytecoffee.R

class ValidateEmailRegisterUseCase {

    operator fun invoke(
        email: String
    ): RegisterValidatorResult {
        if (email.isEmpty()) {
            return RegisterValidatorResult(
                errorMessage = R.string.sign_up_email_error,
            )
        }

        return RegisterValidatorResult(
            isSuccess = true,
        )
    }
}