package com.bianchini.vinicius.matheus.cupcake.db.enity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bianchini.vinicius.matheus.cupcake.db.util.dbconstants.DbConstants

@Entity(tableName = DbConstants.PROFILE_TABLE_NAME)
data class ProfileEntity(
    @PrimaryKey
    @ColumnInfo(name = DbConstants.COLUMN_ID)
    val id: Int,
    @ColumnInfo(name = DbConstants.COLUMN_NAME)
    val name: String,
    @ColumnInfo(name = DbConstants.COLUMN_LAST_NAME)
    val lastName: String,
    @ColumnInfo(name = DbConstants.COLUMN_EMAIL)
    val email: String,
    @ColumnInfo(name = DbConstants.COLUMN_PROFILE_IMAGE)
    val profileImage: String
)