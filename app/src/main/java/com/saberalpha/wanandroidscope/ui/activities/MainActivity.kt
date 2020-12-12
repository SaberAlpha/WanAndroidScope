package com.saberalpha.wanandroidscope.ui.activities

import android.os.Bundle
import com.saberalpha.mvvmlib.base.BaseActivity
import com.saberalpha.wanandroidscope.BR
import com.saberalpha.wanandroidscope.R
import com.saberalpha.wanandroidscope.databinding.ActivityMainBinding
import com.saberalpha.wanandroidscope.ui.viewmodel.MainViewModel
import com.saberalpha.wanandroidscope.utils.attachPopup
import com.saberalpha.wanandroidscope.utils.createSortReposPopup
import com.saberalpha.wanandroidscope.utils.createSortUsesPopup

class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>() {

    override fun initContentView(savedInstanceState: Bundle?): Int = R.layout.activity_main

    override fun initVariableId(): Int = BR.viewModel

    override fun initData() {
        super.initData()
        binding.tvTest.setOnClickListener {
//            viewModel.testClick()
            attachPopup(this,it)
        }
    }


}