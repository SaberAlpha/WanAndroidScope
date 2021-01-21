package com.saberalpha.wanandroidscope.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.saberalpha.mvvmlib.base.BaseFragment
import com.saberalpha.wanandroidscope.BR
import com.saberalpha.wanandroidscope.R
import com.saberalpha.wanandroidscope.adapter.PublicCodeAdapter
import com.saberalpha.wanandroidscope.common.Constant
import com.saberalpha.wanandroidscope.databinding.FragmentArticleListBinding
import com.saberalpha.wanandroidscope.databinding.FragmentPublicCodeBinding
import com.saberalpha.wanandroidscope.entities.WeChatNameRsp
import com.saberalpha.wanandroidscope.ui.viewmodel.ArticleListViewModel
import com.saberalpha.wanandroidscope.ui.viewmodel.PublicCodeViewModel

class PublicCodeFragment : BaseFragment<FragmentPublicCodeBinding, PublicCodeViewModel>() {

    override fun initVariableId(): Int = BR.viewModel

    override fun initContentView(
        it: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): Int = R.layout.fragment_public_code

    override fun initData() {
        super.initData()
        binding.mTabLayout.setupWithViewPager(binding.mContent)
        viewModel.getWeChat()
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.publicCodeEvent.observe(this, Observer {
            initViewPage(it)
        })
    }

    private fun initViewPage(data: List<WeChatNameRsp>){
        val titles = mutableListOf<String>()
        val fragments = mutableListOf<Fragment>()

        for (item in data) {
            titles.add(item.name)
            fragments.add(ArticleListFragment.newInstance(Constant.WE_CHAT,item.id))
        }

        binding.mContent.adapter = PublicCodeAdapter(childFragmentManager,titles,fragments)
    }
}