package com.saberalpha.wanandroidscope.ui.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.saberalpha.mvvmlib.base.BaseViewModel
import com.saberalpha.mvvmlib.network.subscribe
import com.saberalpha.wanandroidscope.network.http.NetworkManager
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class SearchViewModel(application: Application) : BaseViewModel(application) {


    fun search(page: Int, str: String){
        NetworkManager.instance.search(page,str)
            .onStart { showLoading() }
            .onCompletion { dismissLoading() }
            .subscribe(viewModelScope,{

            })
    }

    fun getHotSearch() {
        NetworkManager.instance.getHotKey()
            .onStart { showLoading() }
            .onCompletion { dismissLoading() }
            .subscribe(viewModelScope,{

            })
    }
}