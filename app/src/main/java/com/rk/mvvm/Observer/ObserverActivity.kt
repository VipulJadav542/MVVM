package com.rk.mvvm.Observer

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rk.mvvm.R

class ObserverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_observer)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        lifecycle.addObserver(Observer())
        Log.d("Lifecycle", "onCreate() called")
    }

    override fun onStart() {
//        lifecycle.addObserver(Observer())
        super.onStart()
        Log.d("Lifecycle", "onStart() called")
    }

    override fun onResume() {
//        lifecycle.addObserver(Observer())
        super.onResume()
        Log.d("Lifecycle", "onResume() called")
    }

    override fun onPause() {
//        lifecycle.addObserver(Observer())
        super.onPause()
        Log.d("Lifecycle", "onPause() called")
    }

    override fun onStop() {
//        lifecycle.addObserver(Observer())
        super.onStop()
        Log.d("Lifecycle", "onStop() called")
    }

    override fun onDestroy() {
//        lifecycle.addObserver(Observer())
        super.onDestroy()
        Log.d("Lifecycle", "onDestroy() called")
    }

    override fun onRestart() {
//        lifecycle.addObserver(Observer())
        super.onRestart()
        Log.d("Lifecycle", "onRestart() called")
    }
}