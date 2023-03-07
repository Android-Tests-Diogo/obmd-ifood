package com.omdbifood.ui.components.infiniterecyclerview

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.omdbifood.android.recyclerView.BaseViewHolder
import com.omdbifood.ui.R

class InfiniteRecyclerView<I, VH : BaseViewHolder<I>> : FrameLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private lateinit var rv: RecyclerView
    private var isInitialized = false
    private var infiniteHandler: InfiniteRecyclerViewHandler<I, VH>? = null

    init {
        inflate(context, R.layout.ds_infinite_recycler_view, this)
    }

    fun initialize(
        adapter: ListAdapter<I, VH>,
        layoutManager: LinearLayoutManager,
        onNextPage: () -> Unit
    ) {
        if (!isInitialized) {
            rv = findViewById(R.id.rvItems)
            rv.adapter = adapter
            rv.layoutManager = layoutManager
            infiniteHandler = InfiniteRecyclerViewHandler(
                adapter,
                rv.layoutManager as LinearLayoutManager,
                onNextPage
            )

            rv.addOnScrollListener(
                infiniteHandler!!
            )

            isInitialized = true
        }
    }

    fun fetchDataFinished() {
        if (isInitialized) {
            infiniteHandler?.fetchDataFinished()
        } else {
            throw InfiniteRecyclerViewExceptions.NotInitializedException()
        }
    }

    fun submitItems(items: List<I>) {
        if (isInitialized) {
            (rv.adapter as ListAdapter<I, VH>).submitList(items)
        } else {
            throw InfiniteRecyclerViewExceptions.NotInitializedException()
        }
    }

    sealed class InfiniteRecyclerViewExceptions : Exception() {

        class NotInitializedException : InfiniteRecyclerViewExceptions() {
            override val message: String =
                "InfiniteRecyclerView not initialize, make sure you called initialize()"
        }
    }
}
