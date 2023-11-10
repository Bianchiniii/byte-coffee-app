package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.usecase

import com.bianchini.vinicius.matheus.bytecoffee.R

class ValidateStreetRegisterUseCase {

    operator fun invoke(
        street: String
    ): RegisterValidatorResult {
        if (street.isEmpty()) {
            return RegisterValidatorResult(
                errorMessage = R.string.sign_up_street_error,
            )
        }

        return RegisterValidatorResult(
            isSuccess = true,
        )
    }
}