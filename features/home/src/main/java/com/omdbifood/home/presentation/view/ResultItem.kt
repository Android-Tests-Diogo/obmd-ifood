package com.omdbifood.home.presentation.view

import android.graphics.drawable.Drawable

data class ResultItem(
    val posterUrl: String,
    val movieId: String,
    val title: String,
    val isFavoriteForDiffUtilContent:Boolean = false,
    val favoriteDrawable: Drawable?
)
