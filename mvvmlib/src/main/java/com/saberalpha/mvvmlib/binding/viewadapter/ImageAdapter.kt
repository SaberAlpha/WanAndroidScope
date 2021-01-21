package com.saberalpha.mvvmlib.binding.viewadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation


object ImageAdapter {

    @BindingAdapter(value = ["url", "placeholder"], requireAll = false)
    @JvmStatic
    fun setImageUrl(imageView: ImageView, url: String, placeholder: Int) {


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