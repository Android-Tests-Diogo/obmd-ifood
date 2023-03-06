package com.omdbifood.core.moviedetaills.data.remote.datasource

import com.omdbifood.core.moviedetaills.data.local.datasource.MovieDetailsLocalDataSource
import com.omdbifood.core.moviedetaills.data.remote.service.MovieDetailsService
import com.omdbifood.core.moviedetaills.domain.MovieDetailsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieDetailsRemoteDataSourceImpl(
    private val service: MovieDetailsService,
    private val localDataSource: MovieDetailsLocalDataSource,
) : MovieDetailsRemoteDataSource {

    override fun getMovieDetails(movieId: String): Flow<MovieDetailsEntity> =
        flow {
            val response = service.fetchMovieDetails(movieId)

            with(localDataSource) {
                save(response)
            }

            localDataSource.getMovieDetails(movieId).collect { entity ->
                emit(entity)
            }
        }
}
