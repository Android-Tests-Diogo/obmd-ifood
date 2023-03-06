package com.omdbifood.core.results.favorites.data.local.datasource

import com.omdbifood.common.database.FlowGenericResult
import com.omdbifood.core.results.domain.ResultEntity
import kotlinx.coroutines.flow.Flow

interface FavoritesLocalDataSource {
    fun addFavorite(resultEntity: ResultEntity) : Flow<FlowGenericResult>
    fun removeFavorite(imdbId: String) : Flow<FlowGenericResult>
    fun getAllFavorites(): Flow<List<ResultEntity>>
    fun removeAllFavorites() : Flow<FlowGenericResult>
}
