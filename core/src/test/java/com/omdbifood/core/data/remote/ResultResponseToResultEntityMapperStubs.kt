package com.omdbifood.core.data.remote

import com.omdbifood.core.results.data.remote.model.ResultResponse
import com.omdbifood.core.results.data.remote.model.ResultsSuccessResponse

internal object ResultResponseToResultEntityMapperStubs {
    const val resultTitleStub = "resultTitleStub"
    const val resultYearStub = "resultYearStub"
    const val resultImdbIdStub = "resultImdbIdStub"
    const val resultTypeStub = "movie"
    const val resultPosterUrlStub = "resultPosterUrlStub"
    val resultsResponseStub = ResultsSuccessResponse(
        results = listOf(
            ResultResponse(
                title = resultTitleStub,
                year = resultYearStub,
                imdbId = resultImdbIdStub,
                type = resultTypeStub,
                posterUrl = resultPosterUrlStub,
            )
        ),
        response = true
    )
}
