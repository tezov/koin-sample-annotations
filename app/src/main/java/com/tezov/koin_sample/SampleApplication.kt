package com.tezov.koin_sample

import android.app.Application
import com.tezov.koin_sample.di.AppKoin
import com.tezov.koin_sample.di.userSessionModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.ksp.generated.*

class SampleApplication: Application() {

    // Not used he this sample. Old way to be able to access to injection instance. I'm sentimental xD
    lateinit var koin: KoinApplication

    override fun onCreate() {
        super.onCreate()
        koin = AppKoin.startKoin {
            androidContext(this@SampleApplication)
            modules(userSessionModule)
        }
    }
}