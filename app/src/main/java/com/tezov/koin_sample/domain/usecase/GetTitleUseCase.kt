package com.tezov.koin_sample.domain.usecase

import org.koin.core.annotation.Factory

@Factory
class GetTitleUseCase {
    operator fun invoke(): String = "Koin Sample"
}