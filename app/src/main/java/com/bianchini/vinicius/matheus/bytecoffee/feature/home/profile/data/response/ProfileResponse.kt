package com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.data.response

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.Profile
import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val surname: String,
)

fun ProfileResponse.toDomain() = Profile(
    id = id,
    email = email,
    name = name,
    lastName = surname,
    profileImage = ""
)