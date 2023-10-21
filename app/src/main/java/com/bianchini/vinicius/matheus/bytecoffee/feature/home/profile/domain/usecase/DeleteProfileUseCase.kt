package com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.usecase

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.Profile

interface DeleteProfileUseCase {

    operator fun invoke(params: Params)

    data class Params(
        val profile: Profile
    )
}

class DeleteProfileUseCaseImpl : DeleteProfileUseCase {

    override fun invoke(params: DeleteProfileUseCase.Params) {
        TODO()
    }
}