package com.omdbifood.android.viewmodel.livedata

import androidx.lifecycle.LiveData
import com.omdbifood.android.viewmodel.UIState
import com.omdbifood.android.viewmodel.livedata.plugins.ViewModelPlugins

class State<State : UIState>(initialState: State) {

    private val _state = ViewModelPlugins.factory.createMutableLiveDataState(initialState)
    val stateProperty: LiveData<State> = _state

    fun setState(state: (State) -> State) {
        _state.value = state(_state.value!!)
    }
}
