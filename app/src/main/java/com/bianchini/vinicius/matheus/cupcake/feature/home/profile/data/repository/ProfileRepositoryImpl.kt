package com.bianchini.vinicius.matheus.cupcake.feature.home.profile.data.repository

import com.bianchini.vinicius.matheus.cupcake.db.profile.dao.ProfileDao
import com.bianchini.vinicius.matheus.cupcake.db.profile.enity.toDomain
import com.bianchini.vinicius.matheus.cupcake.feature.home.profile.domain.Profile
import com.bianchini.vinicius.matheus.cupcake.feature.home.profile.domain.repository.ProfileLocalDataSource
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDao: ProfileDao
) : ProfileLocalDataSource {
    override suspend fun getProfile(): Result<Profile?> {
        return runCatching {
            val storageRequest = profileDao.loadProfile()

            storageRequest?.toDomain()
        }.onFailure {
            null
        }
    }

    override suspend fun saveProfile(profile: Profile) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProfile(profile: Profile) {
        TODO("Not yet implemented")
    }
}