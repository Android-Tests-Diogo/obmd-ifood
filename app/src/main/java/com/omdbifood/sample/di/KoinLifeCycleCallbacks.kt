package com.omdbifood.sample.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.omdbifood.android.koin.KoinActivity
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class KoinLifeCycleCallbacks : Application.ActivityLifecycleCallbacks,
    FragmentManager.FragmentLifecycleCallbacks() {

    override fun onActivityCreated(activity: Activity, p1: Bundle?) {
        if (activity is KoinActivity) {
            activity.getFeatureModule {
                loadKoinModules(
                    it
                )
            }
        }
    }

    override fun onActivityDestroyed(activity: Activity) {
        if (activity is KoinActivity) {
            activity.getFeatureModule {
                unloadKoinModules(
                    it
                )
            }
        }
    }
    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) = Unit

    override fun onActivityStarted(p0: Activity) = Unit

    override fun onActivityResumed(p0: Activity) = Unit

    override fun onActivityPaused(p0: Activity) = Unit

    override fun onActivityStopped(p0: Activity) = Unit
}
