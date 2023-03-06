package com.omdbifood.core.results.data.remote

import com.omdbifood.common.mapper.Mapper
import com.omdbifood.core.results.data.remote.model.ResultsResponse
import com.omdbifood.core.results.domain.ResultEntity
import com.omdbifood.core.results.domain.ResultTypeEntity

class ResultResponseToResultEntityMapper : Mapper<ResultsResponse, List<ResultEntity>> {

    override fun map(input: ResultsResponse): List<ResultEntity> =
        input.results.map { resultResponse ->
            ResultEntity(
                title = resultResponse.title,
                year = resultResponse.year,
                imdbId = resultResponse.imdbId,
                type = ResultTypeEntity.fromString(resultResponse.type),
                posterUrl = resultResponse.posterUrl,
                isFavorite = false
            )
        }
}
