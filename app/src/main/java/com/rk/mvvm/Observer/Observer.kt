@file:Suppress("DEPRECATION")

package com.rk.mvvm.Observer

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class Observer:LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.d("Lifecycle", "onCreate from observer")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.d("Lifecycle", "onStart from observer")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.d("Lifecycle", "onResume from observer")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.d("Lifecycle", "onPause from observer")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.d("Lifecycle", "onStop from observer")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        Log.d("Lifecycle", "onDestroy from observer")
    }

//    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
//    fun onAny() {
//        Log.d(TAG, "onAny from observer")
//    }
}