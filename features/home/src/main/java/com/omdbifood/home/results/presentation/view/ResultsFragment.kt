package com.omdbifood.home.results.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.omdbifood.android.viewbinding.viewBinding
import com.omdbifood.android.koin.KoinFragment
import com.omdbifood.android.viewmodel.extensions.onSendAction
import com.omdbifood.android.viewmodel.extensions.onSendState
import com.omdbifood.home.R
import com.omdbifood.home.databinding.ResultsFragmentBinding
import com.omdbifood.home.presentation.view.ResultsAdapter
import com.omdbifood.home.results.presentation.viewmodel.ResultsUIAction
import com.omdbifood.home.results.presentation.viewmodel.ResultsUIState
import com.omdbifood.home.results.presentation.viewmodel.ResultsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResultsFragment(
    private val fetchDataStatus: (Boolean) -> Unit,
    private val favoritesChange: () -> Unit
) : KoinFragment(R.layout.results_fragment) {

    private val binding by viewBinding<ResultsFragmentBinding>()
    private val viewModel by viewModel<ResultsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        initializeRecyclerView()

        binding.etSearch.onUserStopType = { input ->
            viewModel.getResults(input)
        }
    }

    private fun initializeRecyclerView() {
        binding.irvMovies.initialize(
            ResultsAdapter { movieId ->
                viewModel.onFavoritesManagerClicked(movieId)
            },
            LinearLayoutManager(context)
        ) {
            viewModel.onLastResultReached()
        }
    }

    private fun setupObservers() {
        onSendState(viewModel) {
            onStateUpdate(it)
        }
        onSendAction(viewModel) {
            onActionTrigger(it)
        }
    }

    private fun onStateUpdate(state: ResultsUIState) {
        binding.irvMovies.submitItems(state.results)
        binding.pbSearch.isVisible = state.searchLoadingVisibility
    }

    private fun onActionTrigger(action: ResultsUIAction) {
        when (action) {
            is ResultsUIAction.SearchAllowed -> binding.irvMovies.fetchDataFinished()
            is ResultsUIAction.ShowToast -> Toast.makeText(
                context,
                action.message,
                Toast.LENGTH_LONG
            ).show()
            is ResultsUIAction.FetchingDataStatus -> fetchDataStatus.invoke(action.isFetchingData)
            is ResultsUIAction.FavoritesChanged -> favoritesChange.invoke()
        }
    }

    fun syncData() {
        viewModel.syncData()
    }
}
