package com.bianchini.vinicius.matheus.bytecoffee.db.token.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bianchini.vinicius.matheus.bytecoffee.db.token.entity.TokenEntity
import com.bianchini.vinicius.matheus.bytecoffee.db.token.util.DbTokenConstants

@Dao
interface TokenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToken(tokenEntity: TokenEntity)

    @Query("SELECT * FROM ${DbTokenConstants.TABLE_NAME}")
    suspend fun findToken(): TokenEntity?

    @Query("DELETE FROM ${DbTokenConstants.TABLE_NAME}")
    suspend fun deleteAllToken()
}