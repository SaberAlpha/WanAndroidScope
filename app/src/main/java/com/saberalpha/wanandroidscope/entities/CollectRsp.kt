package com.saberalpha.wanandroidscope.entities

data class CollectRsp(
        var curPage: Int,
        var datas: List<Article>,
        var total: Int
)