package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.usecase

import com.bianchini.vinicius.matheus.bytecoffee.R

class ValidatePasswordLoginUseCase {

    operator fun invoke(
        password: String
    ): LoginValidatorResult {
        if (password.isEmpty()) {
            return LoginValidatorResult(
                errorMessage = R.string.sign_up_password_error,
            )
        }

        return LoginValidatorResult(
            isSuccess = true,
        )
    }
}