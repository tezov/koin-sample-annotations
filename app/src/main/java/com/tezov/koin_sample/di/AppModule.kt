package com.tezov.koin_sample.di

import com.tezov.koin_sample.data.LibraryRepository
import com.tezov.koin_sample.domain.model.UserSession
import com.tezov.koin_sample.domain.protocol.LibraryRepositoryProtocol
import com.tezov.koin_sample.domain.usecase.GetFavoriteLibrariesUseCase
import com.tezov.koin_sample.domain.usecase.GetHomeFeaturedLibraryUseCase
import com.tezov.koin_sample.domain.usecase.GetProfileNameUseCase
import com.tezov.koin_sample.domain.usecase.GetTitleUseCase
import com.tezov.koin_sample.presentation.MainActivityViewModel
import com.tezov.koin_sample.presentation.screens.favorite.FavoritesViewModel
import com.tezov.koin_sample.presentation.screens.home.HomeViewModel
import com.tezov.koin_sample.presentation.screens.profile.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    factory<LibraryRepositoryProtocol> { LibraryRepository() }

    factory { GetHomeFeaturedLibraryUseCase(get()) }
    factory { GetFavoriteLibrariesUseCase(get()) }
    factory { GetProfileNameUseCase(get()) }
    factory { GetTitleUseCase() }

    viewModel { HomeViewModel(get()) }
    viewModel { FavoritesViewModel(get()) }
    viewModel { MainActivityViewModel(get()) }
    viewModel { ProfileViewModel(get()) }

    scope(named("userSession")) {
        scoped<UserSession> {
            error("UserSession not set")
        }

    }
}