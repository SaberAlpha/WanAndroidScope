package com.saberalpha.wanandroidscope.entities

data class UserInfo (
    var username: String,
    var id: Int,
    var icon: String,
    var type: Int,
    var collectIds: List<Int>
)