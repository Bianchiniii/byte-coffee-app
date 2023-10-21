package com.bianchini.vinicius.matheus.cupcake.feature.home.di

import com.bianchini.vinicius.matheus.cupcake.feature.home.profile.data.repository.ProfileRepositoryImpl
import com.bianchini.vinicius.matheus.cupcake.feature.home.profile.domain.repository.ProfileLocalDataSource
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
}