package com.omdbifood.android.viewmodel.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.omdbifood.android.viewmodel.UIAction
import com.omdbifood.android.viewmodel.UIState
import com.omdbifood.android.viewmodel.ViewModel

inline fun <reified State : UIState, reified Action : UIAction> AppCompatActivity.onSendState(
    viewModel: ViewModel<State, Action>,
    crossinline handleState: (State) -> Unit
) {
    viewModel.state.observe(this) { state -> handleState(state as State) }
}

inline fun <reified State : UIState, reified Action : UIAction> AppCompatActivity.onSendAction(
    viewModel: ViewModel<State, Action>,
    crossinline handleState: (Action) -> Unit
) {
    viewModel.action.observe(this) { action -> handleState(action as Action) }
}

inline fun <reified State : UIState, reified Action : UIAction> Fragment.onSendState(
    viewModel: ViewModel<State, Action>,
    crossinline handleState: (State) -> Unit
) {
    viewModel.state.observe(viewLifecycleOwner) { state -> handleState(state as State) }
}

inline fun <reified State : UIState, reified Action : UIAction> Fragment.onSendAction(
    viewModel: ViewModel<State, Action>,
    crossinline handleState: (Action) -> Unit
) {
    viewModel.action.observe(viewLifecycleOwner) { action -> handleState(action as Action) }
}
