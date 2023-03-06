package com.omdbifood.home.results.presentation.viewmodel

import com.omdbifood.core.results.domain.ResultEntity
import com.omdbifood.core.results.domain.ResultTypeEntity

internal object ResultsViewModelTestStubs {
    const val defaultInputStub = ""
    const val searchInputStub = "searchInputStub"
    const val searchPageStub = 1
    const val nextPageStub = 2
    private const val titleStub = "title"
    private const val yearStub = "year"
    private const val imdbIdStub= "imdbId"
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
