package com.tezov.koin_sample.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tezov.koin_sample.domain.model.Library
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Library of the Day",
            style = MaterialTheme.typography.headlineMedium
        )

        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            state.library?.let { library ->
                FeaturedLibraryCard(library)
            }
        }
    }
}

@Composable
private fun FeaturedLibraryCard(library: Library) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = library.name,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = library.description,
                style = MaterialTheme.typography.bodyMedium
            )

            Button(
                onClick = {},
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("More info")
            }
        }
    }
}
