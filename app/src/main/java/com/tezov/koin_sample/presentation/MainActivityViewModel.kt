package com.tezov.koin_sample.presentation

import androidx.lifecycle.ViewModel
import com.tezov.koin_sample.domain.usecase.GetTitleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivityViewModel(
    private val getTitleUseCase: GetTitleUseCase
) : ViewModel() {

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title

    init {
        _title.value = getTitleUseCase()
    }
}