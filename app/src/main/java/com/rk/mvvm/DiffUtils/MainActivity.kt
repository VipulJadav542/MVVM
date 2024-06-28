package com.rk.mvvm.DiffUtils

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rk.mvvm.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = ProgramingAdapter()

        val p1 = ProgramingItem(1, "A", "Java")
        val p2 = ProgramingItem(2, "B", "Kotlin")
        val p3 = ProgramingItem(3, "C", "C++")

        adapter.submitList(listOf(p1, p2, p3))
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        Handler(Looper.getMainLooper()).postDelayed({
            val p3 = ProgramingItem(3, "C", "C++")
            val p4 = ProgramingItem(4, "D", "MySQL")
            val p5 = ProgramingItem(5, "E", "C#")
            val p6 = ProgramingItem(6, "F", "JavaScript")
            adapter.submitList(listOf(p3, p4, p5, p6))
        }, 4000)


    }
}