package com.omdbifood.core.results.favorites.data.local

import com.omdbifood.core.results.domain.ResultTypeEntity
import com.omdbifood.core.results.favorites.data.local.model.Result

internal object ResultListToResultEntityListMapperStubs {

    const val titleStub = "titleStub"
    const val yearStub = "yearStub"
    const val imdbIdStub = "imdbIdStub"
    val typeStub = ResultTypeEntity.MOVIE.typeStr
    const val posterUrlStub = "posterUrlStub"

    val favoritesResultStubs = listOf(
        Result(
            title = titleStub,
            year = yearStub,
            imdbId = imdbIdStub,
            type = typeStub,
            posterUrl = posterUrlStub
        )
    )
}
