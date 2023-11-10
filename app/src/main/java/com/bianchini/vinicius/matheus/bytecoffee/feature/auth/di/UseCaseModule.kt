package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.di

import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.usecase.LoginUseCase
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.usecase.ValidateEmailLoginUseCase
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.usecase.ValidatePasswordLoginUseCase
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.usecase.RegisterUseCase
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.usecase.ValidateCityAndStateRegisterUseCase
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.usecase.ValidateEmailRegisterUseCase
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.usecase.ValidateLastNameRegisterUseCase
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.usecase.ValidateNameRegisterUseCase
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.usecase.ValidateNeighborhoodRegisterUseCase
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.usecase.ValidateNumberRegisterUseCase
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.usecase.ValidatePasswordRegisterUseCase
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.usecase.ValidateStreetRegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun providesLoginUseCase(
        validateEmailLoginUseCase: ValidateEmailLoginUseCase,
        validatePasswordLoginUseCase: ValidatePasswordLoginUseCase
    ): LoginUseCase {
        return LoginUseCase(
            validateEmailLoginUseCase = validateEmailLoginUseCase,
            validatePasswordLoginUseCase = validatePasswordLoginUseCase
        )
    }

    @Provides
    fun providesValidatePasswordLoginUseCase(): ValidatePasswordLoginUseCase {
        return ValidatePasswordLoginUseCase()
    }

    @Provides
    fun providesValidateEmailLoginUseCase(): ValidateEmailLoginUseCase {
        return ValidateEmailLoginUseCase()
    }

    @Provides
    fun providesRegisterUseCase(
        validateNameRegisterUseCase: ValidateNameRegisterUseCase,
        validateLastNameRegisterUseCase: ValidateLastNameRegisterUseCase,
        validateEmailRegisterUseCase: ValidateEmailRegisterUseCase,
        validatePasswordRegisterUseCase: ValidatePasswordRegisterUseCase,
        validateStreetRegisterUseCase: ValidateStreetRegisterUseCase,
        validateNumberRegisterUseCase: ValidateNumberRegisterUseCase,
        validateNeighborhoodRegisterUseCase: ValidateNeighborhoodRegisterUseCase,
        validateCityAndStateRegisterUseCase: ValidateCityAndStateRegisterUseCase,
    ): RegisterUseCase {
        return RegisterUseCase(
            validateNameRegisterUseCase,
            validateLastNameRegisterUseCase,
            validateEmailRegisterUseCase,
            validatePasswordRegisterUseCase,
            validateStreetRegisterUseCase,
            validateNumberRegisterUseCase,
            validateNeighborhoodRegisterUseCase,
            validateCityAndStateRegisterUseCase
        )
    }

    @Provides
    fun providesValidateNameRegisterUseCase(): ValidateNameRegisterUseCase {
        return ValidateNameRegisterUseCase()
    }

    @Provides
    fun providesValidateLastNameRegisterUseCase(): ValidateLastNameRegisterUseCase {
        return ValidateLastNameRegisterUseCase()
    }

    @Provides
    fun providesValidateEmailRegisterUseCase(): ValidateEmailRegisterUseCase {
        return ValidateEmailRegisterUseCase()
    }

    @Provides
    fun providesValidatePasswordRegisterUseCase(): ValidatePasswordRegisterUseCase {
        return ValidatePasswordRegisterUseCase()
    }

    @Provides
    fun providesValidateStreetRegisterUseCase(): ValidateStreetRegisterUseCase {
        return ValidateStreetRegisterUseCase()
    }

    @Provides
    fun providesValidateNumberRegisterUseCase(): ValidateNumberRegisterUseCase {
        return ValidateNumberRegisterUseCase()
    }

    @Provides
    fun providesValidateNeighborhoodRegisterUseCase(): ValidateNeighborhoodRegisterUseCase {
        return ValidateNeighborhoodRegisterUseCase()
    }

    @Provides
    fun providesValidateCityAndStateRegisterUseCase(): ValidateCityAndStateRegisterUseCase {
        return ValidateCityAndStateRegisterUseCase()
    }
}