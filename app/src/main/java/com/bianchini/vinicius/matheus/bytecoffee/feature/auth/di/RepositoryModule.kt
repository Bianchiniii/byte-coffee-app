package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.di

import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.data.repository.AuthRegisterRepositoryImpl
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.repository.AuthRegisterRepository
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.data.repository.AisleRepositoryImpl
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.repository.AisleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRegisterRepository(
        authRegisterRepositoryImpl: AuthRegisterRepositoryImpl
    ): AuthRegisterRepository

    @Binds
    abstract fun bindAisleRepository(
        aisleRepositoryImpl: AisleRepositoryImpl
    ): AisleRepository
}