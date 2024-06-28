package com.rk.mvvm.RoomRetrofit.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rk.mvvm.RoomRetrofit.Repositoy.QuoteRepositoy
import com.rk.mvvm.RoomRetrofit.Repositoy.Response
import com.rk.mvvm.RoomRetrofit.model.QuoteList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuoteRepositoy):ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuote(1)
        }
    }
    val quotes:LiveData<Response<QuoteList>>
        get() = repository.quote
}