package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.usecase

import com.bianchini.vinicius.matheus.bytecoffee.R

class ValidateNumberRegisterUseCase {

    operator fun invoke(
        number: String
    ): RegisterValidatorResult {
        if (number.isEmpty()) {
            return RegisterValidatorResult(
                errorMessage = R.string.sign_up_number_error,
            )
        }

        return RegisterValidatorResult(
            isSuccess = true,
        )
    }
}