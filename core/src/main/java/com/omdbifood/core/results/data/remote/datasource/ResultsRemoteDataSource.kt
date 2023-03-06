package com.omdbifood.core.results.data.remote.datasource

import com.omdbifood.core.results.domain.ResultEntity
import kotlinx.coroutines.flow.Flow

interface ResultsRemoteDataSource {
    fun getResults(name: String, page: Int): Flow<List<ResultEntity>>
}
