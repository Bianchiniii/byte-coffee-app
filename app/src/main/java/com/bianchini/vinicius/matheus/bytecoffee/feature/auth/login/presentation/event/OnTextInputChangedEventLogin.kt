package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.presentation.event

sealed class OnTextInputChangedEventLogin {

    data class OnEmailChangedLogin(val email: String) : OnTextInputChangedEventLogin()

    data class OnPasswordChangedLogin(val password: String) : OnTextInputChangedEventLogin()
}