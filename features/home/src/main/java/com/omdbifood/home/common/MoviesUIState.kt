package com.omdbifood.home.common

import com.omdbifood.android.viewmodel.UIState
import com.omdbifood.home.presentation.view.ResultItem

interface MoviesUIState : UIState {
    val results: List<ResultItem>
}
