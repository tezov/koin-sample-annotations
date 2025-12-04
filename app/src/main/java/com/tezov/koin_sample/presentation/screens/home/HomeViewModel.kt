package com.tezov.koin_sample.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tezov.koin_sample.domain.model.Library
import com.tezov.koin_sample.domain.usecase.GetHomeFeaturedLibraryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class HomeUiState(
    val isLoading: Boolean = true,
    val library: Library? = null
)

class HomeViewModel(
    private val getHomeFeaturedLibraryUseCase: GetHomeFeaturedLibraryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            _uiState.value = HomeUiState(isLoading = true)
            val library = getHomeFeaturedLibraryUseCase()
            _uiState.value = HomeUiState(isLoading = false, library = library)
        }
    }
}