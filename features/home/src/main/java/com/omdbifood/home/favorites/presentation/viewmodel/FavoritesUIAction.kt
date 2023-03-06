package com.omdbifood.home.favorites.presentation.viewmodel

import com.omdbifood.android.viewmodel.UIAction

sealed class FavoritesUIAction : UIAction {
    object FavoritesChanged : FavoritesUIAction()
}
