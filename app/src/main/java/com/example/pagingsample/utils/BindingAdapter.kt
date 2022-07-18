package com.example.pagingsample.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.pagingsample.R

fun glideOptions() = RequestOptions()
    .placeholder(R.drawable.ic_loading)
    .error(R.drawable.ic_load_image_error)
    .diskCacheStrategy(DiskCacheStrategy.ALL)
    .priority(Priority.HIGH);

@BindingAdapter("imageUrl")
fun getImageUrl(imageView: ImageView, imageUrl: String) {
    Glide.with(imageView).load(imageUrl)
        .apply(glideOptions())
        .into(imageView)
}