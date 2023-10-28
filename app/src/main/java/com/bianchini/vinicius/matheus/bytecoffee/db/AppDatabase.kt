package com.bianchini.vinicius.matheus.bytecoffee.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bianchini.vinicius.matheus.bytecoffee.db.address.dao.AddressDao
import com.bianchini.vinicius.matheus.bytecoffee.db.address.entity.AddressEntity
import com.bianchini.vinicius.matheus.bytecoffee.db.profile.dao.ProfileDao
import com.bianchini.vinicius.matheus.bytecoffee.db.profile.enity.ProfileEntity

@Database(
    entities = [ProfileEntity::class, AddressEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun profileDao(): ProfileDao

    abstract fun addressDao(): AddressDao
}