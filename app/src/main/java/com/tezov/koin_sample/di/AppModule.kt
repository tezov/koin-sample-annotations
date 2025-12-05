package com.tezov.koin_sample.di

import com.tezov.koin_sample.domain.model.ScopedClass
import com.tezov.koin_sample.domain.model.UserSession
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module

@Module
@ComponentScan("com.tezov.koin_sample")
class AppModule

val userSessionModule = module {
    scope<UserSessionScope> {

        factory {
            val session = get<UserSession>()
            when (session) {
                is UserSession.AdminUser -> ScopedClass("admin from scope")
                is UserSession.NormalUser -> ScopedClass("normal user from scope")
            }
        }

    }
}