package com.example.quotable

import android.app.Application
import com.example.quotable.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
    startKoin{
        androidContext(this@MyApplication)
        modules(listOf(appModule))
    }
    }
}