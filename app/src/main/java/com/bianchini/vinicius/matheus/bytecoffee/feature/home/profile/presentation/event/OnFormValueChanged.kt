package com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.presentation.event

sealed class OnFormValueChanged {

    data class OnNameChanged(val name: String) : OnFormValueChanged()

    data class OnLastNameChanged(val lastName: String) : OnFormValueChanged()

    data class OnEmailChanged(val email: String) : OnFormValueChanged()

    data class OnPasswordChanged(val password: String) : OnFormValueChanged()
}