package com.tezov.koin_sample.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class AppDestination(
    val label: String,
    val icon: ImageVector,
    val route: String
) {
    HOME("Home", Icons.Default.Home, "home"),
    FAVORITES("Favorites", Icons.Default.Favorite, "favorites"),
    PROFILE("Profile", Icons.Default.AccountBox, "profile"),
}