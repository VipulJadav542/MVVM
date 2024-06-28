package com.rk.mvvm.dataBinding

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.rk.mvvm.R
import com.rk.mvvm.databinding.ActivityDataBindingBinding

class DataBindingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataBindingBinding
    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding)
        viewModel= ViewModelProvider(this)[MyViewModel::class.java]


        binding.mainViewModel1=viewModel
        binding.lifecycleOwner=this

    }
}