package com.omdbifood.home.results.presentation.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ResultsFragmentPageStateAdapter(
    view: FragmentActivity,
    private val createPage: (position: Int) -> Fragment,
    private val pages: Int,
) : FragmentStateAdapter(view) {

    override fun getItemCount(): Int = pages

    override fun createFragment(position: Int): Fragment = createPage.invoke(position)
}
