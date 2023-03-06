package com.omdbifood.android.resources

import android.content.Context

class ResourceProviderImpl(private val context: Context) : ResourceProvider {

    override fun getString(resourceId: Int) : String =
        context.resources.getString(resourceId)
}
