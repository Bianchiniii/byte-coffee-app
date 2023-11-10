package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.usecase

import com.bianchini.vinicius.matheus.bytecoffee.R

class ValidatePasswordRegisterUseCase {

    operator fun invoke(
        password: String
    ): RegisterValidatorResult {
        if (password.isEmpty()) {
            return RegisterValidatorResult(
                errorMessage = R.string.sign_up_password_error,
            )
        }

        return RegisterValidatorResult(
            isSuccess = true,
        )
    }
}