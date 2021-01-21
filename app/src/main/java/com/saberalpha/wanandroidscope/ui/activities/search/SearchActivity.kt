package com.saberalpha.wanandroidscope.ui.activities.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saberalpha.mvvmlib.base.BaseActivity
import com.saberalpha.wanandroidscope.BR
import com.saberalpha.wanandroidscope.R
import com.saberalpha.wanandroidscope.databinding.ActivitySearchBinding
import com.saberalpha.wanandroidscope.ui.viewmodel.SearchViewModel

class SearchActivity : BaseActivity<ActivitySearchBinding,SearchViewModel>() {

    override fun initContentView(savedInstanceState: Bundle?): Int = R.layout.activity_search

    override fun initVariableId(): Int = BR.viewModel

    override fun initData() {
        super.initData()

    }
}