package com.omdbifood.core.results.data.local

import com.omdbifood.core.results.domain.ResultEntity
import com.omdbifood.core.results.domain.ResultTypeEntity

internal object ResultsTemporaryDataSourceImplStubs {
    private const val titleStub = "title"
    private const val yearStub = "year"
    const val imdbIdStub= "imdbId"
    private const val posterUrlStub = "posterUrl"
    val temporaryResultsEntity1Stub = ResultEntity(
        title = titleStub,
        year = yearStub,
        imdbId = imdbIdStub,
        type = ResultTypeEntity.MOVIE,
        posterUrl = posterUrlStub,
        isFavorite = false
    )
    private const val title2Stub2 = "title2Stub2"
    private const val year2Stub = "year2Stub"
    private const val imdbId2Stub= "imdbId2Stub"
    private val type2Stub = ResultTypeEntity.SERIES
    private const val posterUrl2Stub = "posterUrl2Stub"
    val temporaryResultsEntity2Stub = ResultEntity(
        title = title2Stub2,
        year = year2Stub,
        imdbId = imdbId2Stub,
        type = type2Stub,
        posterUrl = posterUrl2Stub,
        isFavorite = false
    )
}
