package com.omdbifood.home.presentation.viewmodel

import com.omdbifood.android.viewmodel.ViewModel

class HomeViewModel : ViewModel<HomeUIState, HomeUIAction>(HomeUIState()) {

    private var isFavoritesFragmentReady = false

    fun onFetchResultsChanged(isFetchingResults: Boolean) {
        setState {
            it.copy(
                bottomLoadingVisibility = isFetchingResults
            )
        }
    }

    fun onFavoritesChanged() {
        sendAction {
            HomeUIAction.UpdateFavoritesOnResultsPage
        }
    }

    fun onFavoritesInResultsChanged() {
        if (isFavoritesFragmentReady) {
            sendAction {
                HomeUIAction.UpdateFavoritesPage
            }
        }
    }

    fun onFavoritesFragmentReady() {
        isFavoritesFragmentReady = true
    }
}
