package com.omdbifood.core.results.data.remote.datasource

import com.omdbifood.core.results.data.remote.model.ResultResponse
import com.omdbifood.core.results.data.remote.model.ResultsSuccessResponse

internal object ResultsRemoteDataSourceImplStubs {
    const val invalidCharStubStub = "ma"
    const val movieNameStub = "movieNameStub"
    const val pageStub = 1
    const val titleStub = "title"
    const val yearStub = "year"
    const val imdbIdStub= "imdbId"
    const val typeStub = "movie"
    const val posterUrlStub = "posterUrl"
    val resultResponseStub = ResultsSuccessResponse(
        results = listOf(
            ResultResponse(
                title = titleStub,
                year = yearStub,
                imdbId = imdbIdStub,
                type = typeStub,
                posterUrl = posterUrlStub
            )
        ),
        response = true
    )
}
