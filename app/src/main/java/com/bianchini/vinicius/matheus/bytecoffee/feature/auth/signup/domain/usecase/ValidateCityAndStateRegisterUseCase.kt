package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.usecase

import com.bianchini.vinicius.matheus.bytecoffee.R

class ValidateCityAndStateRegisterUseCase {

    operator fun invoke(
        cityAndState: String,
    ): RegisterValidatorResult {
        if (cityAndState.isEmpty()) {
            return RegisterValidatorResult(
                errorMessage = R.string.sign_up_city_and_state_error,
            )
        }

        return RegisterValidatorResult(
            isSuccess = true,
        )
    }
}