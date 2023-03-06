package com.omdbifood.sample

import android.app.Application
import com.omdbifood.sample.di.KoinLifeCycleCallbacks
import com.omdbifood.sample.di.databaseModule
import com.omdbifood.sample.di.networkModule
import com.omdbifood.sample.di.resourcesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.loadKoinModules

class OmdbApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(applicationContext)
            loadKoinModules(listOf(networkModule, databaseModule, resourcesModule))
        }

        registerActivityLifecycleCallbacks(KoinLifeCycleCallbacks())
    }
}
