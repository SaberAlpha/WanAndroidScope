package com.saberalpha.wanandroidscope.entities


data class SearchResultRsp (
    var curPage: Int,
    var datas: List<Article>,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int
)