package com.saberalpha.mvvmlib.binding.viewadapter

import androidx.databinding.BindingAdapter
import com.saberalpha.mvvmlib.binding.command.BindingCommand
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener

object SmartRefreshAdapter {

    @BindingAdapter(value = ["onRefreshCommand", "onLoadMoreCommand"], requireAll = false)
    @JvmStatic
    fun onRefreshAndLoadMoreCommand(
        layout: SmartRefreshLayout,
        onRefreshCommand: BindingCommand<Any>?,
        onLoadMoreCommand: BindingCommand<Any>?
    ){
        layout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                onRefreshCommand?.execute()
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                onLoadMoreCommand?.execute()
            }
        })
    }

    @BindingAdapter(value = ["finishAction"], requireAll = false)
    @JvmStatic
    fun finishAction(layout: SmartRefreshLayout, state: Int) {
        if (state == 0) {
            layout.finishRefresh()
        } else {
            layout.finishLoadMore()
        }
    }

    @BindingAdapter(value = ["startAction"], requireAll = false)
    @JvmStatic
    fun startLoadAction(layout: SmartRefreshLayout, state: Int) {
        if (state == -1){
            layout.autoRefreshAnimationOnly()
        } else {
            layout.finishRefresh()
        }
    }
}