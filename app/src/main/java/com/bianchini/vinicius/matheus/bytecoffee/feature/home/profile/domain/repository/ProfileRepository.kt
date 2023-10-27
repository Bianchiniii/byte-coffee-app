package com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun getProfile(): Flow<Profile>

    suspend fun saveProfile(profile: Profile)

    suspend fun deleteProfile(profile: Profile)
}