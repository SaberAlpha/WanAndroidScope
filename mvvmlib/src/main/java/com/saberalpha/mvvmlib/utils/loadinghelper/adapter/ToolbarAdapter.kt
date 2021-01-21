package com.saberalpha.mvvmlib.utils.loadinghelper.adapter

import android.app.Activity
import android.os.Build
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.annotation.RequiresApi
import com.saberalpha.mvvmlib.R
import com.saberalpha.mvvmlib.utils.loadinghelper.LoadingHelper
import com.saberalpha.mvvmlib.utils.loadinghelper.adapter.ToolbarAdapter.ViewHolder

class ToolbarAdapter(
    val title: String, val isShowBack: Boolean = true,
    val menuId: Int? = null, val onMenuItemClick: Function1<MenuItem?, Boolean>? = null,
    val onLeftClick:(()->Unit)? = null
)
    : LoadingHelper.Adapter<ViewHolder>() {

    var toolBar:Toolbar? = null

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.layout_toolbar, parent, false))
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            holder.getActivity().window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        toolBar = holder.toolbar

        if (!TextUtils.isEmpty(title)) {
            holder.toolbar.title = title
        }

        if (isShowBack){
            holder.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black)
            holder.toolbar.setNavigationOnClickListener {
                onLeftClick?.invoke()?:holder.getActivity().finish()
            }
        } else {
            holder.toolbar.navigationIcon = null
        }

        menuId?.let {
            holder.toolbar.inflateMenu(it)
        }

        onMenuItemClick?.let {
            holder.toolbar.setOnMenuItemClickListener { item->
                it.invoke(item)
            }
        }

    }

    fun setTitle(title: String){
        toolBar?.title = title
    }


    inner class ViewHolder(rootView: View) : LoadingHelper.ViewHolder(rootView) {

        var toolbar: Toolbar = rootView as Toolbar

        fun getActivity() = rootView.context as Activity

    }
}