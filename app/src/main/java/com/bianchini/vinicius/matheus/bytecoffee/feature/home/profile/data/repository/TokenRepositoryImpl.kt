package com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.data.repository

import android.util.Log
import com.bianchini.vinicius.matheus.bytecoffee.db.token.dao.TokenDao
import com.bianchini.vinicius.matheus.bytecoffee.db.token.entity.TokenEntity
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.profile.TokenLocalDataSource
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenDao: TokenDao
) : TokenLocalDataSource {
    override suspend fun findToken(): String {
        return tokenDao.findToken()?.token ?: ""
    }

    override suspend fun saveToken(token: String) {
        runCatching {
            tokenDao.insertToken(TokenEntity(token = token))
        }.onFailure {
            Log.e("TokenRepositoryImpl", "saveToken: ", it)
        }
    }

    override suspend fun deleteAllToken() {
        runCatching {
            tokenDao.deleteAllToken()
        }.onFailure {
            Log.e("TokenRepositoryImpl", "deleteAllToken: ", it)
        }
    }
}