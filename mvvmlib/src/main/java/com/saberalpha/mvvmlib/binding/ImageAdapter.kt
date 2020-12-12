package com.saberalpha.mvvmlib.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


object ImageAdapter {

    @BindingAdapter(value = ["url", "placeholder"], requireAll = false)
    @JvmStatic
    fun setImageUrl(imageView: ImageView, url: String, placeholder: Int) {
        Glide.with(imageView.context)
            .load(url)
            .apply(RequestOptions().placeholder(placeholder))
            .into(imageView)

    }

    @BindingAdapter(value = ["circleUrl","placeholder"],requireAll = false)
    @JvmStatic
    fun setCircleImageUrl(imageView: ImageView, url: String, placeholder: Int){
        imageView.load(url){
            crossfade(true)
            placeholder(placeholder)
            transformations(CircleCropTransformation())
        }
    }

}