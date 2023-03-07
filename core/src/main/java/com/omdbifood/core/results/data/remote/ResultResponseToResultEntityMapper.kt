package com.omdbifood.core.results.data.remote

import com.omdbifood.common.mapper.Mapper
import com.omdbifood.core.results.data.remote.model.ResultsSuccessResponse
import com.omdbifood.core.results.domain.ResultEntity
import com.omdbifood.core.results.domain.ResultTypeEntity

class ResultResponseToResultEntityMapper : Mapper<ResultsSuccessResponse, List<ResultEntity>> {

    override fun map(input: ResultsSuccessResponse): List<ResultEntity> =
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
