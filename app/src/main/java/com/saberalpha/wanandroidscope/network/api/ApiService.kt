package com.saberalpha.wanandroidscope.network.api

import com.saberalpha.mvvmlib.network.BaseResult
import com.saberalpha.wanandroidscope.entities.*
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.POST
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
     * 首页文章
     */
    @GET("/article/list/{page}/json")
    suspend fun getHomeArticle(@Path("page") page: Int): BaseResult<HomeArticleRsp>

    /**
     * 收藏
     */
    @POST("/lg/collect/{id}/json")
    suspend fun collect(@Path("id") id: Int): BaseResult<Any>

    /**
     * 取消收藏
     */
    @POST("/lg/uncollect_originId/{id}/json")
    suspend fun unCollect(@Path("id") id: Int): BaseResult<Any>

    /**
     * 登录
     */
    @POST("/user/login")
    suspend fun login(@Query("username") username: String, @Query("password") password: String): BaseResult<UserInfo>/**

     * 注册
     */
    @POST("/user/register")
    suspend fun register(@Query("username") username: String, @Query("password") password: String,
                         @Query("repassword") repassword: String): BaseResult<UserInfo>

    /**
     * 获取收藏
     */
    @GET("/lg/collect/list/{page}/json")
    suspend fun getCollectArticle(@Path("page") page: Int): BaseResult<CollectRsp>

    /**
     * 取消收藏页收藏
     */
    @POST("/lg/uncollect/{id}/json")
    suspend fun unMyCollect(@Path("id") id: Int, @Query("originId") originId: Int): BaseResult<Any>

    /**
     * 微信公众号列表
     */
    @GET("/wxarticle/chapters/json")
    suspend fun getWeChat(): BaseResult<List<WeChatNameRsp>>

    /**
     * 获取微信文章列表
     */
    @GET("/wxarticle/list/{id}/{page}/json")
    suspend fun getWeChatList(@Path("id") id: Int, @Path("page") page: Int): BaseResult<WeChatListRsp>

    /**
     * 导航页面数据
     */
    @GET("/navi/json")
    suspend fun getCategory(): BaseResult<List<NagivationCategoryRsp>>

    /**
     * 搜索
     */
    @POST("/article/query/{page}/json")
    suspend fun search(@Path("page") page: Int, @Query("k") key: String): BaseResult<SearchResultRsp>

    /**
     * 热门搜索
     */
    @GET("/hotkey/json")
    suspend fun getHotKey(): BaseResult<List<HotSearchRsp>>

    /**
     * 体系一级菜单
     */
    @GET("/tree/json")
    suspend fun getTopMenu(): BaseResult<List<TopMenuRsp>>

    /**
     * 获取体系文章列表
     */
    @GET("/article/list/{page}/json")
    suspend fun getSystemArticles(@Path("page") page: Int, @Query("cid") id: Int): BaseResult<SystemAtricleRsp>

    /**
     * 获取项目Tab
     */
    @GET("/project/tree/json")
    suspend fun getProjectTab(): BaseResult<List<ProjectTabRsp>>

    /**
     * 获取项目列表
     */
    @GET("/project/list/{page}/json")
    suspend fun getProjectList(@Path("page") page: Int, @Query("cid") cid: Int): BaseResult<ProjectRsp>
}