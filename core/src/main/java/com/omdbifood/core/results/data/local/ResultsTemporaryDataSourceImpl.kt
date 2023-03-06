package com.omdbifood.core.results.data.local

import com.omdbifood.core.results.domain.ResultEntity

class ResultsTemporaryDataSourceImpl : ResultsTemporaryDataSource {

    private val temporaryResults: MutableList<ResultEntity> = mutableListOf()

    override fun getResultToAddFavorites(movieId: String): ResultEntity =
        temporaryResults.first {
            movieId == it.imdbId
        }

    override fun addMoreTemporaryResults(results: List<ResultEntity>) {
        temporaryResults.addAll(results)
    }

    override fun renewTemporaryResults(results: List<ResultEntity>) {
        temporaryResults.clear()
        temporaryResults.addAll(results)
    }

    override fun getTemporaryResults(): List<ResultEntity> =
        temporaryResults
}
