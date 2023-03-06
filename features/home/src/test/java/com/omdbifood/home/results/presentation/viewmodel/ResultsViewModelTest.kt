package com.omdbifood.home.results.presentation.viewmodel

import androidx.lifecycle.Observer
import com.omdbifood.android.CoroutineTestRule
import com.omdbifood.android.LocalTestRule
import com.omdbifood.android.ViewModelTestRule
import com.omdbifood.android.extensions.second
import com.omdbifood.android.extensions.third
import com.omdbifood.android.resources.ResourceProvider
import com.omdbifood.common.database.FlowGenericResult
import com.omdbifood.core.results.data.remote.exceptions.ResultsExceptions
import com.omdbifood.home.favorites.presentation.viewmodel.FavoritesViewModelStubs.imdbIdStub
import com.omdbifood.home.results.domain.ResultsInteractor
import com.omdbifood.home.results.presentation.viewmodel.ResultsViewModelTestStubs.defaultInputStub
import com.omdbifood.home.results.presentation.viewmodel.ResultsViewModelTestStubs.nextPageStub
import com.omdbifood.home.results.presentation.viewmodel.ResultsViewModelTestStubs.searchPageStub
import com.omdbifood.home.results.presentation.viewmodel.ResultsViewModelTestStubs.resultEntityStub
import com.omdbifood.home.results.presentation.viewmodel.ResultsViewModelTestStubs.searchInputStub
import com.omdbifood.home.R
import com.omdbifood.home.results.presentation.viewmodel.ResultsViewModelTestStubs.toastGenericMessageStub
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class ResultsViewModelTest {

    private val resourceProviderMock = mockk<ResourceProvider>()
    private val resultsInteractorMock = mockk<ResultsInteractor>()
    private lateinit var resultsViewModel: ResultsViewModel

    @get:Rule
    val localTestRule = LocalTestRule()

    @get:Rule
    val viewModelRule = ViewModelTestRule()

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private val stateObserver: Observer<ResultsUIState> by lazy { viewModelRule.getStateObserver() }
    private val actionObserver: Observer<ResultsUIAction> by lazy { viewModelRule.getActionObserver() }
    private val stateObserverSlot = slot<ResultsUIState>()
    private var statesCaptured: MutableList<ResultsUIState> = mutableListOf()
    private val actionObserverSlot = slot<ResultsUIAction>()
    private val actionsCaptured: MutableList<ResultsUIAction> = mutableListOf()

    @Before
    fun setUp() {
        resultsViewModel = ResultsViewModel(
            resultsInteractor = resultsInteractorMock,
            resourceProvider = resourceProviderMock
        )

        every { stateObserver.onChanged(capture(stateObserverSlot)) } answers {
            statesCaptured.add(stateObserverSlot.captured)
        }

        every { actionObserver.onChanged(capture(actionObserverSlot)) } answers {
            actionsCaptured.add(actionObserverSlot.captured)
        }
    }

    @Test
    fun `syncData should get Results with stored page and last input and send actions`() {
        // Given
        val resultListStub = listOf(resultEntityStub.copy(isFavorite = true))
        val resultStub = resultListStub.first()
        every { resultsInteractorMock.getResults(defaultInputStub, searchPageStub) } returns flowOf(
            resultListStub
        )

        // When
        resultsViewModel.syncData()

        // Then
        assertTrue(statesCaptured.first().searchLoadingVisibility)
        with(statesCaptured.second()) {
            assertFalse(searchLoadingVisibility)
            assertTrue(resultListStub.size == results.size)
            with(results.first()) {
                assertEquals(resultStub.imdbId, movieId)
                assertEquals(resultStub.title, title)
                assertEquals(resultStub.isFavorite, favoriteVisibility)
            }
        }
        assertTrue(actionsCaptured.first() is ResultsUIAction.SearchAllowed)
        assertFalse((actionsCaptured.second() as ResultsUIAction.FetchingDataStatus).isFetchingData)
        assertTrue(actionsCaptured.third() is ResultsUIAction.SearchAllowed)
    }

    @Test
    fun `getResults should get Results with input page and input and send actions`() {
        // Given
        every { resultsInteractorMock.getResults(searchInputStub, searchPageStub) } returns flowOf(
            listOf(resultEntityStub)
        )

        // When
        resultsViewModel.getResults(searchInputStub)

        // Then
        verify { resultsInteractorMock.getResults(searchInputStub, searchPageStub) }
    }

    @Test
    fun `onFavoritesManagerClicked should call manageFavorite and fetchResults again`() {
        // Given
        every { resultsInteractorMock.getResults(defaultInputStub, searchPageStub) } returns flowOf(
            listOf(resultEntityStub)
        )
        every { resultsInteractorMock.manageFavorite(imdbIdStub) } returns flowOf(
            FlowGenericResult.Successful
        )

        // When
        resultsViewModel.onFavoritesManagerClicked(imdbIdStub)

        // Then
        assertTrue(actionsCaptured.first() is ResultsUIAction.FavoritesChanged)
        verify { resultsInteractorMock.getResults(defaultInputStub, searchPageStub) }
    }

    @Test
    fun `onLastResultReached should increase page number and fetchResults again`() {
        // Given
        every { resultsInteractorMock.getResults(defaultInputStub, nextPageStub) } returns flowOf(
            listOf(resultEntityStub)
        )

        // When
        resultsViewModel.onLastResultReached()

        // Then
        assertFalse(statesCaptured.first().searchLoadingVisibility)
        assertTrue((actionsCaptured.first() as ResultsUIAction.FetchingDataStatus).isFetchingData)
        verify { resultsInteractorMock.getResults(defaultInputStub, nextPageStub) }
    }

    @Test
    fun `getResults should send ShowToast action when throw NoResultsFound exception`() {
        // Given
        every { resultsInteractorMock.getResults(searchInputStub, searchPageStub) } returns flow {
            throw ResultsExceptions.NoResultsFound
        }
        every {
            resourceProviderMock.getString(R.string.home_movie_not_found)
        } returns toastGenericMessageStub

        // When
        resultsViewModel.getResults(searchInputStub)

        // Then
        assertFalse(statesCaptured.last().searchLoadingVisibility)
        assertEquals(
            (actionsCaptured.last() as ResultsUIAction.ShowToast).message,
            toastGenericMessageStub
        )
    }

    @Test
    fun `getResults should send ShowToast action when throw MinCharsAccepted exception`() {
        // Given
        every { resultsInteractorMock.getResults(searchInputStub, searchPageStub) } returns flow {
            throw ResultsExceptions.MinCharsAccepted
        }
        every {
            resourceProviderMock.getString(R.string.home_insert_more_than_three_chars)
        } returns toastGenericMessageStub

        // When
        resultsViewModel.getResults(searchInputStub)

        // Then
        assertFalse(statesCaptured.last().searchLoadingVisibility)
        assertEquals(
            (actionsCaptured.last() as ResultsUIAction.ShowToast).message,
            toastGenericMessageStub
        )
    }

    @Test
    fun `getResults should send ShowToast action when throw Any exception`() {
        // Given
        every { resultsInteractorMock.getResults(searchInputStub, searchPageStub) } returns flow {
            throw ResultsViewModelTestStubs.TestException
        }
        every {
            resourceProviderMock.getString(R.string.home_movie_error_message)
        } returns toastGenericMessageStub

        // When
        resultsViewModel.getResults(searchInputStub)

        // Then
        assertFalse(statesCaptured.last().searchLoadingVisibility)
        assertEquals(
            (actionsCaptured.last() as ResultsUIAction.ShowToast).message,
            toastGenericMessageStub
        )
    }

    @After
    fun after() {
        statesCaptured.clear()
        actionsCaptured.clear()
    }
}
