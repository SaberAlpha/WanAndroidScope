package com.saberalpha.mvvmlib.network

import com.blankj.utilcode.util.LogUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.saberalpha.mvvmlib.network.Result

/**
 * File: HttpExt
 * author: zhangjiabiao Create on 2020/12/3 15:14
 * Change (from 2020/12/3)
 *--------------------------------
 *decription:
 *----------------------------
 *
 */

public fun <T> Flow<BaseResult<T>>.subscribe(scope: CoroutineScope,success: (T?) -> Unit,
                                         onServerError: (ResponseThrowable) -> Unit = {}){
    this.onEach {
            when(it.errorCode){
                0->success(it.data)
            }
        }.catch {
            val exception = ExceptionHandle.handleException(it)
            onServerError(exception)
            LogUtils.d("error",exception.message)
        }
        .launchIn(scope)
}

