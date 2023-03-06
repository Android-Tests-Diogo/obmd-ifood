package com.omdbifood.core.results.favorites.data.local

import com.omdbifood.common.mapper.Mapper
import com.omdbifood.core.results.domain.ResultEntity
import com.omdbifood.core.results.domain.ResultTypeEntity
import com.omdbifood.core.results.favorites.data.local.model.Result

class ResultListToResultEntityListMapper : Mapper<List<Result>, List<ResultEntity>> {

    override fun map(input: List<Result>): List<ResultEntity> =
        input.map { result ->
            ResultEntity(
                title = result.title,
                year = result.year,
                imdbId = result.imdbId,
                type = ResultTypeEntity.fromString(result.type),
                posterUrl = result.posterUrl,
                isFavorite = false
            )
        }
}
