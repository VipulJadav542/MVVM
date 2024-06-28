package com.rk.mvvm.RoomRetrofit

import MyAdapter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rk.mvvm.QuoteApplication
import com.rk.mvvm.R
import com.rk.mvvm.RoomRetrofit.Repositoy.QuoteRepositoy
import com.rk.mvvm.RoomRetrofit.ViewModel.MainViewModel
import com.rk.mvvm.RoomRetrofit.ViewModel.MainViewModelFactory
import com.rk.mvvm.RoomRetrofit.api.QuoteAPI
import com.rk.mvvm.RoomRetrofit.api.RetrofitHelper
import retrofit2.Response

class RoomRetrofit : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    val progressBar: ProgressBar
        get() = findViewById(R.id.progressBar)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_room_retrofit)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerView2)
        val repositoy = (application as QuoteApplication).quoteRepositoy
        viewModel =
            ViewModelProvider(this, MainViewModelFactory(repositoy)).get(MainViewModel::class.java)

        viewModel.quotes.observe(this) {
            when (it) {
                is com.rk.mvvm.RoomRetrofit.Repositoy.Response.Loading -> {
                }
                is com.rk.mvvm.RoomRetrofit.Repositoy.Response.Success -> {
                    progressBar.visibility= View.GONE
                    Toast.makeText(this, it.data!!.results.size.toString(), Toast.LENGTH_SHORT).show()
                    val Adapter = MyAdapter(it.data.results, this)
                    recyclerView.layoutManager = LinearLayoutManager(this)
                    recyclerView.adapter = Adapter
                }

                is com.rk.mvvm.RoomRetrofit.Repositoy.Response.Error -> {
//                    it.errorMessage
                    progressBar.visibility= View.GONE
                    Toast.makeText(this,it.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}