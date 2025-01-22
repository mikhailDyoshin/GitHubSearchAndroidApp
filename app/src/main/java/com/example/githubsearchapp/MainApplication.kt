package com.example.githubsearchapp

import android.app.Application
import com.example.githubsearchapp.di.repositoryModule
import com.example.githubsearchapp.di.networkModule
import com.example.githubsearchapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(repositoryModule, networkModule, viewModelModule)
        }
    }
}