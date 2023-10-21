package com.bianchini.vinicius.matheus.cupcake.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bianchini.vinicius.matheus.cupcake.db.profile.dao.ProfileDao
import com.bianchini.vinicius.matheus.cupcake.db.profile.enity.ProfileEntity

@Database(entities = [ProfileEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun profileDao(): ProfileDao
}