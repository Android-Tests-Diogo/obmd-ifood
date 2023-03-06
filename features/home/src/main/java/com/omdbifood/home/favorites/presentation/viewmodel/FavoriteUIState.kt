package com.omdbifood.home.favorites.presentation.viewmodel

import com.omdbifood.home.presentation.view.ResultItem
import com.omdbifood.home.common.MoviesUIState

data class FavoriteUIState(
    override val results: List<ResultItem> = listOf()
) : MoviesUIState
