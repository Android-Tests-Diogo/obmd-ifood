package com.omdbifood.home.favorites.presentation.viewmodel

import com.omdbifood.home.presentation.view.ResultItem
import com.omdbifood.home.common.MoviesUIState

data class FavoriteUIState(
    val noFavoritesFoundTextVisibility: Boolean = false,
    override val results: List<ResultItem> = listOf()
) : MoviesUIState
