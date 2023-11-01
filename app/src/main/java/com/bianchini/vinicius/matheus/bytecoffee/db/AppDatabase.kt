package com.bianchini.vinicius.matheus.bytecoffee.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bianchini.vinicius.matheus.bytecoffee.db.address.dao.AddressDao
import com.bianchini.vinicius.matheus.bytecoffee.db.address.entity.AddressEntity
import com.bianchini.vinicius.matheus.bytecoffee.db.profile.dao.ProfileDao
import com.bianchini.vinicius.matheus.bytecoffee.db.profile.enity.ProfileEntity
import com.bianchini.vinicius.matheus.bytecoffee.db.token.dao.TokenDao
import com.bianchini.vinicius.matheus.bytecoffee.db.token.entity.TokenEntity

@Database(
    entities = [ProfileEntity::class, AddressEntity::class, TokenEntity::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun profileDao(): ProfileDao

    abstract fun addressDao(): AddressDao

    abstract fun tokenDao(): TokenDao
}