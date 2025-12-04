package com.tezov.koin_sample.presentation.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tezov.koin_sample.domain.model.Library
import com.tezov.koin_sample.domain.usecase.GetFavoriteLibrariesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class FavoritesUiState(
    val isLoading: Boolean = true,
    val libraries: List<Library> = emptyList()
)

class FavoritesViewModel(
    private val getFavoriteLibrariesUseCase: GetFavoriteLibrariesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoritesUiState())
    val uiState: StateFlow<FavoritesUiState> = _uiState

    init {
        load(true)
    }

    fun load(onlyFavorites: Boolean) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val list = getFavoriteLibrariesUseCase(onlyFavorites)
            _uiState.value = _uiState.value.copy(isLoading = false, libraries = list)
        }
    }
}