package com.omdbifood.home.results.domain

import com.omdbifood.core.results.data.remote.repository.ResultsRepository
import com.omdbifood.core.results.domain.ResultEntity
import kotlinx.coroutines.flow.Flow

class ResultsUseCase(private val resultsRepository: ResultsRepository) {

    operator fun invoke(name: String, page: Int): Flow<List<ResultEntity>> =
        resultsRepository.getResults(name, page)
}
