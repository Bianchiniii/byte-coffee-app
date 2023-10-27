package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.repository

import Resource
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.model.LoginRequest
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.model.ProfileToken
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.model.NewAccount
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.Profile

interface AuthRegisterRepository {

    suspend fun register(newAccount: NewAccount): Resource.Result<Profile, Throwable>

    suspend fun login(loginRequest: LoginRequest): Resource.Result<ProfileToken?, Throwable>
}