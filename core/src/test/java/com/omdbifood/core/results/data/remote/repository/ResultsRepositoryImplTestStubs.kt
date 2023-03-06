package com.omdbifood.core.results.data.remote.repository

import com.omdbifood.core.results.domain.ResultEntity
import com.omdbifood.core.results.domain.ResultTypeEntity

internal object ResultsRepositoryImplTestStubs {
    const val movieNameStub = "movieNameStub"
    const val pageStub = 1
    const val nextPageStub = 2
    const val titleStub = "title"
    const val yearStub = "year"
    const val imdbIdStub= "imdbId"
    val typeStub = ResultTypeEntity.MOVIE
    const val posterUrlStub = "posterUrl"
    val resultResultEntityStub = listOf(
        ResultEntity(
            title = titleStub,
            year = yearStub,
            imdbId = imdbIdStub,
            type = ResultTypeEntity.MOVIE,
            posterUrl = posterUrlStub,
            isFavorite = false
        )
    )
}
