package com.omdbifood.android.recyclerView

import androidx.recyclerview.widget.ListAdapter

abstract class BaseListAdapter<I, VH : BaseViewHolder<I>> : ListAdapter<I, VH>(DefaultDiffUtil<I>())
