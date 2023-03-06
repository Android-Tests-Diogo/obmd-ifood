package com.omdbifood.home.favorites.domain

import com.omdbifood.common.database.FlowGenericResult
import com.omdbifood.core.results.domain.ResultEntity
import com.omdbifood.core.results.favorites.data.local.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavoritesUseCase(
    private val favoritesRepository: FavoritesRepository
) {

    fun manageFavorite(movieId: String): Flow<FlowGenericResult> = flow {
        favoritesRepository.getAllFavorites().collect { favorites ->
            favorites.firstOrNull { favorite ->
                movieId == favorite.imdbId
            }.let { result ->
                if (isFavorite(result)) {
                    favoritesRepository.removeFavorite(movieId).collect {
                        emit(it)
                    }
                } else {
                    favoritesRepository.addFavorites(movieId).collect {
                        emit(it)
                    }
                }
            }
        }
    }

    private fun isFavorite(result: ResultEntity?): Boolean = result != null

    fun getAllFavorites(): Flow<List<ResultEntity>> =
        favoritesRepository.getAllFavorites()
}
