package com.saberalpha.mvvmlib.utils.loadinghelper.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.saberalpha.mvvmlib.R
import com.saberalpha.mvvmlib.utils.loadinghelper.LoadingHelper

class ErrorAdapter : LoadingHelper.Adapter<ErrorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup) = ViewHolder(
        inflater.inflate(
            R.layout.layout_error,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder) {
        holder.btnReload.setOnClickListener {
            holder.onReloadListener?.onReload()
        }
    }

    inner class ViewHolder constructor(rootView: View) : LoadingHelper.ViewHolder(rootView) {
        val btnReload: View = rootView.findViewById(R.id.btn_reload)
    }
}