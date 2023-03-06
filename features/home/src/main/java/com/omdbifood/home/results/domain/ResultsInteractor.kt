package com.omdbifood.home.results.domain

import com.omdbifood.common.database.FlowGenericResult
import com.omdbifood.core.results.domain.ResultEntity
import com.omdbifood.home.favorites.domain.FavoritesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

class ResultsInteractor(
    private val favoritesUseCase: FavoritesUseCase,
    private val resultsUseCase: ResultsUseCase,
) {

    fun manageFavorite(movieId: String): Flow<FlowGenericResult> =
        favoritesUseCase.manageFavorite(movieId)

    fun getResults(name: String, page: Int): Flow<List<ResultEntity>> = flow {
        favoritesUseCase.getAllFavorites().collect { favoritesResults ->
            val favoritesIds = favoritesResults.map { result ->
                result.imdbId
            }.toList()

            compareFavoritesIdsWithResults(
                this,
                favoritesIds,
                name,
                page
            )
        }
    }

    private suspend fun compareFavoritesIdsWithResults(
        flow: FlowCollector<List<ResultEntity>>,
        favoritesIds: List<String>,
        name: String,
        page: Int
    ) {
        resultsUseCase(name, page).collect { results ->
            flow.emit(
                results.map { result ->
                    result.copy(
                        isFavorite = favoritesIds.contains(result.imdbId)
                    )
                }
            )
        }
    }
}
