package com.saberalpha.wanandroidscope.ui.activities.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saberalpha.mvvmlib.base.BaseActivity
import com.saberalpha.wanandroidscope.BR
import com.saberalpha.wanandroidscope.R
import com.saberalpha.wanandroidscope.databinding.ActivityLoginBinding
import com.saberalpha.wanandroidscope.ui.viewmodel.LoginViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding,LoginViewModel>() {

    override fun initContentView(savedInstanceState: Bundle?): Int = R.layout.activity_login

    override fun initVariableId(): Int = BR.viewModel

    override fun initData() {
        super.initData()
    }
}