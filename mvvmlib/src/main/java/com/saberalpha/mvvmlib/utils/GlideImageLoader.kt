package com.saberalpha.mvvmlib.utils

import android.content.Context
import android.widget.ImageView
import coil.load
import com.youth.banner.loader.ImageLoader

class GlideImageLoader : ImageLoader() {

    override fun displayImage(context: Context?, path: Any, imageView: ImageView) {
        imageView.load(path as String)
    }

}