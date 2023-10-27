package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.domain.model

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.Profile

data class ProfileToken(
    val token: String,
    val profile: Profile,
)
