package com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.profile

interface TokenLocalDataSource {

    suspend fun findToken(): String

    suspend fun saveToken(token: String)

    suspend fun deleteAllToken()
}