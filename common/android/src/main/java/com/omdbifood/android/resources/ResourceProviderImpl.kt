package com.omdbifood.android.resources

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

class ResourceProviderImpl(private val context: Context) : ResourceProvider {

    override fun getString(@StringRes resourceId: Int): String =
        context.resources.getString(resourceId)

    override fun getDrawable(@DrawableRes resourceId: Int): Drawable? =
        ContextCompat.getDrawable(context, resourceId)
}
