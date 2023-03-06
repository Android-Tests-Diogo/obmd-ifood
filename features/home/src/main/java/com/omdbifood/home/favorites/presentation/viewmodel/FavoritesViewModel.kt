package com.omdbifood.home.favorites.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.omdbifood.android.viewmodel.ViewModel
import com.omdbifood.home.favorites.domain.FavoritesUseCase
import com.omdbifood.home.presentation.view.ResultItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val favoritesUseCase: FavoritesUseCase
) : ViewModel<FavoriteUIState, FavoritesUIAction>(FavoriteUIState()) {

    fun getFavorites() {
        viewModelScope.launch {
            fetchData()
        }
    }

    private suspend fun fetchData() {
        favoritesUseCase.getAllFavorites().collect {
            setState { state ->
                state.copy(
                    results = it.map { entity ->
                        ResultItem(
                            movieId = entity.imdbId,
                            title = entity.title,
                            favoriteVisibility = true
                        )
                    }
                )
            }
        }
    }

    fun onFavoritesManagerClicked(movieId: String) {
        viewModelScope.launch {
            favoritesUseCase.manageFavorite(movieId).collectLatest {
                fetchData()
                sendAction {
                    FavoritesUIAction.FavoritesChanged
                }
            }
        }
    }
}
