package com.omdbifood.home.favorites.presentation.viewmodel

import com.omdbifood.home.common.MoviesUIState
import com.omdbifood.home.presentation.view.ResultItem

data class FavoriteUIState(
    val noFavoritesFoundTextVisibility: Boolean = false,
    override val results: List<ResultItem> = listOf()
) : MoviesUIState
