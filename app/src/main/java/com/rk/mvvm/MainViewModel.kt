package com.rk.mvvm

import androidx.lifecycle.ViewModel

class MainViewModel(initialvalue:Int):ViewModel() {

    var count=initialvalue
    fun increment()
    {
        count++
    }
}