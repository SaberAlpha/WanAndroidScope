package com.saberalpha.wanandroidscope.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.blankj.utilcode.util.LogUtils
import com.saberalpha.mvvmlib.base.BaseFragment
import com.saberalpha.wanandroidscope.BR
import com.saberalpha.wanandroidscope.R
import com.saberalpha.wanandroidscope.common.Constant
import com.saberalpha.wanandroidscope.databinding.FragmentArticleListBinding
import com.saberalpha.wanandroidscope.ui.viewmodel.ArticleListViewModel

class ArticleListFragment : BaseFragment<FragmentArticleListBinding, ArticleListViewModel>() {

    override fun initVariableId(): Int = BR.viewModel

    override fun initContentView(
        it: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): Int = R.layout.fragment_article_list

    private var type = 1

    private var uid = -1

    companion object {
        fun newInstance(type : Int,uid : Int = -1): ArticleListFragment {
            return ArticleListFragment().apply {
                this.type = type
                this.uid = uid
            }
        }
    }

    override fun initData() {
        super.initData()
        viewModel.type = type
        when(type){
            Constant.HOME->{
                viewModel.getArticle(-1)
                viewModel.getBanner()
            }
            Constant.WE_CHAT->{
                LogUtils.d("Article>>>>$uid")
                viewModel.uid = uid
                viewModel.getWeChatList()
            }
        }

    }
}