package com.omdbifood.core.results.favorites.data.local.repository

import com.omdbifood.common.database.FlowGenericResult
import com.omdbifood.core.results.data.local.ResultsTemporaryDataSource
import com.omdbifood.core.results.domain.ResultEntity
import com.omdbifood.core.results.favorites.data.local.datasource.FavoritesLocalDataSource
import kotlinx.coroutines.flow.Flow

class FavoritesRepositoryImpl(
    private val favoritesLocalDataSourceMock: FavoritesLocalDataSource,
    private val temporaryResultsDataSourceMock: ResultsTemporaryDataSource
) : FavoritesRepository {

    override fun addFavorites(movieId: String): Flow<FlowGenericResult> =
        favoritesLocalDataSourceMock.addFavorite(
            temporaryResultsDataSourceMock.getResultToAddFavorites(
                movieId
            )
        )

    override fun removeFavorite(imdbId: String): Flow<FlowGenericResult> =
        favoritesLocalDataSourceMock.removeFavorite(imdbId)

    override fun getAllFavorites(): Flow<List<ResultEntity>> =
        favoritesLocalDataSourceMock.getAllFavorites()

    override fun removeAllFavorites(): Flow<FlowGenericResult> =
        favoritesLocalDataSourceMock.removeAllFavorites()
}
