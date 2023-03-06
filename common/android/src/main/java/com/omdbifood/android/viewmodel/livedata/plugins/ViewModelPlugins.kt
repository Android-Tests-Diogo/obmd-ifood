package com.omdbifood.android.viewmodel.livedata.plugins

import androidx.annotation.VisibleForTesting
import com.omdbifood.android.viewmodel.livedata.LiveDataFactory
import com.omdbifood.android.viewmodel.livedata.LiveDataFactoryImpl

object ViewModelPlugins {

    var factory: LiveDataFactory = LiveDataFactoryImpl()
        private set

    @VisibleForTesting
    fun setFactory(factory: LiveDataFactory) {
        ViewModelPlugins.factory = factory
    }
}
