package com.rk.mvvm.RoomRetrofit.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rk.mvvm.RoomRetrofit.Repositoy.QuoteRepositoy

class MainViewModelFactory(private val repositoy: QuoteRepositoy):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repositoy) as T
    }
}