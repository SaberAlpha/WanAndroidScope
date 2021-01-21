package com.saberalpha.wanandroidscope.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saberalpha.mvvmlib.base.BaseActivity
import com.saberalpha.wanandroidscope.BR
import com.saberalpha.wanandroidscope.R
import com.saberalpha.wanandroidscope.databinding.ActivitySplashBinding
import com.saberalpha.wanandroidscope.databinding.ActivitySplashBindingImpl
import com.saberalpha.wanandroidscope.ui.viewmodel.SplashViewModel

class SplashActivity : BaseActivity<ActivitySplashBinding,SplashViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun initContentView(savedInstanceState: Bundle?): Int = R.layout.activity_splash

    override fun initVariableId(): Int = BR.viewModel

    override fun initData() {
        super.initData()
    }
}