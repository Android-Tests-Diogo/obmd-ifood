package com.omdbifood.android

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.omdbifood.android.viewmodel.UIAction
import com.omdbifood.android.viewmodel.UIState
import com.omdbifood.android.viewmodel.livedata.LiveDataFactory

@Suppress("UNCHECKED_CAST")
class TestLiveDataFactory(
    val stateObserver: Observer<UIState>,
    val actionObserver: Observer<UIAction>
) : LiveDataFactory {

    override fun <T : UIState> createMutableLiveDataState(initialState: T): MutableLiveData<T> =
        MutableLiveData(initialState).apply {
            observeForever(stateObserver as Observer<T>)
        }

    override fun <T : UIAction> createMutableLiveDataAction(): MutableLiveData<T> =
        MutableLiveData<T>().apply {
            observeForever(actionObserver as Observer<T>)
        }
}
