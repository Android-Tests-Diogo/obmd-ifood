package com.omdbifood.android.viewbinding

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

@Suppress("unused")
inline fun <reified T : ViewBinding> Fragment.viewBinding(): ReadOnlyProperty<Fragment, T> =
    FragmentBindingProperty { fragment ->
        fragment.requireView().bind(T::class.java)
    }

class FragmentBindingProperty<T : ViewBinding>(
    private val viewBindingCreator: (Fragment) -> T
) : ReadOnlyProperty<Fragment, T> {

    private var viewBinding: T? = null
    private val lifecycleObserver = BindingLifecycleObserver()

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        thisRef.viewLifecycleOwner.lifecycle.addObserver(lifecycleObserver)
        return viewBinding ?: viewBindingCreator(thisRef).also { viewBinding = it }
    }

    private inner class BindingLifecycleObserver : DefaultLifecycleObserver {

        override fun onDestroy(owner: LifecycleOwner) {
            owner.lifecycle.removeObserver(this)
            Handler(Looper.getMainLooper()).post {
                viewBinding = null
            }
        }
    }
}
