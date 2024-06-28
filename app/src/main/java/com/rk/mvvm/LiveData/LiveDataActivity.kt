package com.rk.mvvm.LiveData

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.rk.mvvm.R

class LiveDataActivity : AppCompatActivity() {

    private val text: TextView
        get() = findViewById(R.id.textView2)

    private val change: Button
        get() = findViewById(R.id.button2)

    private lateinit var liveDataViewModel: LiveDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_live_data)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        liveDataViewModel = ViewModelProvider(this)[LiveDataViewModel::class.java]


        liveDataViewModel.factLiveData.observe(this, androidx.lifecycle.Observer {
            text.text = it
        })

        change.setOnClickListener {
            liveDataViewModel.changeLiveData()
        }

    }
}