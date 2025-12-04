package com.tezov.koin_sample.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tezov.koin_sample.domain.model.UserSession
import com.tezov.koin_sample.domain.usecase.GetProfileNameUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.mp.KoinPlatform.getKoin

data class ProfileUiState(
    val isConnected: Boolean = false,
    val isLoading: Boolean = false,
    val name: String = "",
    val role: String = ""
)

class ProfileViewModel(
    private val getProfileNameUseCase: GetProfileNameUseCase
) : ViewModel() {

    private val koin = getKoin()
    private var sessionScope: Scope? = koin.getScopeOrNull("currentSession")

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState

    init {
        load()
    }

    private fun load() {
        val session = sessionScope?.getOrNull<UserSession>() ?: return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val name = getProfileNameUseCase()

            when (session) {
                is UserSession.NormalUser ->
                    _uiState.value = ProfileUiState(
                        isConnected = true,
                        isLoading = false,
                        name = name,
                        role = "Normal user"
                    )

                is UserSession.AdminUser ->
                    _uiState.value = ProfileUiState(
                        isConnected = true,
                        isLoading = false,
                        name = name,
                        role = "Admin (lvl ${session.adminLevel})"
                    )
            }
        }
    }

    fun loginNormal() {
        viewModelScope.launch {
            _uiState.value = ProfileUiState(isLoading = true)
            delay(500)
            val name = getProfileNameUseCase()
            val sessionScope = koin.createScope("currentSession", named("userSession")).also {
                sessionScope = it
            }
            sessionScope.declare<UserSession>(UserSession.NormalUser(name))

            _uiState.value = ProfileUiState(
                isConnected = true,
                isLoading = false,
                name = name,
                role = "Normal user"
            )
        }
    }

    fun loginAdmin() {
        viewModelScope.launch {
            _uiState.value = ProfileUiState(isLoading = true)
            delay(500)
            val name = getProfileNameUseCase()
            val sessionScope = koin.createScope("currentSession", named("userSession")).also {
                sessionScope = it
            }
            sessionScope.declare<UserSession>(UserSession.AdminUser(name, 5))

            _uiState.value = ProfileUiState(
                isConnected = true,
                isLoading = false,
                name = name,
                role = "Admin (lvl 5)"
            )
        }
    }

    fun logout() {
        sessionScope?.close()
        sessionScope = null
        _uiState.value = ProfileUiState()
    }
}
