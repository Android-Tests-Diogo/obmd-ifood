package com.omdbifood.sample.di

import com.omdbifood.android.resources.ResourceProvider
import com.omdbifood.android.resources.ResourceProviderImpl
import org.koin.dsl.module

val resourcesModule = module {
    factory<ResourceProvider> {
        ResourceProviderImpl(
            get()
        )
    }
}
