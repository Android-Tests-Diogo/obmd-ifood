package com.omdbifood.android.recyclerView

import androidx.recyclerview.widget.DiffUtil

class DefaultDiffUtil<T> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        areItemsTheSame(oldItem, newItem)
}
