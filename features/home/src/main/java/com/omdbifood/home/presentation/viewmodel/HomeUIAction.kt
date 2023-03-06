package com.omdbifood.home.presentation.viewmodel

import com.omdbifood.android.viewmodel.UIAction

sealed class HomeUIAction : UIAction {

    object UpdateFavoritesPage : HomeUIAction()

    object UpdateFavoritesOnResultsPage : HomeUIAction()
}
