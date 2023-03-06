package com.omdbifood.home.favorites.domain

import app.cash.turbine.test
import com.omdbifood.common.database.FlowGenericResult
import com.omdbifood.core.results.favorites.data.local.repository.FavoritesRepository
import com.omdbifood.home.favorites.domain.FavoritesUseCaseStubs.imdbIdStub
import com.omdbifood.home.favorites.domain.FavoritesUseCaseStubs.resultEntityStub
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class FavoritesUseCaseTest {

    private val favoritesRepositoryMock = mockk<FavoritesRepository>()
    private val favoritesUseCase = FavoritesUseCase(
        favoritesRepository = favoritesRepositoryMock
    )

    @Test
    fun `manageFavorite should call removeFavorite in FavoritesRepository`() = runTest {
        // Given
        every { favoritesRepositoryMock.getAllFavorites() } returns flowOf(listOf(resultEntityStub))
        every { favoritesRepositoryMock.removeFavorite(imdbIdStub) } returns flowOf(
            FlowGenericResult.Successful
        )

        // When
        favoritesUseCase.manageFavorite(imdbIdStub).test {
            // Then
            verify { favoritesRepositoryMock.removeFavorite(imdbIdStub) }
            awaitItem()
            awaitComplete()
        }
    }

    @Test
    fun `manageFavorite should call addFavorites in FavoritesRepository`() = runTest {
        // Given
        val imdbIdStubNotHasInList = "imdbIdStubNotHasInList"
        every { favoritesRepositoryMock.getAllFavorites() } returns flowOf(listOf(resultEntityStub))
        every { favoritesRepositoryMock.addFavorites(imdbIdStubNotHasInList) } returns flowOf(
            FlowGenericResult.Successful
        )

        // When
        favoritesUseCase.manageFavorite(imdbIdStubNotHasInList).test {
            // Then
            verify { favoritesRepositoryMock.addFavorites(imdbIdStubNotHasInList) }
            awaitItem()
            awaitComplete()
        }
    }

    @Test
    fun `getAllFavorites should call getAllFavorites in FavoritesRepository`() = runTest {
        // Given
        every { favoritesRepositoryMock.getAllFavorites() } returns flowOf(listOf(resultEntityStub))

        // When
        favoritesUseCase.getAllFavorites().test {
            // Then
            with(awaitItem()) {
                assertEquals(first(), resultEntityStub)
            }
            awaitComplete()
        }
    }
}
