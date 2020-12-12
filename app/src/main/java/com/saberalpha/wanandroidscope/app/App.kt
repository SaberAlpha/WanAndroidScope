package com.saberalpha.wanandroidscope.app

import android.app.Application
import android.content.Context
import android.content.ContextWrapper

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
    }

}

object AppContext : ContextWrapper(mApplication)//ContextWrapper对Context上下文进行包装(装饰者模式)
