package com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.data.response

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.Address
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.Profile
import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("user")
    val profileInfo: ProfileResponseWrapper,
    @SerializedName("address")
    val address: AddressResponseWrapper,
    @SerializedName("token")
    val token: String
)

data class ProfileResponseWrapper(
    @SerializedName("id")
    val id: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val surname: String,
)

data class AddressResponseWrapper(
    @SerializedName("id")
    val id: String,
    @SerializedName("street")
    val street: String,
    @SerializedName("number")
    val number: String,
    @SerializedName("neighborhood")
    val neighborhood: String,
    @SerializedName("city_and_state")
    val cityAndState: String,
)

fun ProfileResponseWrapper.toDomain() = Profile(
    id = id,
    email = email,
    name = name,
    lastName = surname,
    profileImage = ""
)

fun AddressResponseWrapper.toDomain() = Address(
    id = id,
    street = street,
    number = number,
    neighborhood = neighborhood,
    cityAndState = cityAndState,
)