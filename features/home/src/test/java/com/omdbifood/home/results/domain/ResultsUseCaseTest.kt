package com.omdbifood.home.results.domain

import app.cash.turbine.test
import com.omdbifood.core.results.data.remote.repository.ResultsRepository
import com.omdbifood.home.results.domain.ResultsUseCaseStubs.movieNameStub
import com.omdbifood.home.results.domain.ResultsUseCaseStubs.pageStub
import com.omdbifood.home.results.domain.ResultsUseCaseStubs.resultEntityStub
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class ResultsUseCaseTest {

    private val resultsRepository = mockk<ResultsRepository>()
    private val resultsUseCase = ResultsUseCase(
        resultsRepository = resultsRepository
    )

    @Test
    fun `resultsUseCase should get results from ResultsRepository`() = runTest {
        // Given
        every { resultsRepository.getResults(movieNameStub, pageStub) } returns flowOf(
            listOf(resultEntityStub)
        )

        // When
        resultsUseCase(movieNameStub, pageStub).test {
            // Then
            with(awaitItem()) {
                assertEquals(first(), resultEntityStub)
            }

            awaitComplete()
        }
    }
}
