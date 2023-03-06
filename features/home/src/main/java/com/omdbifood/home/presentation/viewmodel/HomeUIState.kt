package com.omdbifood.home.presentation.viewmodel

import com.omdbifood.android.viewmodel.UIState

data class HomeUIState(
    val bottomLoadingVisibility: Boolean = false
) : UIState
