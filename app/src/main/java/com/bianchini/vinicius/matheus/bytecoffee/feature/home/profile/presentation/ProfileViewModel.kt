package com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.presentation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.EditAccountForm
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.Profile
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.address.ProfileLocalAddressDataSource
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.profile.ProfileLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileLocalDataSource: ProfileLocalDataSource,
    private val profileAddressDataSource: ProfileLocalAddressDataSource
) : ViewModel() {

    private val _editProfileForm = MutableStateFlow(EditAccountForm())
    val form = _editProfileForm.asStateFlow()

    private var _userProfile: Profile? = null

    private val _shouldRedirectToAuth = MutableStateFlow(false)
    val shouldRedirectToAuth = _shouldRedirectToAuth.asStateFlow()

    private var _profilePhoto: MutableStateFlow<String?> = MutableStateFlow("")
    val profilePhoto = _profilePhoto.asStateFlow()

    init {
        viewModelScope.launch {
            _userProfile = profileLocalDataSource.getProfile().getOrNull()

            _profilePhoto.value = _userProfile?.profileImage
        }
    }


    fun logout() {
        viewModelScope.launch {
            profileLocalDataSource.deleteProfile(_userProfile?.id ?: "")
            profileAddressDataSource.deleteAllAddress()

            _shouldRedirectToAuth.value = true
        }
    }

    fun saveProfilePhoto(uri: Uri) {
        viewModelScope.launch {
            profileLocalDataSource.updateProfilePhoto(_userProfile?.id!!, uri.toString())
        }
    }
}