package com.omdbifood.android.koin

import android.content.Context
import androidx.annotation.LayoutRes
import org.koin.androidx.scope.ScopeFragment

abstract class KoinFragment(
    @LayoutRes layoutId: Int = 0
) : ScopeFragment(layoutId) {

    var isFragmentReady: () -> Unit = {}
    override fun onAttach(context: Context) {
        super.onAttach(context)

        isFragmentReady.invoke()
    }
}
