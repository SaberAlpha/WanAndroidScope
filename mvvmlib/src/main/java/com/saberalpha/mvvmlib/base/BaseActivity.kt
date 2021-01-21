package com.saberalpha.mvvmlib.base

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.ToastUtils
import com.saberalpha.mvvmlib.ext.createIntent
import com.saberalpha.mvvmlib.utils.DialogUtils
import com.saberalpha.mvvmlib.utils.loadinghelper.LoadingHelper
import com.saberalpha.mvvmlib.utils.loadinghelper.ViewType
import com.saberalpha.mvvmlib.utils.loadinghelper.adapter.ToolbarAdapter
import org.jetbrains.anko.internals.AnkoInternals.createIntent
import java.lang.reflect.ParameterizedType

/**
 * File: BaseActivity
 * author: zhangjiabiao Create on 2020/12/2 17:08
 * Change (from 2020/12/2)
 *--------------------------------
 *decription:
 *----------------------------
 *
 */
abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(),IBaseActivity{

    protected lateinit var binding: V
    protected lateinit var viewModel: VM
    private var viewModelId: Int? = null
    private var loadingHelper: LoadingHelper? = null
    private var toolbarAdapter: ToolbarAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewDataBinding(savedInstanceState)
        initLoadingHelper()
        initParam()
        initData()
        initClickEvent()
        initViewObservable()
    }

    private fun initViewDataBinding(savedInstanceState: Bundle?){
        binding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState))
        viewModelId = initVariableId()
        if (!this::viewModel.isInitialized) {
            val modelClass: Class<VM>
            val type = javaClass.genericSuperclass
            modelClass = if (type is ParameterizedType) {
                type.actualTypeArguments[1] as Class<VM>
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                BaseViewModel::class.java as Class<VM>
            }
            viewModel = createViewModel(modelClass)
        }
        //关联viewModel
        viewModelId?.let { binding.setVariable(it, viewModel) }
        //让ViewModel拥有View的生命周期感应
        lifecycle.addObserver(viewModel)
    }


    private fun <T : ViewModel> createViewModel(cls: Class<T>): T {
        return ViewModelProvider(this).get(cls)
    }

    abstract fun initContentView(savedInstanceState: Bundle?): Int

    abstract fun initVariableId(): Int?

    private fun initLoadingHelper(){
        loadingHelper = LoadingHelper(this)
        loadingHelper?.setOnReloadListener {

        }
    }

    fun changeContentLoadingView(contentView: View){
        loadingHelper = LoadingHelper(contentView)
        loadingHelper?.setOnReloadListener {

        }
    }

    override fun initParam() {

    }

    override fun initData() {

    }

    override fun initClickEvent() {

    }

    override fun initViewObservable() {
        viewModel.mStateLiveData.observe(this, Observer {
            when (it) {
                LoadState -> showLoading()
                DismissState -> dismissLoading()
                SuccessState -> dismissLoading()
                FinishEventState -> finish()
                OnBackPressedEventState -> onBackPressed()
                is ErrorState -> {
                    dismissLoading()
                }
                is ToastEventState -> {
                    ToastUtils.showLong(it.message)
                }
                is StartActivityState->{
                    startActivity(createIntent(this, it.activity, it.params))
                }
            }
        })
    }

    /**
     * 配置标题栏
     */
    fun setToolbar(title: String, isShowBack: Boolean = true, menuId: Int? = null,onLeftClick:(()->Unit)? = null){
        toolbarAdapter = ToolbarAdapter(title, isShowBack, menuId,{ item: MenuItem? ->
            onOptionsItemSelected(
                item!!
            )
        },onLeftClick)
        loadingHelper?.register(
            ViewType.TITLE,toolbarAdapter!!)

        loadingHelper?.setDecorHeader(ViewType.TITLE)
    }

    fun setToolbarTitle(title: String){
        toolbarAdapter?.setTitle(title)
    }

    open fun showLoadingView() = loadingHelper?.showLoadingView()

    open fun showContentView() = loadingHelper?.showContentView()

    open fun showErrorView() = loadingHelper?.showErrorView()

    open fun showEmptyView() = loadingHelper?.showEmptyView()

    /**
     *隐藏弹框
     */
    fun dismissLoading() {
        DialogUtils.instance.dismissLoading()
    }

    /**
     * 显示弹框
     */
    fun showLoading() {
        DialogUtils.instance.showLoading(this, this)
    }

    override fun onDestroy() {
        super.onDestroy()
        //解除ViewModel生命周期感应
        lifecycle.removeObserver(viewModel)
        binding.unbind()
    }

}