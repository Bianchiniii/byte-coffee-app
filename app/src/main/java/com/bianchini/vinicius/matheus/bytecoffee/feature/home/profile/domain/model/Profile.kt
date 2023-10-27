package com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model

import com.bianchini.vinicius.matheus.bytecoffee.db.profile.enity.ProfileEntity

data class Profile(
    val id: String,
    val name: String,
    val lastName: String,
    val email: String,
    val profileImage: String
)

fun Profile.toEntity() = ProfileEntity(
    id = id,
    name = name,
    lastName = lastName,
    email = email,
    profileImage = profileImage
)