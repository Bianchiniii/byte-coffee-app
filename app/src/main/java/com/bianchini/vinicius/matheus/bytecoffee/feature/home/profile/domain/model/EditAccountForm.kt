package com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model

data class EditAccountForm(
    val id: String = "",
    val name: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
)