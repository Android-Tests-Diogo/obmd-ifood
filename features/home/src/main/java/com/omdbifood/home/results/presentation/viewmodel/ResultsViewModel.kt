package com.omdbifood.home.results.presentation.viewmodel

import android.graphics.drawable.Drawable
import androidx.lifecycle.viewModelScope
import com.omdbifood.android.resources.ResourceProvider
import com.omdbifood.android.viewmodel.ViewModel
import com.omdbifood.core.results.data.remote.exceptions.ResultsExceptions
import com.omdbifood.home.R
import com.omdbifood.home.presentation.view.ResultItem
import com.omdbifood.home.results.domain.ResultsInteractor
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

const val DEFAULT_PAGE = 1

class ResultsViewModel(
    private val resultsInteractor: ResultsInteractor,
    private val resourceProvider: ResourceProvider
) : ViewModel<ResultsUIState, ResultsUIAction>(ResultsUIState()) {

    private var lastInput: String = ""
    private var currentPage: Int = DEFAULT_PAGE

    fun syncData() {
        getResults(lastInput)
    }

    fun getResults(input: String) {
        lastInput = input

        viewModelScope.launch {
            sendAction {
                ResultsUIAction.SearchAllowed
            }

            setState {
                it.copy(
                    searchLoadingVisibility = true
                )
            }

            fetchResults(lastInput, currentPage)
        }
    }

    private suspend fun fetchResults(lastInput: String, page: Int) {
        resultsInteractor.getResults(lastInput, page)
            .catch { exception ->
                setState {
                    it.copy(
                        searchLoadingVisibility = false
                    )
                }

                sendAction {
                    ResultsUIAction.FetchingDataStatus(false)
                }

                handleException(exception)
            }
            .collect {
                setState { state ->
                    state.copy(
                        searchLoadingVisibility = false,
                        results = it.map { entity ->
                            ResultItem(
                                posterUrl = entity.posterUrl,
                                movieId = entity.imdbId,
                                title = entity.title,
                                isFavoriteForDiffUtilContent = entity.isFavorite,
                                favoriteDrawable = getDrawableByFavorite(entity.isFavorite)
                            )
                        }
                    )
                }
                sendAction {
                    ResultsUIAction.FetchingDataStatus(false)
                }
                sendAction {
                    ResultsUIAction.SearchAllowed
                }
            }
    }

    private fun getDrawableByFavorite(isFavorite: Boolean): Drawable? =
        if (isFavorite) {
            resourceProvider.getDrawable(com.omdbifood.ui.R.drawable.ic_favorite)
        } else {
            resourceProvider.getDrawable(com.omdbifood.ui.R.drawable.ic_favorite_background)
        }


    private fun handleException(exception: Throwable) {
        when (exception) {
            is ResultsExceptions.NoResultsFound -> sendToastAction(R.string.home_movie_not_found)
            is ResultsExceptions.MinCharsAccepted -> sendToastAction(
                R.string.home_insert_more_than_three_chars
            )
            else -> sendToastAction(R.string.home_movie_error_message)
        }
    }

    private fun sendToastAction(resId: Int) {
        sendAction {
            ResultsUIAction.ShowToast(
                resourceProvider.getString(resId)
            )
        }
    }

    fun onFavoritesManagerClicked(movieId: String) {
        viewModelScope.launch {
            resultsInteractor.manageFavorite(movieId).collectLatest {
                sendAction {
                    ResultsUIAction.FavoritesChanged
                }
            }
            fetchResults(lastInput, currentPage)
        }
    }

    fun onLastResultReached() {
        currentPage++

        sendAction {
            ResultsUIAction.FetchingDataStatus(true)
        }

        viewModelScope.launch {
            fetchResults(lastInput, currentPage)
        }
    }
}
