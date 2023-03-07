package com.omdbifood.home.favorites.presentation.view

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omdbifood.android.koin.KoinFragment
import com.omdbifood.android.viewbinding.viewBinding
import com.omdbifood.android.viewmodel.extensions.onSendAction
import com.omdbifood.android.viewmodel.extensions.onSendState
import com.omdbifood.home.R
import com.omdbifood.home.databinding.HomeFavoritesFragmentBinding
import com.omdbifood.home.favorites.presentation.viewmodel.FavoriteUIState
import com.omdbifood.home.favorites.presentation.viewmodel.FavoritesUIAction
import com.omdbifood.home.favorites.presentation.viewmodel.FavoritesViewModel
import com.omdbifood.home.presentation.view.ResultItem
import com.omdbifood.home.presentation.view.ResultsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment(
    private val favoritesChange: () -> Unit
) : KoinFragment(R.layout.home_favorites_fragment) {

    private val viewModel by viewModel<FavoritesViewModel>()
    private val binding by viewBinding<HomeFavoritesFragmentBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        initializeRecyclerView()
        viewModel.getFavorites()
    }

    private fun initializeRecyclerView() {
        binding.rvMovies.layoutManager = LinearLayoutManager(context)
        binding.rvMovies.adapter = ResultsAdapter { movieId ->
            viewModel.onFavoritesManagerClicked(movieId)
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

    private fun onStateUpdate(state: FavoriteUIState) {
        binding.rvMovies.updateItems(state.results)
        binding.tvNoFavoritesFound.isVisible = state.noFavoritesFoundTextVisibility
    }

    private fun RecyclerView.updateItems(items: List<ResultItem>) {
        (adapter as ResultsAdapter).submitList(items)
    }

    private fun onActionTrigger(action: FavoritesUIAction) {
        when(action) {
            FavoritesUIAction.FavoritesChanged -> favoritesChange.invoke()
        }
    }

    fun syncData() {
        viewModel.getFavorites()
    }
}
