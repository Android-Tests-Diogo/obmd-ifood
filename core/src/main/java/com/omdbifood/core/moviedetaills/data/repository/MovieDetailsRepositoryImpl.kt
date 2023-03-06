package com.omdbifood.core.moviedetaills.data.repository

import com.omdbifood.core.moviedetaills.data.local.datasource.MovieDetailsLocalDataSource
import com.omdbifood.core.moviedetaills.data.remote.datasource.MovieDetailsRemoteDataSource
import com.omdbifood.core.moviedetaills.domain.MovieDetailsEntity
import kotlinx.coroutines.flow.Flow

class MovieDetailsRepositoryImpl(
    private val localDataSource: MovieDetailsLocalDataSource,
    private val remoteDataSource: MovieDetailsRemoteDataSource
) : MovieDetailsRepository {

    override fun getMovieDetails(movieId: String): Flow<MovieDetailsEntity> =
        kotlin.runCatching {
            getFromLocal(movieId)
        }.getOrElse {
            getFromRemote(movieId)
        }

    private fun getFromLocal(movieId: String): Flow<MovieDetailsEntity> =
        localDataSource.getMovieDetails(movieId)

    private fun getFromRemote(movieId: String): Flow<MovieDetailsEntity> =
        remoteDataSource.getMovieDetails(movieId)
}


