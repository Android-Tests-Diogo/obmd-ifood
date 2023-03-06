package com.omdbifood.android.viewbinding

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

@Suppress("unused")
inline fun <reified T : ViewBinding> ViewHolder.viewBinding(): ReadOnlyProperty<ViewHolder, T> =
    ViewHolderBindingProperty { viewHolder ->
        viewHolder.itemView.bind(T::class.java)
    }

class ViewHolderBindingProperty<T : ViewBinding>(
    private val viewBindingCreator: (ViewHolder) -> T
) : ReadOnlyProperty<ViewHolder, T> {

    private var viewViewBinding: T? = null

    override fun getValue(thisRef: ViewHolder, property: KProperty<*>): T {
        return viewViewBinding ?: viewBindingCreator(thisRef).also { viewViewBinding = it }
    }
}
