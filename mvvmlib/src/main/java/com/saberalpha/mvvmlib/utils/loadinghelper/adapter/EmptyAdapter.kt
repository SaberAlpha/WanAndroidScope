package com.saberalpha.mvvmlib.utils.loadinghelper.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.saberalpha.mvvmlib.R
import com.saberalpha.mvvmlib.utils.loadinghelper.LoadingHelper

class EmptyAdapter : LoadingHelper.Adapter<LoadingHelper.ViewHolder>() {
    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): LoadingHelper.ViewHolder  = LoadingHelper.ViewHolder(inflater.inflate(R.layout.layout_empty, parent, false))

    override fun onBindViewHolder(holder: LoadingHelper.ViewHolder) {

    }
}