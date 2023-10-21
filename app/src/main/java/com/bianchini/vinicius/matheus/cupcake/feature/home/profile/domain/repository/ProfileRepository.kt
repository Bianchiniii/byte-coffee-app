package com.bianchini.vinicius.matheus.cupcake.feature.home.profile.domain.repository

import com.bianchini.vinicius.matheus.cupcake.feature.home.profile.domain.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun getProfile(): Flow<Profile>

    suspend fun saveProfile(profile: Profile)

    suspend fun deleteProfile(profile: Profile)
}