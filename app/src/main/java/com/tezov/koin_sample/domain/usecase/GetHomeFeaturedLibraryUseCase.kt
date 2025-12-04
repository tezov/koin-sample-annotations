package com.tezov.koin_sample.domain.usecase

import com.tezov.koin_sample.domain.model.Library
import com.tezov.koin_sample.domain.protocol.LibraryRepositoryProtocol

class GetHomeFeaturedLibraryUseCase(
    private val repository: LibraryRepositoryProtocol
) {
    suspend operator fun invoke(): Library = repository.getHomeFeaturedLibrary()
}