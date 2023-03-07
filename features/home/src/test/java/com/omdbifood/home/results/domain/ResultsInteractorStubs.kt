package com.omdbifood.home.results.domain

import com.omdbifood.core.results.domain.ResultEntity
import com.omdbifood.core.results.domain.ResultTypeEntity

internal object ResultsInteractorStubs {
    const val movieIdStub = "movieIdStub"
    const val pageStub = 1
    private const val titleStub = "title"
    private const val yearStub = "year"
    private const val imdbIdStub = "imdbId"
    private const val posterUrlStub = "posterUrl"
    val resultEntityStub = ResultEntity(
        title = titleStub,
        year = yearStub,
        imdbId = imdbIdStub,
        type = ResultTypeEntity.MOVIE,
        posterUrl = posterUrlStub,
        isFavorite = false
    )
}
