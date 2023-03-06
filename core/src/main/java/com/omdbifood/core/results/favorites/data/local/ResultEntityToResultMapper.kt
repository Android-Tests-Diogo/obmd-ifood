package com.omdbifood.core.results.favorites.data.local

import com.omdbifood.common.mapper.Mapper
import com.omdbifood.core.results.domain.ResultEntity
import com.omdbifood.core.results.favorites.data.local.model.Result

class ResultEntityToResultMapper : Mapper<ResultEntity, Result> {
    override fun map(input: ResultEntity): Result =
        Result(
            title = input.title,
            year = input.year,
            imdbId = input.imdbId,
            type = input.type.typeStr,
            posterUrl = input.posterUrl
        )
}
