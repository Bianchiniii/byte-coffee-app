package com.bianchini.vinicius.matheus.cupcake.feature.auth.signup.data.repository

import com.bianchini.vinicius.matheus.cupcake.feature.auth.signup.domain.model.NewAccount
import com.bianchini.vinicius.matheus.cupcake.feature.auth.signup.domain.repository.AuthRegisterRepository
import com.bianchini.vinicius.matheus.cupcake.feature.home.profile.domain.Profile
import com.bianchini.vinicius.matheus.cupcake.services.ByteCoffeeService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class AuthRegisterRepositoryImpl @Inject constructor(
    private val service : ByteCoffeeService
) : AuthRegisterRepository {

    /*override suspend fun register(newAccount: NewAccount): Flow<Profile> {
        TODO("Not yet implemented")
    }*/
}