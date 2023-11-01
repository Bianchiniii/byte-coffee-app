package com.bianchini.vinicius.matheus.bytecoffee.db.address.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bianchini.vinicius.matheus.bytecoffee.db.address.util.DbAddressConstants
import com.bianchini.vinicius.matheus.bytecoffee.db.address.util.DbAddressConstants.COLUMN_CITY_AND_STATE
import com.bianchini.vinicius.matheus.bytecoffee.db.address.util.DbAddressConstants.COLUMN_ID
import com.bianchini.vinicius.matheus.bytecoffee.db.address.util.DbAddressConstants.COLUMN_NEIGHBORHOOD
import com.bianchini.vinicius.matheus.bytecoffee.db.address.util.DbAddressConstants.COLUMN_NUMBER
import com.bianchini.vinicius.matheus.bytecoffee.db.address.util.DbAddressConstants.COLUMN_STREET
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.Address

@Entity(tableName = DbAddressConstants.ADDRESS_TABLE_NAME)
data class AddressEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: String,
    @ColumnInfo(name = COLUMN_STREET)
    val street: String,
    @ColumnInfo(name = COLUMN_NUMBER)
    val number: String,
    @ColumnInfo(name = COLUMN_NEIGHBORHOOD)
    val neighborhood: String,
    @ColumnInfo(name = COLUMN_CITY_AND_STATE)
    val cityAndState: String,
)

fun AddressEntity.toDomain() = Address(
    id = id,
    street = street,
    number = number,
    neighborhood = neighborhood,
    cityAndState = cityAndState,
)
