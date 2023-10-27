package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.data.repository

import Resource
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.data.response.toProfileToken
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.model.LoginRequest
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.model.NewAccount
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.repository.AuthRegisterRepository
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.data.response.toDomain
import com.bianchini.vinicius.matheus.bytecoffee.services.ByteCoffeeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRegisterRepositoryImpl @Inject constructor(
    private val service: ByteCoffeeService
) : AuthRegisterRepository {

    override suspend fun register(newAccount: NewAccount) = withContext(Dispatchers.IO) {
        val request = async {
            service.register(newAccount).execute()
        }.await()

        if (request.isSuccessful) {
            Resource.Result.Success(request.body().toDomain())
        } else {
            Resource.Result.Failure(Throwable("Failed to create new account!"))
        }
    }

    override suspend fun login(loginRequest: LoginRequest) = withContext(Dispatchers.IO) {
        val request = async {
            service.login(loginRequest).execute()
        }.await()

        if (request.isSuccessful) {
            Resource.Result.Success(request.body()?.toProfileToken())
        } else {
            Resource.Result.Failure(Throwable("Failed to login!"))
        }
    }
}