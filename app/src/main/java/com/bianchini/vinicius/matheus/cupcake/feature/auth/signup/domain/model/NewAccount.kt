package com.bianchini.vinicius.matheus.cupcake.feature.auth.signup.domain.model

data class NewAccount(
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
)