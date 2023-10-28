package com.bianchini.vinicius.matheus.bytecoffee.db.address.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bianchini.vinicius.matheus.bytecoffee.db.address.entity.AddressEntity
import com.bianchini.vinicius.matheus.bytecoffee.db.address.util.DbAddressConstants.ADDRESS_TABLE_NAME

@Dao
interface AddressDao {

    @Query("SELECT * FROM $ADDRESS_TABLE_NAME")
    suspend fun getAddress(): AddressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddress(address: AddressEntity)

    @Query("DELETE FROM $ADDRESS_TABLE_NAME WHERE id = :addressId")
    suspend fun deleteAddress(addressId: String)
}