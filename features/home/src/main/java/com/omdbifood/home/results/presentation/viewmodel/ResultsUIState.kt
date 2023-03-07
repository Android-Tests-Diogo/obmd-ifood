package com.omdbifood.home.results.presentation.viewmodel

import com.omdbifood.home.common.MoviesUIState
import com.omdbifood.home.presentation.view.ResultItem

data class ResultsUIState(
    val searchLoadingVisibility: Boolean = false,
    override val results: List<ResultItem> = listOf()
) : MoviesUIState
