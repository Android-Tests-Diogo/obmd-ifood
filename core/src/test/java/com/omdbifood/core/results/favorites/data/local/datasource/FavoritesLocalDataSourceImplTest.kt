package com.omdbifood.core.results.favorites.data.local.datasource

import app.cash.turbine.test
import com.omdbifood.common.database.FlowGenericResult
import com.omdbifood.core.results.favorites.data.local.ResultEntityToResultMapper
import com.omdbifood.core.results.favorites.data.local.ResultListToResultEntityListMapper
import com.omdbifood.core.results.favorites.data.local.dao.FavoritesDao
import com.omdbifood.core.results.favorites.data.local.datasource.FavoritesLocalDataSourceImplStubs.resultEntityStub
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class FavoritesLocalDataSourceImplTest {

    private val dao = mockk<FavoritesDao>()
    private val mapperToResult = ResultEntityToResultMapper()
    private val mapperToEntityList = ResultListToResultEntityListMapper()
    private val localDataSource = FavoritesLocalDataSourceImpl(
        dao = dao,
        mapperToResult = mapperToResult,
        mapperToEntityList = mapperToEntityList,
    )

    @Test
    fun `addFavorites should call dao to save Result on database`() = runTest {
        // Given
        every { dao.save(any()) } just runs

        // When
        localDataSource.addFavorite(resultEntityStub).test {
            // Then
            verify(exactly = 1) { dao.save(any()) }
            assertEquals(awaitItem(), FlowGenericResult.Successful)
            awaitComplete()
        }
    }

    @Test
    fun `removeFavorite should call dao to delete Result from database`() = runTest {
        // Given
        every { dao.delete(resultEntityStub.imdbId) } just runs

        // When
        localDataSource.removeFavorite(resultEntityStub.imdbId).test {
            // Then
            verify(exactly = 1) { dao.delete(any()) }
            assertEquals(awaitItem(), FlowGenericResult.Successful)
            awaitComplete()
        }
    }

    @Test
    fun `removeAllFavorites should call dao to delete all Results from database`() = runTest {
        // Given
        every { dao.deleteAll() } just runs

        // When
        localDataSource.removeAllFavorites().test {
            // Then
            assertEquals(awaitItem(), FlowGenericResult.Successful)
            awaitComplete()
            verify(exactly = 1) { dao.deleteAll() }
        }
    }
}
