package com.omdbifood.core.moviedetaills.data.remote.service

import com.omdbifood.core.moviedetaills.data.remote.model.MovieDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDetailsService {
    @GET
    suspend fun fetchMovieDetails(@Query("i") movieId: String): MovieDetailsResponse
}
