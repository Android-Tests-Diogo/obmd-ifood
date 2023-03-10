package com.omdbifood.core.results.data.remote.datasource

import com.omdbifood.common.mapper.Mapper
import com.omdbifood.core.results.data.remote.exceptions.ResultsExceptions
import com.omdbifood.core.results.data.remote.model.ResultsErrorResponse
import com.omdbifood.core.results.data.remote.model.ResultsSuccessResponse
import com.omdbifood.core.results.data.remote.service.ResultsService
import com.omdbifood.core.results.domain.ResultEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

const val MIN_INPUT_ACCEPTABLE_COUNT = 3

class ResultsRemoteDataSourceImpl(
    private val service: ResultsService,
    private val mapper: Mapper<ResultsSuccessResponse, List<ResultEntity>>
) : ResultsRemoteDataSource {

    override fun getResults(name: String, page: Int): Flow<List<ResultEntity>> =
        flow {
            if (isInvalidInput(name)) {
                throw ResultsExceptions.MinCharsAccepted
            } else {
                val result = service.fetchMovies(name, page)

                if (result is ResultsErrorResponse) {
                    throw ResultsExceptions.NoResultsFound
                } else {
                    emit(mapper.map(result as ResultsSuccessResponse))
                }
            }
        }

    private fun isInvalidInput(input: String): Boolean = input.length < MIN_INPUT_ACCEPTABLE_COUNT
}
