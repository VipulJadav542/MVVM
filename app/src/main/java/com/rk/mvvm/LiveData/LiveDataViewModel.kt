package com.rk.mvvm.LiveData

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataViewModel:ViewModel() {
    private val factLiveDataobject=MutableLiveData<String>("old fact")

    val factLiveData:LiveData<String>
        get() = factLiveDataobject

    fun changeLiveData(){
        factLiveDataobject.value="new fact"
    }
}