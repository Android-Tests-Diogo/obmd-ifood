package com.omdbifood.android.viewmodel.livedata

import androidx.lifecycle.MutableLiveData
import com.omdbifood.android.viewmodel.UIAction
import com.omdbifood.android.viewmodel.UIState

class LiveDataFactoryImpl : LiveDataFactory {

    override fun <T : UIState> createMutableLiveDataState(initialState: T): MutableLiveData<T> =
        MutableLiveData(initialState)

    override fun <T : UIAction> createMutableLiveDataAction(): MutableLiveData<T> =
        MutableLiveData<T>()
}
