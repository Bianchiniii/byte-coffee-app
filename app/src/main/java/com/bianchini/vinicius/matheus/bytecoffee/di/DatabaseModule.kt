package com.bianchini.vinicius.matheus.bytecoffee.di

import android.content.Context
import androidx.room.Room
import com.bianchini.vinicius.matheus.bytecoffee.db.AppDatabase
import com.bianchini.vinicius.matheus.bytecoffee.db.DbConstants.APP_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun providesAppDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        name = APP_DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun providesProfileDao(
        appDatabase: AppDatabase
    ) = appDatabase.profileDao()

    @Provides
    fun providesAddressDao(
        appDatabase: AppDatabase
    ) = appDatabase.addressDao()

    @Provides
    fun providesTokenDao(
        appDatabase: AppDatabase
    ) = appDatabase.tokenDao()
}