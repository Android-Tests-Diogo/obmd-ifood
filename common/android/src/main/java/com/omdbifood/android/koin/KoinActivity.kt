package com.omdbifood.android.koin

import androidx.annotation.LayoutRes
import org.koin.androidx.scope.ScopeActivity
import org.koin.core.module.Module

abstract class KoinActivity(
    @LayoutRes layoutId: Int = 0,
    private val module: FeatureModule
) : ScopeActivity(layoutId) {

    fun getFeatureModule(featureModule: (List<Module>) -> Unit) {
        featureModule.invoke(module.modules)
    }
}
