package com.rk.mvvm

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class ViewModelActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    lateinit var text: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val factory = MainViewModelFactory(10) // Pass initial value here
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        text = findViewById(R.id.textView)
        setText()
    }

    private fun setText() {
        text.text = viewModel.count.toString()
    }

    fun increment(v : View) {
        viewModel.increment()
        setText()
    }
}