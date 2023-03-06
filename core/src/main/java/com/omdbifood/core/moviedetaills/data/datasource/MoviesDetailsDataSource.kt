package com.omdbifood.core.moviedetaills.data.datasource

import com.omdbifood.core.moviedetaills.domain.MovieDetailsEntity
import kotlinx.coroutines.flow.Flow

interface MoviesDetailsDataSource {
    fun getMovieDetails(movieId: String): Flow<MovieDetailsEntity>
}
