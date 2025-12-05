package com.tezov.koin_sample.domain.usecase

import com.tezov.koin_sample.domain.model.Library
import com.tezov.koin_sample.domain.protocol.LibraryRepositoryProtocol
import org.koin.core.annotation.Factory

@Factory
class GetFavoriteLibrariesUseCase(
    private val repository: LibraryRepositoryProtocol
) {
    suspend operator fun invoke(onlyFavorites: Boolean): List<Library> = repository.getFavoriteLibraries(onlyFavorites)
}