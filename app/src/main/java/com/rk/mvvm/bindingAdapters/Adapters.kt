package com.rk.mvvm.bindingAdapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageFromurl")
fun ImageView.imageFromurl(url: String){
    Glide.with(this.context).load(url).into(this)
}