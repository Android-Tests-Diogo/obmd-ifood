package com.omdbifood.core.results.favorites.data.local.repository

import com.omdbifood.core.results.domain.ResultEntity
import com.omdbifood.core.results.domain.ResultTypeEntity

object FavoritesRepositoryImplStubs {
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
