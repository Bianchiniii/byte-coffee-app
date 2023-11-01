package com.bianchini.vinicius.matheus.bytecoffee.di

import com.bianchini.vinicius.matheus.bytecoffee.services.AisleService
import com.bianchini.vinicius.matheus.bytecoffee.services.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitServiceModule {
    @Provides
    @Singleton
    fun providesAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun providesAisleService(retrofit: Retrofit): AisleService {
        return retrofit.create(AisleService::class.java)
    }
}