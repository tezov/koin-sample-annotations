package com.tezov.koin_sample.domain.usecase

import com.tezov.koin_sample.domain.protocol.LibraryRepositoryProtocol
import org.koin.core.annotation.Factory

@Factory
class GetProfileNameUseCase(
    private val repository: LibraryRepositoryProtocol
) {
    suspend operator fun invoke(): String = repository.getProfileName()
}