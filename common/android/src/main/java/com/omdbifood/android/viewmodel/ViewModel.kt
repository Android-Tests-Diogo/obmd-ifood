package com.omdbifood.android.viewmodel

import androidx.lifecycle.LiveData

abstract class ViewModel<State : UIState, Action : UIAction>(
    initialState: State
) : androidx.lifecycle.ViewModel() {

    private val viewModelState = com.omdbifood.android.viewmodel.livedata.State(initialState)
    private val viewModelAction = com.omdbifood.android.viewmodel.livedata.Action<Action>()

    val state: LiveData<State> = viewModelState.stateProperty
    val action: LiveData<Action> = viewModelAction.actionProperty

    protected fun setState(state: (State) -> State) {
        viewModelState.setState(state)
    }

    protected fun sendAction(action: () -> Action) {
        viewModelAction.sendAction(action)
    }
}
