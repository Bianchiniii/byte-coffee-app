package com.bianchini.vinicius.matheus.bytecoffee.di

import com.bianchini.vinicius.matheus.bytecoffee.services.ByteCoffeeService
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
    fun providesByteCoffeeService(retrofit: Retrofit): ByteCoffeeService {
        return retrofit.create(ByteCoffeeService::class.java)
    }
}