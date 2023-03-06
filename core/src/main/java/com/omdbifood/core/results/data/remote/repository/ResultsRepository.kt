package com.omdbifood.core.results.data.remote.repository

import com.omdbifood.core.results.domain.ResultEntity
import kotlinx.coroutines.flow.Flow

interface ResultsRepository {
    fun getResults(input: String, page: Int): Flow<List<ResultEntity>>
}
