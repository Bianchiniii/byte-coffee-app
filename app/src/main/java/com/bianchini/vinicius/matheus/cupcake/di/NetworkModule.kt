package com.bianchini.vinicius.matheus.cupcake.di

import com.bianchini.vinicius.matheus.cupcake.BuildConfig
import com.bianchini.vinicius.matheus.cupcake.services.ByteCoffeeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val TIMEOUT_SECONDS = 15L

    //Mostra as requisições realizadas no logcat em modo degub
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else HttpLoggingInterceptor.Level.NONE
            )
        }
    }

    /*
        @Provides
        fun providesAuthorizationInterceptor(): AuthorizationInterceptor {
            return AuthorizationInterceptor(
                BuildConfig.PUBLIC_KEY,
                BuildConfig.PRIVATE_KEY,
                Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            )
        }
    */

    @Provides
    fun providesOkHttpCliente(
        loggingInterceptor: HttpLoggingInterceptor,
//        authorizationInterceptor: AuthorizationInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
//            .addInterceptor(authorizationInterceptor)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory,
    ): ByteCoffeeService {
        return Retrofit.Builder()
            .baseUrl("http://localhost:8080/")
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
            .create(ByteCoffeeService::class.java)
    }
}