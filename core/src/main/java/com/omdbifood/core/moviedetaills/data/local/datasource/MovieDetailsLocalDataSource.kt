package com.omdbifood.core.moviedetaills.data.local.datasource

import com.omdbifood.core.moviedetaills.data.datasource.MoviesDetailsDataSource
import com.omdbifood.core.moviedetaills.data.remote.datasource.MovieDetailsRemoteDataSource
import com.omdbifood.core.moviedetaills.data.remote.model.MovieDetailsResponse

interface MovieDetailsLocalDataSource : MoviesDetailsDataSource {
    suspend fun MovieDetailsRemoteDataSource.save(moviesDetailsResponse: MovieDetailsResponse)
    suspend fun delete(movieId: String)
}
