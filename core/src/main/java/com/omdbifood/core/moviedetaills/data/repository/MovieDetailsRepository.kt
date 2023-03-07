package com.omdbifood.core.moviedetaills.data.repository

import com.omdbifood.core.moviedetaills.domain.MovieDetailsEntity
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    fun getMovieDetails(movieId: String): Flow<MovieDetailsEntity>
}
