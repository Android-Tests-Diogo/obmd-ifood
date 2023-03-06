package com.omdbifood.ui.components.infiniterecyclerview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omdbifood.android.recyclerView.BaseListAdapter
import com.omdbifood.android.recyclerView.BaseViewHolder

const val ITEMS_PER_PAGE = 10

internal class InfiniteRecyclerViewHandler<I , VH : BaseViewHolder<I>>(
    private val adapter: BaseListAdapter<I, VH>,
    private val layoutManager: LinearLayoutManager,
    private val reachedLastItemCallback: () -> Unit
) : RecyclerView.OnScrollListener() {

    private var isLoadingNewItems = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val visibleItemCount = layoutManager.childCount
        val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
        val total = adapter.itemCount

        if (!isLoadingNewItems) {
            if ((visibleItemCount + pastVisibleItem) >= total && total >= ITEMS_PER_PAGE) {
                isLoadingNewItems = true
                reachedLastItemCallback.invoke()
            }
        }

        super.onScrolled(recyclerView, dx, dy)
    }

    fun fetchDataFinished() {
        isLoadingNewItems = false
    }
}
