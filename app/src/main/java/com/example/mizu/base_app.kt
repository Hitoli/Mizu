package com.example.mizu

import android.app.Application
import com.example.mizu.di.DIModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseAppMizu:Application(){
    override fun onCreate() {

        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BaseAppMizu)
            modules(DIModule)
        }
    }
}