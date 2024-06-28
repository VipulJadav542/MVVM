package com.rk.mvvm.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.rk.mvvm.R
import com.rk.mvvm.databinding.ActivityMain2Binding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)

        val dao = QuoteDatabase.getDatabase(applicationContext).getDao()

        val repository = QuoteRepository(dao)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]
        mainViewModel.getQuotes().observe(this) {
            binding.quotes = it.toString()
        }

        binding.btnAddQuote.setOnClickListener {
            val quote = Quote(0, "vipyknjsbfjnfjfe", "vipul jadav")
            mainViewModel.insertQuotes(quote)
        }
    }
}