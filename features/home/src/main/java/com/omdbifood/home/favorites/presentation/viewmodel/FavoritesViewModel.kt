package com.omdbifood.home.favorites.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.omdbifood.android.resources.ResourceProvider
import com.omdbifood.android.viewmodel.ViewModel
import com.omdbifood.home.R
import com.omdbifood.home.favorites.domain.FavoritesUseCase
import com.omdbifood.home.presentation.view.ResultItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val favoritesUseCase: FavoritesUseCase,
    private val resourceProvider: ResourceProvider
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
                            posterUrl = entity.posterUrl,
                            movieId = entity.imdbId,
                            title = entity.title,
                            isFavoriteForDiffUtilContent = true,
                            favoriteDrawable = resourceProvider.getDrawable(
                                com.omdbifood.ui.R.drawable.ic_favorite
                            )
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
