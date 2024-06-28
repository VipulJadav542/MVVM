package com.rk.mvvm.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuoteRepository) : ViewModel() {
    fun getQuotes(): LiveData<List<Quote>> {
        return repository.getQuotes()
    }

    fun insertQuotes(quote: Quote) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertQuotes(quote)
        }
    }
}