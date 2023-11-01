package com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.profile

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.Profile

interface ProfileLocalDataSource {

    suspend fun getProfile(): Result<Profile?>

    suspend fun saveProfile(profile: Profile)

    suspend fun deleteProfile(profileId: String)

    suspend fun updateProfilePhoto(profileId: String, profilePhoto: String)
}