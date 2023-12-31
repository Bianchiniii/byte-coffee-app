package com.bianchini.vinicius.matheus.bytecoffee.feature.home.di

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.data.repository.OrdersRepositoryImpl
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.domain.repository.OrdersRepository
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.data.repository.ProfileAddressRepositoryImpl
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.data.repository.ProfileRemoteRepositoryImpl
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.data.repository.ProfileRepositoryImpl
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.data.repository.TokenRepositoryImpl
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.address.ProfileLocalAddressDataSource
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.profile.ProfileLocalDataSource
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.profile.ProfileRemoteDataSource
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.profile.TokenLocalDataSource
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.data.repository.TicketRepositoryImpl
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.domain.repository.TicketRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsProfileLocalDataSource(
        profileLocalDataSourceImpl: ProfileRepositoryImpl
    ): ProfileLocalDataSource

    @Binds
    @Singleton
    abstract fun bindsProfileAddressLocalDataSource(
        profileAddressRepositoryImpl: ProfileAddressRepositoryImpl
    ): ProfileLocalAddressDataSource

    @Binds
    abstract fun bindsTokenLocalDataSource(
        tokenRepositoryImpl: TokenRepositoryImpl
    ): TokenLocalDataSource

    @Binds
    abstract fun bindsTicketRepository(
        ticketRepositoryImpl: TicketRepositoryImpl
    ): TicketRepository

    @Binds
    abstract fun bindsOrdersRepository(
        ordersRepositoryImpl: OrdersRepositoryImpl
    ): OrdersRepository

    @Binds
    abstract fun bindsProfileRemoteDataSource(
        profileRemoteRepositoryImpl: ProfileRemoteRepositoryImpl
    ): ProfileRemoteDataSource
}