package com.omdbifood.android.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<I>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    protected abstract val binding: ViewBinding

    abstract fun bind(item: I)
}
