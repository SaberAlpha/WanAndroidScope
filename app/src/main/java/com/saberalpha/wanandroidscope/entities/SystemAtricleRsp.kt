package com.saberalpha.wanandroidscope.entities


data class SystemAtricleRsp (
    var curPage: Int,
    var datas: List<Article>,
    var pageCount: Int,
    var total: Int
)