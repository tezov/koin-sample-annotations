package com.tezov.koin_sample

import android.app.Application
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext.startKoin

class SampleApplication: Application() {

    lateinit var koin: KoinApplication

    override fun onCreate() {
        super.onCreate()
        koin = startKoin {


        }
    }
}