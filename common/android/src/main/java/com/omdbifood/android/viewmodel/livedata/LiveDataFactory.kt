package com.omdbifood.android.viewmodel.livedata

import androidx.lifecycle.MutableLiveData
import com.omdbifood.android.viewmodel.UIAction
import com.omdbifood.android.viewmodel.UIState

interface LiveDataFactory {

    fun <T : UIState> createMutableLiveDataState(initialState: T): MutableLiveData<T>

    fun <T : UIAction> createMutableLiveDataAction(): MutableLiveData<T>
}
