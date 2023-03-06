package com.omdbifood.android

import androidx.lifecycle.Observer
import com.omdbifood.android.viewmodel.UIAction
import com.omdbifood.android.viewmodel.UIState
import com.omdbifood.android.viewmodel.livedata.LiveDataFactoryImpl
import com.omdbifood.android.viewmodel.livedata.plugins.ViewModelPlugins
import io.mockk.mockk
import org.junit.rules.ExternalResource

@Suppress("UNCHECKED_CAST", "VisibleForTests")
class ViewModelTestRule : ExternalResource() {

    private val stateObserver: Observer<UIState> = mockk(relaxed = true)
    private val actionObserver: Observer<UIAction> = mockk(relaxed = true)

    private val factory = TestLiveDataFactory(stateObserver, actionObserver)

    fun <T : UIState> getStateObserver(): Observer<T> =
        factory.stateObserver as Observer<T>

    fun <T : UIAction> getActionObserver(): Observer<T> =
        factory.actionObserver as Observer<T>

    override fun before() {
        ViewModelPlugins.setFactory(factory)
    }

    override fun after() {
        ViewModelPlugins.setFactory(LiveDataFactoryImpl())
    }
}
