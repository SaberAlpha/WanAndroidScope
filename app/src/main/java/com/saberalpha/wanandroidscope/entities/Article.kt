package com.saberalpha.wanandroidscope.entities

import com.saberalpha.mvvmlib.ext.toHtml

data class Article(
        var id: Int,
        var author: String,
        var chapterName: String?,
        var desc: String,
        var link: String,
        var originId: Int,
        var title: String,
        var collect: Boolean,
        var superChapterName: String?,
        var niceDate: String,
        var niceShareDate: String,
        var shareUser: String,
        var fresh: Boolean
){
        fun getAuthorText() = if(author.isEmpty()) shareUser else author
        fun getTitleText() = title.toHtml()
        fun getTimeText() = if(niceDate.isEmpty()) niceDate else niceShareDate
        fun getCategoryText() = when{
                superChapterName.isNullOrEmpty() && chapterName.isNullOrEmpty() ->  ""
                superChapterName.isNullOrEmpty() ->  chapterName ?: ""
                chapterName.isNullOrEmpty() -> superChapterName ?: ""
                else -> "$superChapterName / $chapterName"
        }
}