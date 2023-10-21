package com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.Profile

interface ProfileLocalDataSource {

    suspend fun getProfile(): Result<Profile?>

    suspend fun saveProfile(profile: Profile)

    suspend fun deleteProfile(profile: Profile)
}