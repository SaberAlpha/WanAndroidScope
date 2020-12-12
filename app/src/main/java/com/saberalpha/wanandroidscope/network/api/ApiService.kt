package com.saberalpha.wanandroidscope.network.api

import com.saberalpha.mvvmlib.network.BaseResult
import com.saberalpha.wanandroidscope.entities.BannerBean
import com.saberalpha.wanandroidscope.entities.HomeListBean
import com.saberalpha.wanandroidscope.entities.NavTypeBean
import com.saberalpha.wanandroidscope.entities.UsedWeb
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * File: ApiService
 * author: zhangjiabiao Create on 2020/12/2 18:06
 * Change (from 2020/12/2)
 *--------------------------------
 *decription:
 *----------------------------
 *
 */
interface ApiService {

    /**
     * 玩安卓轮播图
     */
    @GET("banner/json")
    suspend fun getBanner(): BaseResult<List<BannerBean>>


    /**
     * 导航数据
     */
    @GET("project/tree/json")
    suspend fun naviJson(): BaseResult<List<NavTypeBean>>


    /**
     * 项目列表
     * @param page 页码，从0开始
     */
    @GET("article/listproject/{page}/json")
    suspend fun getHomeList(@Path("page") page: Int): BaseResult<HomeListBean>


    /**
     * 项目列表
     * @param page 页码，从0开始
     */
    @GET("project/list/{page}/json")
    suspend fun getProjectList(@Path("page") page: Int, @Query("cid") cid: Int): BaseResult<HomeListBean>


    /**
     * 常用网站
     */
    @GET("friend/json")
    suspend fun getPopularWeb(): BaseResult<List<UsedWeb>>
}