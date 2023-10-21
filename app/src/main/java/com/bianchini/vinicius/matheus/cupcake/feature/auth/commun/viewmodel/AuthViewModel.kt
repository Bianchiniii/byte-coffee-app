package com.bianchini.vinicius.matheus.cupcake.feature.auth.commun.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bianchini.vinicius.matheus.cupcake.feature.auth.signup.domain.model.NewAccount
import com.bianchini.vinicius.matheus.cupcake.feature.auth.signup.domain.repository.AuthRegisterRepository
import com.bianchini.vinicius.matheus.cupcake.feature.home.profile.data.repository.ProfileRepositoryImpl
import com.bianchini.vinicius.matheus.cupcake.feature.home.profile.domain.repository.ProfileLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _registerNameValue = mutableStateOf("")
    private val _registerSurnameValue = mutableStateOf("")
    private val _registerEmailValue = mutableStateOf("")
    private val _registerPasswordValue = mutableStateOf("")

    private val _isLoggedProfile = MutableStateFlow(false)
    val isLoggedProfile = _isLoggedProfile.asStateFlow()
    fun updateRegisterNameValue(name: String) {
        _registerNameValue.value = name
    }

    fun updateRegisterSurnameValue(surname: String) {
        _registerSurnameValue.value = surname
    }

    fun updateRegisterEmailValue(email: String) {
        _registerEmailValue.value = email
    }

    fun updateRegisterPasswordValue(password: String) {
        _registerPasswordValue.value = password
    }

    private fun validateRegisterStringFields(): Boolean {
        return _registerNameValue.value.isNotEmpty() && _registerSurnameValue.value.isNotEmpty()
                && _registerEmailValue.value.isNotEmpty() && _registerPasswordValue.value.isNotEmpty()
    }

    fun registerAccount() {
        val areFieldsValid = validateRegisterStringFields()

        _uiStateError.value = !areFieldsValid

        viewModelScope.launch {
            /*val request = authRegisterRepository.register(
                NewAccount(
                    name = _registerNameValue.value,
                    surname = _registerSurnameValue.value,
                    email = _registerEmailValue.value,
                    password = _registerPasswordValue.value
                )
            )*/

            // TODO: emitir redirect para tela de login
        }
    }

    fun verifyIsLoggedProfile() {
        viewModelScope.launch {
            val request = profileRepositoryDataSource.getProfile()

            _isLoggedProfile.value = request.getOrNull() != null
        }
    }
}