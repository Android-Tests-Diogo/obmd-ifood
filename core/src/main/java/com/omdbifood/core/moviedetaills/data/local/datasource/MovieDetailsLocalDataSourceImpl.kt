package com.omdbifood.core.moviedetaills.data.local.datasource

import com.omdbifood.common.mapper.Mapper
import com.omdbifood.core.moviedetaills.data.local.daos.MovieDetailsDao
import com.omdbifood.core.moviedetaills.data.local.model.MovieDetails
import com.omdbifood.core.moviedetaills.data.remote.datasource.MovieDetailsRemoteDataSource
import com.omdbifood.core.moviedetaills.data.remote.model.MovieDetailsResponse
import com.omdbifood.core.moviedetaills.domain.MovieDetailsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieDetailsLocalDataSourceImpl(
    private val dao: MovieDetailsDao,
    private val mapperToModel: Mapper<MovieDetailsResponse, MovieDetails>,
    private val mapperToEntity: Mapper<MovieDetails, MovieDetailsEntity>
) : MovieDetailsLocalDataSource {

    override suspend fun MovieDetailsRemoteDataSource.save(moviesDetailsResponse: MovieDetailsResponse) {
        dao.save(mapperToModel.map(moviesDetailsResponse))
    }

    override suspend fun delete(movieId: String) {
        dao.delete(movieId)
    }

    override fun getMovieDetails(movieId: String): Flow<MovieDetailsEntity> =
        flow {
            emit(mapperToEntity.map(dao.getMovieDetails(movieId)))
        }
}
