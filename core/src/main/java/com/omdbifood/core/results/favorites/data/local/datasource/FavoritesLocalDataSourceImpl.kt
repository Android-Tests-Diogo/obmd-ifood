package com.omdbifood.core.results.favorites.data.local.datasource

import com.omdbifood.common.database.FlowGenericResult
import com.omdbifood.common.mapper.Mapper
import com.omdbifood.core.flow.flowIO
import com.omdbifood.core.results.domain.ResultEntity
import com.omdbifood.core.results.favorites.data.local.dao.FavoritesDao
import com.omdbifood.core.results.favorites.data.local.model.Result
import kotlinx.coroutines.flow.Flow

class FavoritesLocalDataSourceImpl(
    private val dao: FavoritesDao,
    private val mapperToResult: Mapper<ResultEntity, Result>,
    private val mapperToEntityList: Mapper<List<Result>, List<ResultEntity>>
) : FavoritesLocalDataSource {

    override fun addFavorite(resultEntity: ResultEntity): Flow<FlowGenericResult> =
        flowIO {
            dao.save(mapperToResult.map(resultEntity))
            emit(FlowGenericResult.Successful)
        }

    override fun removeFavorite(imdbId: String): Flow<FlowGenericResult> =
        flowIO {
            dao.delete(imdbId)
            emit(FlowGenericResult.Successful)
        }

    override fun getAllFavorites(): Flow<List<ResultEntity>> =
        flowIO {
            emit(mapperToEntityList.map(dao.getAllFavorites()))
        }

    override fun removeAllFavorites(): Flow<FlowGenericResult> =
        flowIO {
            dao.deleteAll()
            emit(FlowGenericResult.Successful)
        }
}
