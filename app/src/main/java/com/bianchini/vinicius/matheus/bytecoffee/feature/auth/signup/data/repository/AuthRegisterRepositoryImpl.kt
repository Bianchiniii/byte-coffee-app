package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.data.repository

import Resource
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.data.response.toProfileToken
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.model.LoginRequest
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.model.NewAccount
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.repository.AuthRegisterRepository
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.data.response.toDomain
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.toEntity
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.address.ProfileLocalAddressDataSource
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.profile.ProfileLocalDataSource
import com.bianchini.vinicius.matheus.bytecoffee.services.AuthService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRegisterRepositoryImpl @Inject constructor(
    private val service: AuthService,
    private val profileRepositoryDataSource: ProfileLocalDataSource,
    private val profileLocalAddressDataSource: ProfileLocalAddressDataSource,
) : AuthRegisterRepository {

    override suspend fun register(newAccount: NewAccount) = withContext(Dispatchers.IO) {
        val request = async {
            service.register(newAccount).execute()
        }.await()

        if (request.isSuccessful) {
            val profileInfo = request.body()?.profileInfo?.toDomain()!!
            val profileAddress = request.body()?.address?.toDomain()!!

            profileRepositoryDataSource.saveProfile(profileInfo)
            profileLocalAddressDataSource.saveAddress(profileAddress)

            Resource.Result.Success(true)
        } else {
            Resource.Result.Failure(Throwable("Failed to create new account!"))
        }
    }

    override suspend fun login(loginRequest: LoginRequest) = withContext(Dispatchers.IO) {
        val request = async {
            service.login(loginRequest).execute()
        }.await()

        if (request.isSuccessful) {
            val profileInfo = request.body()?.profile?.toDomain()!!
            val address = request.body()?.address?.toDomain()!!

            profileRepositoryDataSource.saveProfile(profileInfo)
            profileLocalAddressDataSource.saveAddress(address)

            Resource.Result.Success(true)
        } else {
            Resource.Result.Failure(Throwable("Failed to login!"))
        }
    }
}