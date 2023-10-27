package com.bianchini.vinicius.matheus.bytecoffee.db.profile.enity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bianchini.vinicius.matheus.bytecoffee.db.profile.util.DbProfileConstants.COLUMN_EMAIL
import com.bianchini.vinicius.matheus.bytecoffee.db.profile.util.DbProfileConstants.COLUMN_ID
import com.bianchini.vinicius.matheus.bytecoffee.db.profile.util.DbProfileConstants.COLUMN_LAST_NAME
import com.bianchini.vinicius.matheus.bytecoffee.db.profile.util.DbProfileConstants.COLUMN_NAME
import com.bianchini.vinicius.matheus.bytecoffee.db.profile.util.DbProfileConstants.COLUMN_PROFILE_IMAGE
import com.bianchini.vinicius.matheus.bytecoffee.db.profile.util.DbProfileConstants.PROFILE_TABLE_NAME
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.Profile

@Entity(tableName = PROFILE_TABLE_NAME)
data class ProfileEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: String,
    @ColumnInfo(name = COLUMN_NAME)
    val name: String,
    @ColumnInfo(name = COLUMN_LAST_NAME)
    val lastName: String,
    @ColumnInfo(name = COLUMN_EMAIL)
    val email: String,
    @ColumnInfo(name = COLUMN_PROFILE_IMAGE)
    val profileImage: String
)

fun ProfileEntity.toDomain() = Profile(
    id = id,
    name = name,
    lastName = lastName,
    email = email,
    profileImage = profileImage
)