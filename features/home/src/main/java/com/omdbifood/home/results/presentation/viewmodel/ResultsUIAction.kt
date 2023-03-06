package com.omdbifood.home.results.presentation.viewmodel

import com.omdbifood.android.viewmodel.UIAction

sealed class ResultsUIAction : UIAction {

    class FetchingDataStatus(val isFetchingData: Boolean) : ResultsUIAction()
    class ShowToast(val message: String) : ResultsUIAction()
    object SearchAllowed : ResultsUIAction()

    object FavoritesChanged : ResultsUIAction()
}
