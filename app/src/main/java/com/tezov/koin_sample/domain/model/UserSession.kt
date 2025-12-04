package com.tezov.koin_sample.domain.model

sealed class UserSession {
    data class NormalUser(val name: String) : UserSession()
    data class AdminUser(val name: String, val adminLevel: Int) : UserSession()
}