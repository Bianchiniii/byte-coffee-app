package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.commun.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.model.LoginRequest
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.model.NewAccount
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.repository.AuthRegisterRepository
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.profile.ProfileLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import extension.getOrNull
import extension.ifFailure
import extension.ifSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRegisterRepository: AuthRegisterRepository,
    private val profileRepositoryDataSource: ProfileLocalDataSource
) : ViewModel() {

    private val _uiStateError = MutableStateFlow(false)
    val uiStateError = _uiStateError.asStateFlow()

    private val _successCreateAccount = MutableStateFlow(false)
    val successCreateAccount = _successCreateAccount.asStateFlow()

    private val _isLoggingSuccessful = MutableStateFlow(false)
    val isLoggingSuccessful = _isLoggingSuccessful.asStateFlow()

    private val _isLoggedProfile = MutableStateFlow(false)
    val isLoggedProfile = _isLoggedProfile.asStateFlow()

    private val _profileInfo = MutableStateFlow(
        NewAccount.ProfileInfo(
            name = "",
            surname = "",
            email = "",
            password = "",
            role = DEFAULT_ROLE
        )
    )

    private val _profileAddress = MutableStateFlow(
        NewAccount.Address(
            street = "",
            number = "",
            neighborhood = "",
            cityAndState = ""
        )
    )

    init {
        viewModelScope.launch {
            val request = profileRepositoryDataSource.getProfile()

            _isLoggedProfile.value = request.getOrNull() != null
        }
    }

    fun registerAccount() {
        viewModelScope.launch {
            val newAccount = NewAccount(
                profileInfo = _profileInfo.value,
                address = _profileAddress.value
            )

            val request = authRegisterRepository.register(newAccount)

            _successCreateAccount.value = request.getOrNull() ?: false
        }
    }

    fun updateProfileInfoName(name: String) {
        _profileInfo.update {
            it.copy(
                name = name
            )
        }
    }

    fun updateProfileInfoSurname(surname: String) {
        _profileInfo.update {
            it.copy(surname = surname)
        }
    }

    fun updateProfileInfoEmail(email: String) {
        _profileInfo.update {
            it.copy(email = email)
        }
    }

    fun updateProfileInfoPassword(password: String) {
        _profileInfo.update {
            it.copy(password = password)
        }
    }

    fun updateProfileAddressStreet(street: String) {
        _profileAddress.update {
            it.copy(street = street)
        }
    }

    fun updateProfileAddressNumber(number: String) {
        _profileAddress.update {
            it.copy(number = number)
        }
    }

    fun updateProfileAddressNeighborhood(neighborhood: String) {
        _profileAddress.update {
            it.copy(neighborhood = neighborhood)
        }
    }

    fun updateProfileAddressCityAndState(cityAndState: String) {
        _profileAddress.update {
            it.copy(cityAndState = cityAndState)
        }
    }

    fun login(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            val request = authRegisterRepository.login(
                LoginRequest(
                    login = email,
                    password = password
                )
            )
            request.ifSuccess {
                _isLoggingSuccessful.value = true
            }.ifFailure {
                _isLoggingSuccessful.value = false
            }
        }
    }

    companion object {
        private const val DEFAULT_ROLE = "user"
    }
}
