package com.saberalpha.wanandroidscope.network.http

import com.saberalpha.wanandroidscope.network.api.ApiService
import kotlinx.coroutines.flow.flow

/**
 * File: NetworkManager
 * author: zhangjiabiao Create on 2020/12/2 18:17
 * Change (from 2020/12/2)
 *--------------------------------
 *decription:
 *----------------------------
 *
 */
class NetworkManager {

    private val apiService: ApiService by lazy {
        RetrofitClient.getInstance().create(ApiService::class.java)
    }

    companion object {

        val instance by lazy { NetworkManager() }

    }

    /**
     * 玩安卓轮播图
     */
    fun getBanner() = flow { emit(apiService.getBanner()) }
}