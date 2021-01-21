package com.saberalpha.wanandroidscope.ui.viewmodel

import android.app.Application
import androidx.databinding.*
import androidx.lifecycle.viewModelScope
import com.saberalpha.mvvmlib.base.BaseViewModel
import com.saberalpha.mvvmlib.binding.command.BindingAction
import com.saberalpha.mvvmlib.binding.command.BindingCommand
import com.saberalpha.mvvmlib.network.subscribe
import com.saberalpha.mvvmlib.utils.GlideImageLoader
import com.saberalpha.wanandroidscope.BR
import com.saberalpha.wanandroidscope.R
import com.saberalpha.wanandroidscope.common.Constant
import com.saberalpha.wanandroidscope.databinding.ItemArticleBinding
import com.saberalpha.wanandroidscope.databinding.LayoutHomeHeadviewBinding
import com.saberalpha.wanandroidscope.entities.Article
import com.saberalpha.wanandroidscope.entities.BannerBean
import com.saberalpha.wanandroidscope.entities.BannerRes
import com.saberalpha.wanandroidscope.network.http.NetworkManager
import com.saberalpha.wanandroidscope.ui.activities.web.WebViewActivity
import com.youth.banner.BannerConfig
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass

class ArticleListViewModel(application: Application) : BaseViewModel(application) {

    var page = 0

    val stateField = ObservableInt(0)

    val startRefreshField = ObservableInt(0)

    var uid = 0

    var type = 0

    private val observableList: ObservableList<Article> = ObservableArrayList()

    private val observableBanner: ObservableList<BannerRes> = ObservableArrayList()

    val itemBinding = OnItemBindClass<Any>().apply {
        map(BannerRes::class.java,BR.item,R.layout.layout_home_headview)
        map(Article::class.java,BR.item,R.layout.item_article)
    }

    var headerItems: MergeObservableList<Any> = MergeObservableList<Any>()
        .insertList(observableBanner)
        .insertList(observableList)

    val refreshAction = BindingCommand<Any>{
        page = 0
        refreshList()
    }

    val loadMoreAction = BindingCommand<Any>{
        page++
        refreshList()
    }

    private fun refreshList(){
        when(type){
            Constant.HOME->{
                getArticle()
                if (page == 0) getBanner()
            }
            Constant.WE_CHAT-> getWeChatList()
        }
    }

    fun getBanner(){
        NetworkManager.instance.getBanner()
            .onStart { showLoading() }
            .onCompletion { dismissLoading() }
            .subscribe(viewModelScope,{
                val urls = mutableListOf<String>()
                val titles = mutableListOf<String>()
                val images = mutableListOf<String>()
                it?.forEach { item->
                    images.add(item.imagePath)
                    titles.add(item.title)
                    urls.add(item.url)
                }
                if (urls.isNotEmpty()){
                    observableBanner.clear()
                    observableBanner.add(BannerRes(images,titles,urls))
                }
            })
    }

    fun getArticle(state:Int = page){
        NetworkManager.instance.getHomeArticle(page)
            .onStart { showRefresh(state,startRefreshField) }
            .onCompletion { dismissRefreshLoadMore(page, stateField) }
            .subscribe(viewModelScope,{
                it?.datas?.forEach { item->
                    observableList.add(item)
                }
            })
    }

    fun getWeChatList(state:Int = page){
        NetworkManager.instance.getWeChatList(uid,page)
            .onStart { showRefresh(state,startRefreshField) }
            .onCompletion { dismissRefreshLoadMore(page, stateField) }
            .subscribe(viewModelScope,{
                it?.datas?.forEach { item->
                    observableList.add(item)
                }
            })
    }

    val adapter = object :BindingRecyclerViewAdapter<Any>(){
        override fun onBindBinding(
            binding: ViewDataBinding, variableId: Int, layoutRes: Int, position: Int, item: Any) {
            super.onBindBinding(binding, variableId, layoutRes, position, item)
            when(item){
                is BannerRes->{
                    val bannerBinding = binding as LayoutHomeHeadviewBinding
                    bannerBinding.mBanner.setImageLoader(GlideImageLoader())
                        .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                        .setDelayTime(3000)
                        .setOnBannerListener{
                            startActivity<WebViewActivity>(Constant.WEB_URL to item.urls[it],Constant.WEB_TITLE to item.titles[it])
                        }
                    bannerBinding.mBanner.setImages(item.images).setBannerTitles(item.titles).start()
                }
                is Article ->{
                    val articleBinding = binding as ItemArticleBinding
                    articleBinding.ctlContent.setOnClickListener {
                        startActivity<WebViewActivity>(Constant.WEB_URL to item.link,Constant.WEB_TITLE to item.title)
                    }
                }
                else->{

                }
            }
        }
    }

}