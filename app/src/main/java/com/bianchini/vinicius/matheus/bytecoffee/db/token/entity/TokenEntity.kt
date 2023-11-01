package com.bianchini.vinicius.matheus.bytecoffee.db.token.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bianchini.vinicius.matheus.bytecoffee.db.token.util.DbTokenConstants

@Entity(tableName = DbTokenConstants.TABLE_NAME)
data class TokenEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val token: String
)

fun TokenEntity.toDomain() = token
