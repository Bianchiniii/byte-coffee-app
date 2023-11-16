package com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.data.repository

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.data.response.toProfile
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.EditProfileForm
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.profile.ProfileRemoteDataSource
import com.bianchini.vinicius.matheus.bytecoffee.services.AuthService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileRemoteRepositoryImpl @Inject constructor(
    private val service: AuthService,
) : ProfileRemoteDataSource {

    override suspend fun updateProfile(profile: EditProfileForm) = withContext(Dispatchers.IO) {
        val request = service.updateProfile(profile).execute()

        return@withContext if (request.isSuccessful) {
            Resource.Result.Success(request.body().profileInfo.toProfile())
        } else {
            Resource.Result.Failure(Throwable("Error updating profile"))
        }
    }
}