package com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.address

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.Address

interface ProfileLocalAddressDataSource {

    suspend fun findAddress(): Result<Address?>

    suspend fun saveAddress(address: Address)

    suspend fun deleteAllAddress()

}