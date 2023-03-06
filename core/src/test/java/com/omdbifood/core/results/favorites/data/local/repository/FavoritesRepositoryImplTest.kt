package com.omdbifood.core.results.favorites.data.local.repository

import app.cash.turbine.test
import com.omdbifood.common.database.FlowGenericResult
import com.omdbifood.core.results.data.local.ResultsTemporaryDataSource
import com.omdbifood.core.results.favorites.data.local.datasource.FavoritesLocalDataSource
import com.omdbifood.core.results.favorites.data.local.repository.FavoritesRepositoryImplStubs.imdbIdStub
import com.omdbifood.core.results.favorites.data.local.repository.FavoritesRepositoryImplStubs.resultEntityStub
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyOrder
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class FavoritesRepositoryImplTest {

    private val favoritesLocalDataSourceMock = mockk<FavoritesLocalDataSource>()
    private val temporaryResultsDataSourceMock = mockk<ResultsTemporaryDataSource>()
    private val favoritesRepository = FavoritesRepositoryImpl(
        favoritesLocalDataSourceMock = favoritesLocalDataSourceMock,
        temporaryResultsDataSourceMock = temporaryResultsDataSourceMock
    )

    @Test
    fun `addFavorites should call getResultToAddFavorites and addFavorite`() = runTest {
        // Given
        every { favoritesLocalDataSourceMock.addFavorite(resultEntityStub) } returns flowOf(
            FlowGenericResult.Successful
        )
        every {
            temporaryResultsDataSourceMock.getResultToAddFavorites(imdbIdStub)
        } returns resultEntityStub

        // When
        favoritesRepository.addFavorites(imdbIdStub).test {
            // Then
            verifyOrder {
                temporaryResultsDataSourceMock.getResultToAddFavorites(imdbIdStub)
                favoritesLocalDataSourceMock.addFavorite(resultEntityStub)
            }
            assertEquals(awaitItem(), FlowGenericResult.Successful)
            awaitComplete()
        }
    }

    @Test
    fun `removeFavorite should call removeFavorite from FavoritesLocalDataSource`() = runTest {
        // Given
        every { favoritesLocalDataSourceMock.removeFavorite(imdbIdStub) } returns flowOf(
            FlowGenericResult.Successful
        )

        // When
        favoritesRepository.removeFavorite(imdbIdStub).test {
            // Then
            assertEquals(awaitItem(), FlowGenericResult.Successful)
            awaitComplete()
        }
    }

    @Test
    fun `getAllFavorites should call getAllFavorites from FavoritesLocalDataSource`() = runTest {
        // Given
        every { favoritesLocalDataSourceMock.getAllFavorites() } returns flowOf(
            listOf(
                resultEntityStub
            )
        )

        // When
        favoritesRepository.getAllFavorites().test {
            // Then
            with(awaitItem()) {
                assertEquals(first(), resultEntityStub)
            }

            awaitComplete()
        }
    }

    @Test
    fun `removeAllFavorites should call getAllFavorites from FavoritesLocalDataSource`() = runTest {
        // Given
        every { favoritesLocalDataSourceMock.removeAllFavorites() } returns flowOf(
            FlowGenericResult.Successful
        )

        // When
        favoritesRepository.removeAllFavorites().test {
            // Then
            with(awaitItem()) {
                assertTrue(this is FlowGenericResult.Successful)
            }

            awaitComplete()
        }
    }
}
