package com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model

data class EditProfileForm(
    val id: String = "",
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val password: String = "",
)