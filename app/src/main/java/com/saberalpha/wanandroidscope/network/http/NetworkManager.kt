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

    /**
     * 首页文章
     */
    fun getHomeArticle(page: Int) = flow { emit(apiService.getHomeArticle(page)) }

    /**
     * 收藏
     */
    fun collect(id: Int) = flow { emit(apiService.collect(id)) }

    /**
     * 取消收藏
     */
    fun unCollect(id: Int) = flow { emit(apiService.unCollect(id)) }

    /**
     * 登录或注册
     */
    fun loginOrRegister(username: String, password: String, repassword: String?) =
        flow {
            emit(repassword?.let { apiService.register(username, password, password) }
                ?: apiService.login(username, password))
        }

    /**
     * 获取收藏内容
     */
    fun getCollectArticle(page: Int) = flow { emit(apiService.getCollectArticle(page)) }

    /**
     * 取消收藏页收藏
     */
    fun unMyCollect(id: Int, originId: Int) = flow { emit(apiService.unMyCollect(id, originId)) }

    /**
     * 微信公众号列表
     */
    fun getWeChat() = flow { emit(apiService.getWeChat()) }

    /**
     * 获取微信文章列表
     */
    fun getWeChatList(id: Int, page: Int) = flow { emit(apiService.getWeChatList(id, page)) }

    /**
     * 导航页面数据
     */
    fun getCategory() = flow { emit(apiService.getCategory()) }

    /**
     * 搜索
     */
    fun search(page: Int, key: String) = flow { emit(apiService.search(page, key)) }

    /**
     * 热门搜索
     */
    fun getHotKey() = flow { emit(apiService.getHotKey()) }

    /**
     * 体系一级菜单
     */
    fun getTopMenu() = flow { emit(apiService.getTopMenu()) }

    /**
     * 获取体系文章列表
     */
    fun getSystemArticles(page: Int,id: Int) = flow { emit(apiService.getSystemArticles(page, id)) }

    /**
     * 获取项目Tab
     */
    fun getProjectTab() = flow { emit(apiService.getProjectTab()) }

    /**
     * 获取项目列表
     */
    fun getProjectList(page: Int,cid: Int) = flow { emit(apiService.getProjectList(page, cid)) }

}