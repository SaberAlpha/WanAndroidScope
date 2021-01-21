package com.saberalpha.wanandroidscope.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.saberalpha.mvvmlib.base.BaseFragment
import com.saberalpha.wanandroidscope.BR
import com.saberalpha.wanandroidscope.R
import com.saberalpha.wanandroidscope.databinding.FragmentArticleListBinding
import com.saberalpha.wanandroidscope.ui.viewmodel.ArticleListViewModel

class ProjectFragment : BaseFragment<FragmentArticleListBinding, ArticleListViewModel>() {

    override fun initVariableId(): Int = BR.viewModel

    override fun initContentView(
        it: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): Int = R.layout.fragment_article_list
}