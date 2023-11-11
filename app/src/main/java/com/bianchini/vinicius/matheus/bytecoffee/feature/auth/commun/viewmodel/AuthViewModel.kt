package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.commun.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.model.LoginCredential
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.model.LoginCredentialError
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.usecase.LoginUseCase
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.presentation.event.OnTextInputChangedEventLogin
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.model.NewAccountForm
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.model.NewAccountFormError
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.repository.AuthRegisterRepository
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.usecase.RegisterUseCase
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.presentation.event.OnTextInputChangedEventRegister
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.profile.ProfileLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import extension.getOrNull
import extension.ifFailure
import extension.ifSuccess
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRegisterRepository: AuthRegisterRepository,
    private val profileRepositoryDataSource: ProfileLocalDataSource,
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {

    private val _successCreateAccount = MutableStateFlow(false)
    val successCreateAccount = _successCreateAccount.asStateFlow()

    private val _isLoggingSuccessful = MutableStateFlow(false)
    val isLoggingSuccessful = _isLoggingSuccessful.asStateFlow()

    private val _isLoggedProfile = MutableStateFlow(false)
    val isLoggedProfile = _isLoggedProfile.asStateFlow()

    private val _form = MutableStateFlow(
        NewAccountForm(
            profileInfo = NewAccountForm.ProfileInfo(
                role = DEFAULT_ROLE
            ),
            address = NewAccountForm.Address()
        )
    )
    val form = _form.asStateFlow()

    private val _formError = MutableStateFlow(NewAccountFormError())
    val formError = _formError.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val isRegisterEnabled = _formError.mapLatest {
        isInputRegisterValid()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = false
    )

    private val _loginCredential = MutableStateFlow(LoginCredential())
    val loginCredential = _loginCredential.asStateFlow()

    private val _loginCredentialError = MutableStateFlow(LoginCredentialError())
    val loginCredentialError = _loginCredentialError.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val isLoginCredentialEnabled = _loginCredentialError.mapLatest {
        isLoginInputsValid()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = false
    )

    init {
        viewModelScope.launch {
            val request = profileRepositoryDataSource.getProfile()

            _isLoggedProfile.value = request.getOrNull() != null
        }
    }

    fun onTextInputChangedEventRegister(event: OnTextInputChangedEventRegister) {
        when (event) {
            is OnTextInputChangedEventRegister.OnNameChangedRegister -> {
                _form.update {
                    it.copy(
                        profileInfo = it.profileInfo.copy(name = event.name)
                    )
                }

                val nameUseCaseResult = registerUseCase.validateNameRegisterUseCase(event.name)

                _formError.update {
                    it.copy(
                        nameError = nameUseCaseResult.errorMessage
                    )
                }
            }

            is OnTextInputChangedEventRegister.OnLastNameChangedRegister -> {
                _form.update {
                    it.copy(
                        profileInfo = it.profileInfo.copy(surname = event.lastName)
                    )
                }

                val lastNameUseCaseResult =
                    registerUseCase.validateLastNameRegisterUseCase(event.lastName)

                _formError.update {
                    it.copy(
                        surnameError = lastNameUseCaseResult.errorMessage
                    )
                }
            }

            is OnTextInputChangedEventRegister.OnEmailChangedRegister -> {
                _form.update {
                    it.copy(
                        profileInfo = it.profileInfo.copy(email = event.email)
                    )
                }

                val emailUseCaseResult = registerUseCase.validateEmailRegisterUseCase(event.email)

                _formError.update {
                    it.copy(
                        emailError = emailUseCaseResult.errorMessage
                    )
                }
            }

            is OnTextInputChangedEventRegister.OnPasswordChangedRegister -> {
                _form.update {
                    it.copy(
                        profileInfo = it.profileInfo.copy(password = event.password)
                    )
                }

                val passwordUseCaseResult =
                    registerUseCase.validatePasswordRegisterUseCase(event.password)

                _formError.update {
                    it.copy(
                        passwordError = passwordUseCaseResult.errorMessage
                    )
                }
            }

            is OnTextInputChangedEventRegister.OnStreetChangedRegister -> {
                _form.update {
                    it.copy(
                        address = it.address.copy(street = event.street)
                    )
                }

                val streetUseCaseResult =
                    registerUseCase.validateStreetRegisterUseCase(event.street)

                _formError.update {
                    it.copy(
                        streetError = streetUseCaseResult.errorMessage
                    )
                }
            }

            is OnTextInputChangedEventRegister.OnNumberChangedRegister -> {
                _form.update {
                    it.copy(
                        address = it.address.copy(number = event.number)
                    )
                }

                val numberUseCaseResult =
                    registerUseCase.validateNumberRegisterUseCase(event.number)

                _formError.update {
                    it.copy(
                        numberError = numberUseCaseResult.errorMessage
                    )
                }
            }

            is OnTextInputChangedEventRegister.OnNeighborhoodChangedRegister -> {
                _form.update {
                    it.copy(
                        address = it.address.copy(neighborhood = event.neighborhood),
                    )
                }

                val neighborhoodUseCaseResult =
                    registerUseCase.validateNeighborhoodRegisterUseCase(event.neighborhood)

                _formError.update {
                    it.copy(
                        neighborhoodError = neighborhoodUseCaseResult.errorMessage
                    )
                }
            }


            is OnTextInputChangedEventRegister.OnCityAnStateChangedRegister -> {
                _form.update {
                    it.copy(
                        address = it.address.copy(
                            cityAndState = event.cityAndState
                        )
                    )
                }

                val cityAndStateUseCaseResult =
                    registerUseCase.validateCityAndStateRegisterUseCase(event.cityAndState)

                _formError.update {
                    it.copy(
                        cityAndStateError = cityAndStateUseCaseResult.errorMessage
                    )
                }
            }
        }
    }

    private fun isInputRegisterValid(): Boolean {
        val currentRegisterForm = _form.value

        val nameUseCaseResult =
            registerUseCase.validateNameRegisterUseCase(currentRegisterForm.profileInfo.name)
        val lastNameUseCaseResult =
            registerUseCase.validateLastNameRegisterUseCase(currentRegisterForm.profileInfo.surname)
        val emailUseCaseResult =
            registerUseCase.validateEmailRegisterUseCase(currentRegisterForm.profileInfo.email)
        val passwordUseCaseResult =
            registerUseCase.validatePasswordRegisterUseCase(currentRegisterForm.profileInfo.password)
        val streetUseCaseResult =
            registerUseCase.validateStreetRegisterUseCase(currentRegisterForm.address.street)
        val numberUseCaseResult =
            registerUseCase.validateNumberRegisterUseCase(currentRegisterForm.address.number)
        val neighborhoodUseCaseResult =
            registerUseCase.validateNeighborhoodRegisterUseCase(currentRegisterForm.address.neighborhood)
        val cityAndStateUseCaseResult =
            registerUseCase.validateCityAndStateRegisterUseCase(currentRegisterForm.address.cityAndState)

        val hasError = listOf(
            nameUseCaseResult,
            lastNameUseCaseResult,
            emailUseCaseResult,
            passwordUseCaseResult,
            streetUseCaseResult,
            numberUseCaseResult,
            neighborhoodUseCaseResult,
            cityAndStateUseCaseResult
        ).any {
            !it.isSuccess
        }

        return hasError.not()
    }


    fun registerAccount() {
        viewModelScope.launch {
            val request = authRegisterRepository.register(_form.value)

            _successCreateAccount.value = request.getOrNull() ?: false
        }
    }

    fun login() {
        viewModelScope.launch {
            val request = authRegisterRepository.login(_loginCredential.value)

            request.ifSuccess {
                _isLoggingSuccessful.value = true
            }.ifFailure {
                _isLoggingSuccessful.value = false
            }
        }
    }

    fun onTextInputChangedEventLogin(event: OnTextInputChangedEventLogin) {
        when (event) {
            is OnTextInputChangedEventLogin.OnEmailChangedLogin -> {
                _loginCredential.update {
                    it.copy(
                        login = event.email
                    )
                }

                val emailUseCaseResult = loginUseCase.validateEmailLoginUseCase(event.email)

                _loginCredentialError.update {
                    it.copy(
                        loginError = emailUseCaseResult.errorMessage
                    )
                }
            }

            is OnTextInputChangedEventLogin.OnPasswordChangedLogin -> {
                _loginCredential.update {
                    it.copy(
                        password = event.password
                    )
                }

                val passwordUseCaseResult =
                    loginUseCase.validatePasswordLoginUseCase(event.password)

                _loginCredentialError.update {
                    it.copy(
                        passwordError = passwordUseCaseResult.errorMessage
                    )
                }
            }
        }
    }

    private fun isLoginInputsValid(): Boolean {
        val currentLoginCredential = _loginCredential.value

        val passwordUseCaseResult =
            loginUseCase.validatePasswordLoginUseCase(currentLoginCredential.password)
        val emailUseCaseResult =
            loginUseCase.validateEmailLoginUseCase(currentLoginCredential.login)

        val hasError = listOf(
            passwordUseCaseResult,
            emailUseCaseResult
        ).any {
            !it.isSuccess
        }

        return hasError.not()
    }

    companion object {
        private const val DEFAULT_ROLE = "user"
    }
}
