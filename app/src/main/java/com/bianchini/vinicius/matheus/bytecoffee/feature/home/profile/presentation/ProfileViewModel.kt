package com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.presentation

import Resource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.EditProfileForm
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.Profile
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.address.ProfileLocalAddressDataSource
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.profile.ProfileLocalDataSource
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.profile.ProfileRemoteDataSource
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.presentation.event.OnFormValueChanged
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRemoteDataSource: ProfileRemoteDataSource,
    private val profileLocalDataSource: ProfileLocalDataSource,
    private val profileAddressDataSource: ProfileLocalAddressDataSource
) : ViewModel() {

    private val _editProfileForm = MutableStateFlow(EditProfileForm())
    val form = _editProfileForm.asStateFlow()

    private var _userProfile: Profile? = null

    private val _uiNavigationEvent = Channel<UiNavigationEvent>()
    val uiNavigationEvent = _uiNavigationEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            _userProfile = profileLocalDataSource.getProfile().getOrNull()
        }
    }

    fun onFormValueChanged(newEvent: OnFormValueChanged) {
        when (newEvent) {
            is OnFormValueChanged.OnEmailChanged -> {
                _editProfileForm.update {
                    it.copy(
                        email = newEvent.email
                    )
                }
            }

            is OnFormValueChanged.OnLastNameChanged -> {
                _editProfileForm.update {
                    it.copy(
                        surname = newEvent.lastName
                    )
                }
            }

            is OnFormValueChanged.OnNameChanged -> {
                _editProfileForm.update {
                    it.copy(
                        name = newEvent.name
                    )
                }
            }

            is OnFormValueChanged.OnPasswordChanged -> {
                _editProfileForm.update {
                    it.copy(
                        password = newEvent.password
                    )
                }
            }
        }
    }

    fun updateProfile() {
        viewModelScope.launch {
            _editProfileForm.update {
                it.copy(
                    id = _userProfile!!.id
                )
            }

            val form = _editProfileForm.value

            val request = profileRemoteDataSource.updateProfile(form)

            if (request is Resource.Result.Success) {
                val profile = request.data

                profileLocalDataSource.saveProfile(profile)


                _uiNavigationEvent.send(UiNavigationEvent.NavigateToHome)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            profileLocalDataSource.deleteProfile(_userProfile?.id ?: "")
            profileAddressDataSource.deleteAllAddress()

            _uiNavigationEvent.send(UiNavigationEvent.NavigateToAuth)
        }
    }
}

sealed class UiNavigationEvent {
    object NavigateToHome : UiNavigationEvent()

    object NavigateToAuth : UiNavigationEvent()
}