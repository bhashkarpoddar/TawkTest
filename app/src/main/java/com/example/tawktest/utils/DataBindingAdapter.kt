package com.example.tawktest.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.tawktest.R

@BindingAdapter("imgUrl")
fun loadImageUrl(imageView: ImageView, imgUrl: String) {
    Glide.with(imageView)
        .load(imgUrl)
        .placeholder(R.color.purple_200)
        .into(imageView)
}