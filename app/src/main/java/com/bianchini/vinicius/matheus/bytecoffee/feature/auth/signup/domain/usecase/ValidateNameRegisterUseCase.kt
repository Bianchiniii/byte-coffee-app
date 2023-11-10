package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.usecase

import com.bianchini.vinicius.matheus.bytecoffee.R

class ValidateNameRegisterUseCase {

    operator fun invoke(
        name: String
    ): RegisterValidatorResult {
        if (name.isEmpty()) {
            return RegisterValidatorResult(
                errorMessage = R.string.sign_up_name_error,
            )
        }

        return RegisterValidatorResult(
            isSuccess = true,
        )
    }
}