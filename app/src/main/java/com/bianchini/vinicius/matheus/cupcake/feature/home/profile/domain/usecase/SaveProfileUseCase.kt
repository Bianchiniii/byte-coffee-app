package com.bianchini.vinicius.matheus.cupcake.feature.home.profile.domain.usecase

import com.bianchini.vinicius.matheus.cupcake.feature.home.profile.domain.Profile

interface SaveProfileUseCase {

    operator fun invoke(params: Params)

    data class Params(
        val profile: Profile
    )
}

class SaveProfileUseCaseImpl : SaveProfileUseCase {

    override fun invoke(params: SaveProfileUseCase.Params) {
        TODO()
    }
}