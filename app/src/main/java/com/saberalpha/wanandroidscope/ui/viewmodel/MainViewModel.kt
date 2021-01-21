package com.saberalpha.wanandroidscope.ui.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.saberalpha.mvvmlib.base.BaseViewModel
import com.saberalpha.mvvmlib.network.subscribe
import com.saberalpha.wanandroidscope.network.http.NetworkManager
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

/**
 * File: MainViewModel
 * author: zhangjiabiao Create on 2020/12/3 10:01
 * Change (from 2020/12/3)
 *--------------------------------
 *decription:
 *----------------------------
 *
 */
class MainViewModel(application: Application) : BaseViewModel(application) {

    val textField = ObservableField<String>("Hello World!")

    fun getBanner(){
        NetworkManager.instance.getBanner()
            .onStart { showLoading() }
            .onCompletion { dismissLoading() }
            .subscribe(viewModelScope,{
                textField.set(it.toString())
            })
    }
}