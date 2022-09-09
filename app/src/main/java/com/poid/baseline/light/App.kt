package com.poid.baseline.light

import android.app.Application
import com.poid.baseline.light.di.appModule
import com.poid.baseline.light.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin()
    }

    private fun startKoin() {
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                appModule,
                networkModule,
//                dbModule
            )
        }
    }
}