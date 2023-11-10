package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.presentation.event

sealed class OnTextInputChangedEventRegister {

    data class OnNameChangedRegister(
        val name: String
    ) : OnTextInputChangedEventRegister()

    data class OnLastNameChangedRegister(
        val lastName: String
    ) : OnTextInputChangedEventRegister()

    data class OnEmailChangedRegister(
        val email: String
    ) : OnTextInputChangedEventRegister()

    data class OnPasswordChangedRegister(
        val password: String
    ) : OnTextInputChangedEventRegister()

    data class OnStreetChangedRegister(
        val street: String
    ) : OnTextInputChangedEventRegister()

    data class OnNeighborhoodChangedRegister(
        val neighborhood: String
    ) : OnTextInputChangedEventRegister()

    data class OnNumberChangedRegister(
        val number: String
    ) : OnTextInputChangedEventRegister()

    data class OnCityAnStateChangedRegister(
        val cityAndState: String
    ) : OnTextInputChangedEventRegister()
}