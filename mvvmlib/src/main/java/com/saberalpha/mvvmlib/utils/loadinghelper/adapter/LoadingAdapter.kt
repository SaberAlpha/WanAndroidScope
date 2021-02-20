package com.saberalpha.mvvmlib.utils.loadinghelper.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.saberalpha.mvvmlib.R
import com.saberalpha.mvvmlib.utils.loadinghelper.LoadingHelper

class LoadingAdapter(private val height:Int = ViewGroup.LayoutParams.MATCH_PARENT) : LoadingHelper.Adapter<LoadingHelper.ViewHolder>()  {

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) = LoadingHelper.ViewHolder(inflater.inflate(R.layout.layout_loading, parent, false))

    override fun onBindViewHolder(holder: LoadingHelper.ViewHolder) {
        val layoutParams: ViewGroup.LayoutParams = holder.rootView.layoutParams
        layoutParams.height = height
        holder.rootView.layoutParams = layoutParams
    }

}