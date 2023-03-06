package com.omdbifood.android.koin

import android.content.Context
import androidx.annotation.LayoutRes
import org.koin.android.ext.android.getKoin
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.scope.scopeActivity
import org.koin.core.component.getScopeId
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.StringQualifier
import org.koin.core.scope.Scope
import org.koin.ext.getFullName

abstract class KoinFragment(
    @LayoutRes layoutId: Int = 0
) : ScopeFragment(layoutId) {

    var isFragmentReady: () -> Unit = {}
    override fun onAttach(context: Context) {
        super.onAttach(context)

        isFragmentReady.invoke()
    }
}
