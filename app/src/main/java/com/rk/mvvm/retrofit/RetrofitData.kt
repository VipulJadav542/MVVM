package com.rk.mvvm.retrofit

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rk.mvvm.Home
import com.rk.mvvm.R
import retrofit2.Call
import retrofit2.Callback

class RetrofitData : AppCompatActivity() {
    private val save: Button
        get() = findViewById(R.id.save)
    private val name1: EditText
        get() = findViewById(R.id.name1)
    private val city1: EditText
        get() = findViewById(R.id.city1)
    private val state1: EditText
        get() = findViewById(R.id.state1)

    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_retrofit_data)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerView3)
        progressBar = findViewById(R.id.progressBar2)

        val quote = RetrofitHelper.getInstance().create(MyAPI::class.java)
        val requestCall = quote.getTask()
        requestCall.enqueue(object : Callback<myData> {
            override fun onResponse(p0: Call<myData>, p1: retrofit2.Response<myData>) {
                progressBar.visibility = View.GONE
                val adapter = RetrofitAdapter(p1.body()!!.items, this@RetrofitData)
                recyclerView.layoutManager = LinearLayoutManager(this@RetrofitData)
                recyclerView.adapter = adapter
            }
            override fun onFailure(p0: Call<myData>, p1: Throwable) {
                progressBar.visibility = View.GONE
                Toast.makeText(this@RetrofitData, "something went wrong", Toast.LENGTH_LONG).show()
            }
        })
        save.setOnClickListener {
            addTask()
        }
    }

    fun addTask() {
        if (name1.text.isNotEmpty() && city1.text.isNotEmpty() && state1.text.isNotEmpty()) {
            val quote = RetrofitHelper.getInstance().create(MyAPI::class.java)
            val Item1 = listOf(
                insertItem(
                    city1.text.toString(),
                    name1.text.toString(),
                    state1.text.toString()
                )
            )
            val requestCall = quote.addTast(
//                "application/json",
//                "Bearer j1O1luD59q3dmkJDkz1lcI-7v5hGOjiS7XiHelHgTFmGDyHXiw",
                Item1
            )
            requestCall.enqueue(object : Callback<insertItem> {
                override fun onResponse(p0: Call<insertItem>, p1: retrofit2.Response<insertItem>) {
                    Toast.makeText(
                        this@RetrofitData,
                        "record saved successfully",
                        Toast.LENGTH_LONG
                    ).show()
                    startActivity(Intent(this@RetrofitData, Home::class.java))
                }

                override fun onFailure(p0: Call<insertItem>, p1: Throwable) {
                    Toast.makeText(this@RetrofitData, "something went wrong", Toast.LENGTH_LONG)
                        .show()
                    startActivity(Intent(this@RetrofitData, Home::class.java))
                }
            })
        } else {
            Toast.makeText(this@RetrofitData, "please enter all fields", Toast.LENGTH_LONG).show()
        }

    }
}