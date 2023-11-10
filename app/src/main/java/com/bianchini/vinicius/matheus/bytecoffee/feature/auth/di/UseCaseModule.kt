package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.di

import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.usecase.LoginUseCase
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.usecase.ValidateEmailLoginUseCase
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.usecase.ValidatePasswordLoginUseCase
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
}