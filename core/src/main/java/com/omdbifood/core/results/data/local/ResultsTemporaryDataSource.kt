package com.omdbifood.core.results.data.local

import com.omdbifood.core.results.domain.ResultEntity

interface ResultsTemporaryDataSource {
    fun getResultToAddFavorites(movieId: String): ResultEntity
    fun addMoreTemporaryResults(results: List<ResultEntity>)
    fun renewTemporaryResults(results: List<ResultEntity>)
    fun getTemporaryResults(): List<ResultEntity>
}
