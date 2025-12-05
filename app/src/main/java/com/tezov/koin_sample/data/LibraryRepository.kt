package com.tezov.koin_sample.data

import com.tezov.koin_sample.domain.model.Library
import com.tezov.koin_sample.domain.protocol.LibraryRepositoryProtocol
import kotlinx.coroutines.delay
import org.koin.core.annotation.Single

@Single
class LibraryRepository : LibraryRepositoryProtocol {

    private val libraries = listOf(
        Library(
            id = "koin",
            name = "Koin",
            description = "Kotlin dependency injection framework",
            isFavorite = true
        ),
        Library(
            id = "compose",
            name = "Jetpack Compose",
            description = "Modern toolkit for building native UI",
            isFavorite = true
        ),
        Library(
            id = "coroutines",
            name = "Kotlin Coroutines",
            description = "Asynchronous programming framework",
            isFavorite = false
        ),
        Library(
            id = "ktor",
            name = "Ktor",
            description = "Asynchronous framework for creating microservices and web applications",
            isFavorite = false
        ),
        Library(
            id = "serialization",
            name = "Kotlinx Serialization",
            description = "Kotlin serialization library for JSON, ProtoBuf and more",
            isFavorite = true
        ),
        Library(
            id = "room",
            name = "Room",
            description = "SQLite object-mapping library built on top of SQLite",
            isFavorite = false
        ),
        Library(
            id = "datastore",
            name = "DataStore",
            description = "Modern data storage solution replacing SharedPreferences",
            isFavorite = true
        )
    )

    override suspend fun getHomeFeaturedLibrary(): Library {
        delay(500)
        return libraries.first()
    }

    override suspend fun getFavoriteLibraries(onlyFavorites: Boolean): List<Library> {
        delay(500)
        return if (onlyFavorites)
            libraries.filter { it.isFavorite }
        else
            libraries.filter { !it.isFavorite }
    }

    override suspend fun getProfileName(): String {
        delay(300)
        return "Tezov"
    }
}