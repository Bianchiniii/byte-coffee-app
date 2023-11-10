package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.usecase

data class RegisterUseCase (
    val validateNameRegisterUseCase: ValidateNameRegisterUseCase,
    val validateLastNameRegisterUseCase: ValidateLastNameRegisterUseCase,
    val validateEmailRegisterUseCase: ValidateEmailRegisterUseCase,
    val validatePasswordRegisterUseCase: ValidatePasswordRegisterUseCase,
    val validateStreetRegisterUseCase: ValidateStreetRegisterUseCase,
    val validateNumberRegisterUseCase: ValidateNumberRegisterUseCase,
    val validateNeighborhoodRegisterUseCase: ValidateNeighborhoodRegisterUseCase,
    val validateCityAndStateRegisterUseCase: ValidateCityAndStateRegisterUseCase,
)