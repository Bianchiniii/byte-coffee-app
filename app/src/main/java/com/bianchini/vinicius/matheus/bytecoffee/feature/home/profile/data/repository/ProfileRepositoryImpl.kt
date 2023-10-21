package com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.data.repository

import android.util.Log
import com.bianchini.vinicius.matheus.bytecoffee.db.profile.dao.ProfileDao
import com.bianchini.vinicius.matheus.bytecoffee.db.profile.enity.toDomain
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.Profile
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.ProfileLocalDataSource
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.toEntity
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDao: ProfileDao
) : ProfileLocalDataSource {
    override suspend fun getProfile(): Result<Profile?> {
        return runCatching {
            val storageRequest = profileDao.loadProfile()

            storageRequest?.toDomain()
        }.onFailure {
            Log.e("ProfileRepositoryImpl", "getProfile: ", it)
        }
    }

    override suspend fun saveProfile(profile: Profile) {
        runCatching {
            profileDao.insertProfile(profile.toEntity())
        }.onFailure {
            Log.e("ProfileRepositoryImpl", "saveProfile: ", it)
        }.onSuccess {
            Log.d("ProfileRepositoryImpl", "saveProfile: Success")
        }
    }

    override suspend fun deleteProfile(profile: Profile) {
        TODO("Not yet implemented")
    }
}