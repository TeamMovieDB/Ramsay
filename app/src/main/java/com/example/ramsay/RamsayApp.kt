package com.example.ramsay

import android.app.Application
import com.example.ramsay.utils.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RamsayApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RamsayApp)
            modules(appModule)
        }
    }
}