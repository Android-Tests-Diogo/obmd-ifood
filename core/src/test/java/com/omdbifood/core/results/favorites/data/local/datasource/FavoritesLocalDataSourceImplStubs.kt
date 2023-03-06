package com.omdbifood.core.results.favorites.data.local.datasource

import com.omdbifood.core.results.domain.ResultEntity
import com.omdbifood.core.results.domain.ResultTypeEntity

internal object FavoritesLocalDataSourceImplStubs {
    const val titleStub = "titleStub"
    const val yearStub = "yearStub"
    const val imdbIdStub = "imdbIdStub"
    val typeStub = ResultTypeEntity.MOVIE
    const val posterUrlStub = "posterUrlStub"

    val resultEntityStub = ResultEntity(
        title = titleStub,
        year = yearStub,
        imdbId = imdbIdStub,
        type = typeStub,
        posterUrl = posterUrlStub,
        isFavorite = true
    )
}
