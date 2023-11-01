package com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.data.repository

import android.util.Log
import com.bianchini.vinicius.matheus.bytecoffee.db.address.dao.AddressDao
import com.bianchini.vinicius.matheus.bytecoffee.db.address.entity.toDomain
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.Address
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.toEntity
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.address.ProfileLocalAddressDataSource
import javax.inject.Inject

class ProfileAddressRepositoryImpl @Inject constructor(
    private val addressDao: AddressDao
) : ProfileLocalAddressDataSource {
    override suspend fun findAddress(): Result<Address?> {
        return runCatching {
            val request = addressDao.getAddress()

            request?.toDomain()
        }
    }

    override suspend fun saveAddress(address: Address) {
        runCatching {
            addressDao.insertAddress(address.toEntity())
        }.onFailure {
            Log.e("ProfileAddressRepositoryImpl", "saveAddress: ", it)
        }.onSuccess {
            Log.d("ProfileAddressRepositoryImpl", "saveAddress: Success")
        }
    }

    override suspend fun deleteAllAddress() {
        runCatching {
            addressDao.deleteAllAddress()
        }.onFailure {
            Log.e("ProfileAddressRepositoryImpl", "deleteAddress: ", it)
        }.onSuccess {
            Log.d("ProfileAddressRepositoryImpl", "deleteAddress: Success")
        }
    }
}