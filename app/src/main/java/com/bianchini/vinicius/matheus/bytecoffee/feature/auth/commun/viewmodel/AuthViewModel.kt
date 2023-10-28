package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.commun.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.model.LoginRequest
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.model.NewAccount
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.repository.AuthRegisterRepository
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.ProfileLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import extension.ifFailure
import extension.ifSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    init {
        viewModelScope.launch {
            val request = profileRepositoryDataSource.getProfile()

            _isLoggedProfile.value = request.getOrNull() != null
        }
    }

    fun registerAccount(
        email: String,
        name: String,
        surname: String,
        password: String
    ) {
        viewModelScope.launch {
            val request = authRegisterRepository.register(
                NewAccount(
                    name = email,
                    surname = name,
                    email = surname,
                    password = password,
                    role = DEFAULT_ROLE
                )
            )

            request.ifSuccess {
                profileRepositoryDataSource.saveProfile(it)

                _successCreateAccount.value = true
            }.ifFailure {
                _uiStateError.value = true
            }
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
                profileRepositoryDataSource.saveProfile(it!!.profile)

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
