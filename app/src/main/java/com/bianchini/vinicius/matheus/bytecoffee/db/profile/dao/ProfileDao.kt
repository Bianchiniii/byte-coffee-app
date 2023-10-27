package com.bianchini.vinicius.matheus.bytecoffee.db.profile.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bianchini.vinicius.matheus.bytecoffee.db.profile.enity.ProfileEntity
import com.bianchini.vinicius.matheus.bytecoffee.db.profile.util.DbProfileConstants.COLUMN_PROFILE_IMAGE
import com.bianchini.vinicius.matheus.bytecoffee.db.profile.util.DbProfileConstants.PROFILE_TABLE_NAME

@Dao
interface ProfileDao {

    @Query("SELECT * FROM $PROFILE_TABLE_NAME")
    suspend fun loadProfile(): ProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: ProfileEntity)

    @Query("DELETE FROM $PROFILE_TABLE_NAME WHERE id = :profileId")
    suspend fun deleteProfile(profileId: String)

    @Query("UPDATE $PROFILE_TABLE_NAME SET $COLUMN_PROFILE_IMAGE = :profilePhoto WHERE id = :profileId")
    suspend fun updateProfilePhoto(profileId: String, profilePhoto: String)
}