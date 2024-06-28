package com.rk.mvvm

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rk.mvvm.bindingAdapters.BindingAdapter
import com.rk.mvvm.dataBinding.DataBindingActivity
import com.rk.mvvm.LiveData.LiveDataActivity
import com.rk.mvvm.mvvm.MainActivity
import com.rk.mvvm.Observer.ObserverActivity
import com.rk.mvvm.retrofit.RetrofitData
import com.rk.mvvm.RoomRetrofit.RoomRetrofit
import com.rk.mvvm.databinding.ActivityHomeBinding


class Home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.observer.setOnClickListener {
            startActivity(Intent(this,ObserverActivity::class.java))
        }

        binding.ViewModel.setOnClickListener {
            startActivity(Intent(this,ViewModelActivity::class.java))
        }

        binding.LiveData.setOnClickListener {
            startActivity(Intent(this,LiveDataActivity::class.java))
        }

        binding.Databinding.setOnClickListener {
            startActivity(Intent(this,DataBindingActivity::class.java))
        }
        binding.bindingadapters.setOnClickListener {
            startActivity(Intent(this,BindingAdapter::class.java))
        }

        binding.MVVM.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

        binding.DiffUtils.setOnClickListener {
            startActivity(Intent(this,com.rk.mvvm.DiffUtils.MainActivity::class.java))
        }

        binding.RoomRetrofit.setOnClickListener {
            startActivity(Intent(this,RoomRetrofit::class.java))
        }

        binding.RetrofitData.setOnClickListener {
            startActivity(Intent(this, RetrofitData::class.java))
        }

        binding.map.setOnClickListener {
            startActivity(Intent(this, RetrofitData::class.java))
        }
        binding.ImageSlider.setOnClickListener {
            startActivity(Intent(this, ImageSlider::class.java))
        }
    }
}