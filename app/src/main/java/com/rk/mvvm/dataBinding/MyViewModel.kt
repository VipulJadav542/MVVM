package com.rk.mvvm.dataBinding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel:ViewModel() {
    val factLiveData = MutableLiveData("vipul jadav")
    fun changeLiveData(){
        factLiveData.value="new fact"
    }
}