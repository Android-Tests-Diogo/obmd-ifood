package com.omdbifood.android.viewmodel.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.omdbifood.android.viewmodel.UIAction
import com.omdbifood.android.viewmodel.livedata.plugins.ViewModelPlugins

class Action<Action : UIAction> {

    private val _action: MutableLiveData<Action> =
        ViewModelPlugins.factory.createMutableLiveDataAction()
    val actionProperty: LiveData<Action> = _action

    fun sendAction(action: () -> Action) {
        _action.value = action()
    }
}
