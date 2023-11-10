package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.model

import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName

data class NewAccountForm(
    @SerializedName("profile_info")
    val profileInfo: ProfileInfo,
    @SerializedName("address")
    val address: Address
) {
    data class ProfileInfo(
        @SerializedName("name")
        val name: String = "",
        @SerializedName("surname")
        val surname: String = "",
        @SerializedName("email")
        val email: String = "",
        @SerializedName("password")
        val password: String = "",
        @SerializedName("role")
        val role: String = "",
    )

    data class Address(
        @SerializedName("street")
        val street: String = "",
        @SerializedName("number")
        val number: String = "",
        @SerializedName("neighborhood")
        val neighborhood: String = "",
        @SerializedName("city_and_state")
        val cityAndState: String = "",
    )
}

data class NewAccountFormError(
    @StringRes val nameError: Int? = null,
    @StringRes val surnameError: Int? = null,
    @StringRes val emailError: Int? = null,
    @StringRes val passwordError: Int? = null,
    @StringRes val streetError: Int? = null,
    @StringRes val numberError: Int? = null,
    @StringRes val neighborhoodError: Int? = null,
    @StringRes val cityAndStateError: Int? = null,
)

