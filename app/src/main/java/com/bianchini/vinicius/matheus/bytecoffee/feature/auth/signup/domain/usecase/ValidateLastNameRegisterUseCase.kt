package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.usecase

import com.bianchini.vinicius.matheus.bytecoffee.R

class ValidateLastNameRegisterUseCase {

    operator fun invoke(
        lastName: String
    ): RegisterValidatorResult {
        if (lastName.isEmpty()) {
            return RegisterValidatorResult(
                errorMessage = R.string.sign_up_last_name_error,
            )
        }

        return RegisterValidatorResult(
            isSuccess = true,
        )
    }
}