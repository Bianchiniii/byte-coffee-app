package com.bianchini.vinicius.matheus.cupcake.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bianchini.vinicius.matheus.cupcake.db.enity.ProfileEntity
import com.bianchini.vinicius.matheus.cupcake.db.util.dbconstants.DbConstants.PROFILE_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

    @Query("SELECT * FROM $PROFILE_TABLE_NAME")
    suspend fun loadProfile(): Flow<ProfileEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: ProfileEntity)

    @Delete
    suspend fun deleteProfile(profile: ProfileEntity)
}