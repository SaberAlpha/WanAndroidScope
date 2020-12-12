package com.saberalpha.mvvmlib.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.saberalpha.mvvmlib.utils.DialogUtils
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewDataBinding(savedInstanceState)
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

    override fun initParam() {

    }

    override fun initData() {

    }

    override fun initClickEvent() {

    }

    override fun initViewObservable() {
        viewModel.mStateLiveData.observe(this, Observer {
            when(it){
                LoadState -> showLoading()
                DismissState -> dismissLoading()
                SuccessState -> dismissLoading()
                is ErrorState -> {
                   dismissLoading()
                }
            }
        })
    }

    private fun dismissLoading() {
        DialogUtils.instance.dismissLoading()
    }

    private fun showLoading() {
        DialogUtils.instance.showLoading(this,this)
    }

    override fun onDestroy() {
        super.onDestroy()
        //解除ViewModel生命周期感应
        lifecycle.removeObserver(viewModel)
        binding.unbind()
    }

}