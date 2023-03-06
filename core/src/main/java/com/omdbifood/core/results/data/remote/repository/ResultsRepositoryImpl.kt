package com.omdbifood.core.results.data.remote.repository

import com.omdbifood.core.results.data.local.ResultsTemporaryDataSource
import com.omdbifood.core.results.data.remote.datasource.ResultsRemoteDataSource
import com.omdbifood.core.results.domain.ResultEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

const val DEFAULT_INPUT: String = ""
const val DEFAULT_PAGE: Int = 1

class ResultsRepositoryImpl(
    private val remoteDataSource: ResultsRemoteDataSource,
    private val temporaryDataSource: ResultsTemporaryDataSource
) : ResultsRepository {

    private var lastInput: String = DEFAULT_INPUT
    private var lastPage: Int = DEFAULT_PAGE

    init {
        temporaryDataSource.renewTemporaryResults(listOf())
    }

    override fun getResults(input: String, page: Int): Flow<List<ResultEntity>> =
        flow {
            when {
                isSearch(input, page) -> {
                    remoteDataSource.getResults(input, page).collect {
                        temporaryDataSource.renewTemporaryResults(it)
                    }
                }
                isSearchingMoreResults(input, page) -> {
                    remoteDataSource.getResults(input, page).collect {
                        temporaryDataSource.addMoreTemporaryResults(it)
                    }
                }
            }

            emit(temporaryDataSource.getTemporaryResults())
        }

    private fun isSearch(input: String, page: Int): Boolean {
        if (lastInput != input) {
            lastInput = input
            lastPage = page
            return true
        }

        return false
    }

    private fun isSearchingMoreResults(input: String, page: Int): Boolean {
        if (lastInput == input && lastPage != page) {
            lastPage = page

            return true
        }

        return false
    }
}
