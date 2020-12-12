package com.saberalpha.mvvmlib.network

/**
 * File: BaseResult
 * author: zhangjiabiao Create on 2020/12/3 18:45
 * Change (from 2020/12/3)
 *--------------------------------
 *decription:
 *----------------------------
 *
 */
data class BaseResult<T>(val errorMsg: String,
                         val errorCode: Int,
                         val data: T)