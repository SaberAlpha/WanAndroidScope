package com.saberalpha.wanandroidscope.app

import android.app.Application
import android.content.ContextWrapper
import com.saberalpha.mvvmlib.utils.loadinghelper.LoadingHelper
import com.saberalpha.mvvmlib.utils.loadinghelper.ViewType
import com.saberalpha.mvvmlib.utils.loadinghelper.adapter.EmptyAdapter
import com.saberalpha.mvvmlib.utils.loadinghelper.adapter.ErrorAdapter
import com.saberalpha.mvvmlib.utils.loadinghelper.adapter.LoadingAdapter

/**
 * File: App
 * author: zhangjiabiao Create on 2020/12/12 15:00
 * Change (from 2020/12/12)
 *--------------------------------
 *decription:
 *----------------------------
 *
 */
lateinit var mApplication: Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        mApplication = this
        LoadingHelper.setDefaultAdapterPool {
            register(ViewType.LOADING, LoadingAdapter())
            register(ViewType.ERROR, ErrorAdapter())
            register(ViewType.EMPTY, EmptyAdapter())
        }
    }

}

object AppContext : ContextWrapper(mApplication)//ContextWrapper对Context上下文进行包装(装饰者模式)
