package com.saberalpha.wanandroidscope.ui.activities.web

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import com.just.agentweb.AgentWeb
import com.saberalpha.mvvmlib.base.BaseActivity
import com.saberalpha.wanandroidscope.BR
import com.saberalpha.wanandroidscope.R
import com.saberalpha.wanandroidscope.common.Constant.WEB_TITLE
import com.saberalpha.wanandroidscope.common.Constant.WEB_URL
import com.saberalpha.wanandroidscope.databinding.ActivityWebViewBinding
import com.saberalpha.wanandroidscope.ui.viewmodel.WebViewModel

class WebViewActivity : BaseActivity<ActivityWebViewBinding,WebViewModel>() {

    override fun initContentView(savedInstanceState: Bundle?): Int = R.layout.activity_web_view

    override fun initVariableId(): Int = BR.viewModel

    private lateinit var mAgentWeb: AgentWeb

    private var url: String = ""

    private var title: String = ""

    override fun initData() {
        super.initData()
        title = intent.getStringExtra(WEB_TITLE)?:""
        url = intent.getStringExtra(WEB_URL)?:""
        setToolbar(title,true)
        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(binding.container, LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            ))
            .useDefaultIndicator()
            .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
            .createAgentWeb()
            .ready()
            .go(url)
    }

    override fun onPause() {
        mAgentWeb.webLifeCycle.onPause()
        super.onPause()
    }

    override fun onResume() {
        mAgentWeb.webLifeCycle.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        mAgentWeb.webLifeCycle.onDestroy()
        super.onDestroy()
    }

    override fun onBackPressed() {
        if (mAgentWeb.back().not()) {
            super.onBackPressed()
        }
    }
}