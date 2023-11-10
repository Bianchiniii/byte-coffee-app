package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.repository

import Resource
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.model.LoginCredential
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.model.NewAccountForm

interface AuthRegisterRepository {

    suspend fun register(newAccountForm: NewAccountForm): Resource.Result<Boolean, Throwable>

    suspend fun login(loginCredential: LoginCredential): Resource.Result<Boolean, Throwable>
}