package com.omdbifood.home.favorites.presentation.viewmodel

import com.omdbifood.core.results.domain.ResultEntity
import com.omdbifood.core.results.domain.ResultTypeEntity

internal object FavoritesViewModelStubs {
    const val titleStub = "title"
    private const val yearStub = "year"
    const val imdbIdStub = "imdbId"
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
