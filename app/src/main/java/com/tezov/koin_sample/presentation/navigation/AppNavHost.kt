package com.tezov.koin_sample.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tezov.koin_sample.presentation.screens.favorite.FavoritesScreen
import com.tezov.koin_sample.presentation.screens.home.HomeScreen
import com.tezov.koin_sample.presentation.screens.profile.ProfileScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = AppDestination.HOME.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(AppDestination.HOME.route) { HomeScreen() }
        composable(AppDestination.FAVORITES.route) { FavoritesScreen() }
        composable(AppDestination.PROFILE.route) { ProfileScreen() }
    }
}