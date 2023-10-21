package com.bianchini.vinicius.matheus.cupcake.db.profile.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bianchini.vinicius.matheus.cupcake.db.profile.enity.ProfileEntity
import com.bianchini.vinicius.matheus.cupcake.db.profile.util.DbProfileConstants.PROFILE_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

    @Query("SELECT * FROM $PROFILE_TABLE_NAME")
    suspend fun loadProfile(): ProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: ProfileEntity)

    @Delete
    suspend fun deleteProfile(profile: ProfileEntity)
}