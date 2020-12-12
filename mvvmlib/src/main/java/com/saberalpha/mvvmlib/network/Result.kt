package com.saberalpha.mvvmlib.network

data class Result<T>(
    var data: T,
    var code: String,
    var message: String
)