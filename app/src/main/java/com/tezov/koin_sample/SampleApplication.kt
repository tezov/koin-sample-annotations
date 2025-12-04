package com.tezov.koin_sample

import android.app.Application
import com.tezov.koin_sample.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class SampleApplication: Application() {

    lateinit var koin: KoinApplication

    override fun onCreate() {
        super.onCreate()
        koin = startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@SampleApplication)
            modules(appModule)
        }
    }
}