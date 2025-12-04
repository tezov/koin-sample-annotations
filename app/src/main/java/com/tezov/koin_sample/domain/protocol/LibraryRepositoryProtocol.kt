package com.tezov.koin_sample.domain.protocol

import com.tezov.koin_sample.domain.model.Library

interface LibraryRepositoryProtocol {
    suspend fun getHomeFeaturedLibrary(): Library
    suspend fun getFavoriteLibraries(onlyFavorites: Boolean): List<Library>
    suspend fun getProfileName(): String
}