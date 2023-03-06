package com.omdbifood.home.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.omdbifood.android.recyclerView.BaseListAdapter
import com.omdbifood.android.recyclerView.BaseViewHolder
import com.omdbifood.android.viewbinding.viewBinding
import com.omdbifood.home.R
import com.omdbifood.home.databinding.ResultsItemLayoutBinding

class ResultsAdapter(private val manageFavorites: (String) -> Unit) :
    BaseListAdapter<ResultItem, ResultsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            manageFavorites,
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.results_item_layout, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val manageFavorites: (String) -> Unit, itemView: View) :
        BaseViewHolder<ResultItem>(itemView) {

        override val binding: ResultsItemLayoutBinding by viewBinding()

        override fun bind(item: ResultItem) {
            with(binding) {
                tvTitle.text = item.title
                btnFavorites.setOnClickListener {
                    manageFavorites.invoke(item.movieId)
                }
                ivFavorites.isVisible = item.favoriteVisibility
            }
        }
    }
}
