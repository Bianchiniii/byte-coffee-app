package com.bianchini.vinicius.matheus.cupcake.di

import android.content.Context
import androidx.room.Room
import com.bianchini.vinicius.matheus.cupcake.db.AppDatabase
import com.bianchini.vinicius.matheus.cupcake.db.util.dbconstants.DbConstants.APP_DATABASE_NAME
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
    ).build()
}