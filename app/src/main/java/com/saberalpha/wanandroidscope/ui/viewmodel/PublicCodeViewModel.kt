package com.saberalpha.wanandroidscope.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.saberalpha.mvvmlib.base.BaseViewModel
import com.saberalpha.mvvmlib.network.subscribe
import com.saberalpha.wanandroidscope.entities.WeChatNameRsp
import com.saberalpha.wanandroidscope.network.http.NetworkManager
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class PublicCodeViewModel(application: Application) : BaseViewModel(application) {

    val publicCodeEvent = MutableLiveData<List<WeChatNameRsp>>()

    fun getWeChat(){
        NetworkManager.instance.getWeChat()
            .onStart { showLoading() }
            .onCompletion { dismissLoading() }
            .subscribe(viewModelScope,{
                it?.let {
                    publicCodeEvent.value = it
                }
            })
    }

}