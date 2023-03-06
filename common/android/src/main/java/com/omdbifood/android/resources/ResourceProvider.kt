package com.omdbifood.android.resources

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes resourceId: Int) : String
    fun getDrawable(@DrawableRes resourceId: Int) : Drawable?
}
