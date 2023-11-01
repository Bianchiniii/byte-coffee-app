package com.bianchini.vinicius.matheus.bytecoffee.feature.home.di

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.data.repository.ProfileAddressRepositoryImpl
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.data.repository.ProfileRepositoryImpl
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.data.repository.TokenRepositoryImpl
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.address.ProfileLocalAddressDataSource
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.profile.ProfileLocalDataSource
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.profile.TokenLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsProfileLocalDataSource(
        profileLocalDataSourceImpl: ProfileRepositoryImpl
    ): ProfileLocalDataSource

    @Binds
    abstract fun bindsProfileAddressLocalDataSource(
        profileAddressRepositoryImpl: ProfileAddressRepositoryImpl
    ): ProfileLocalAddressDataSource

    @Binds
    abstract fun bindsTokenLocalDataSource(
        tokenRepositoryImpl: TokenRepositoryImpl
    ): TokenLocalDataSource
}