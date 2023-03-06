package com.omdbifood.home.results.domain

import app.cash.turbine.test
import com.omdbifood.home.favorites.domain.FavoritesUseCase
import com.omdbifood.home.results.domain.ResultsInteractorStubs.movieIdStub
import com.omdbifood.home.results.domain.ResultsInteractorStubs.pageStub
import com.omdbifood.home.results.domain.ResultsInteractorStubs.resultEntityStub
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class ResultsInteractorTest {

    private val favoritesUseCaseMock = mockk<FavoritesUseCase>()
    private val resultsUseCaseMock = mockk<ResultsUseCase>()
    private val resultsInteractor = ResultsInteractor(
        favoritesUseCase = favoritesUseCaseMock,
        resultsUseCase = resultsUseCaseMock
    )

    @Test
    fun `manageFavorite should call manageFavorite in FavoritesUseCase`() = runTest {
        // Given
        every { favoritesUseCaseMock.manageFavorite(movieIdStub) } returns flowOf(mockk())

        // When
        resultsInteractor.manageFavorite(movieIdStub).test {
            // Then
            awaitItem()
            awaitComplete()
            verify { favoritesUseCaseMock.manageFavorite(movieIdStub) }
        }
    }

    @Test
    fun `getResults should recreate ResultsEntity list updating isFavorite field`() =
        runTest {
            // Given
            every { favoritesUseCaseMock.getAllFavorites() } returns flowOf(listOf(resultEntityStub))
            every { resultsUseCaseMock(movieIdStub, pageStub) } returns flowOf(
                listOf(
                    resultEntityStub
                )
            )

            // When
            resultsInteractor.getResults(movieIdStub, pageStub).test {
                // Then
                with(awaitItem()) {
                    assertTrue(size == 1)
                    assertTrue(first().isFavorite)
                }

                awaitComplete()
            }
        }
}
