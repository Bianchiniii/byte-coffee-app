package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.usecase

import com.bianchini.vinicius.matheus.bytecoffee.R

class ValidateNeighborhoodRegisterUseCase {

    operator fun invoke(
        neighborhood: String
    ): RegisterValidatorResult {
        if (neighborhood.isEmpty()) {
            return RegisterValidatorResult(
                errorMessage = R.string.sign_up_neighborhood_error,
            )
        }

        return RegisterValidatorResult(
            isSuccess = true,
        )
    }
}