package com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.profile

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.EditProfileForm
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.Profile

interface ProfileRemoteDataSource {

    suspend fun updateProfile(profile: EditProfileForm): Resource.Result<Profile, Throwable>
}