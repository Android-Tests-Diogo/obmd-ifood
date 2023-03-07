package com.omdbifood.core.results.data.remote.service

import com.omdbifood.core.results.data.remote.model.ResultsResponse
import com.omdbifood.core.results.data.remote.model.ResultsSuccessResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ResultsService {
    @GET(".")
    suspend fun fetchMovies(
        @Query("s") movieName: String,
        @Query("page") page: Int
    ): ResultsResponse
}
