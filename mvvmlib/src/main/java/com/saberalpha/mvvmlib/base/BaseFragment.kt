package com.saberalpha.mvvmlib.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.blankj.utilcode.util.ToastUtils
import com.saberalpha.mvvmlib.ext.createIntent
import java.lang.reflect.ParameterizedType

/**
 * File: BaseFragment
 * author: zhangjiabiao Create on 2020/12/2 17:12
 * Change (from 2020/12/2)
 *--------------------------------
 *decription:
 *----------------------------
 *
 */
abstract class BaseFragment<V : ViewDataBinding, VM : BaseViewModel>:Fragment(),IBaseActivity {

    protected lateinit var binding: V
    protected lateinit var viewModel: VM
    private var viewModelId: Int? = null

    //是否第一次加载
    private var isFirst: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflater.let {
            binding = DataBindingUtil.inflate(it, initContentView(it, container, savedInstanceState), container, false)
        }
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
        viewLifecycleOwner.lifecycle.addObserver(viewModel)
        return binding.root
    }

    private fun <T : ViewModel> createViewModel(cls: Class<T>): T {
        return ViewModelProvider(this).get(cls)
    }

    abstract fun initVariableId(): Int?

    abstract fun initContentView(it: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onVisible()
        //页面数据初始化方法
        initParam()
        initData()
        initClickEvent()
        initViewObservable()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //解除ViewModel生命周期感应
        viewLifecycleOwner.lifecycle.removeObserver(viewModel)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }


    override fun initParam() {

    }

    /**
     * 是否需要懒加载
     */
    private fun onVisible() {
        if (lifecycle.currentState == Lifecycle.State.STARTED && isFirst) {
            lazyLoadData()
            isFirst = false
        }
    }

    /**
     * 懒加载
     */
    open fun lazyLoadData() {}

    override fun initData() {

    }

    override fun initClickEvent() {

    }

    override fun initViewObservable() {
        viewModel.mStateLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                LoadState -> showLoading()
                DismissState -> dismissLoading()
                SuccessState -> dismissLoading()
                FinishEventState -> activity?.finish()
                OnBackPressedEventState ->activity?.onBackPressed()
                is ErrorState -> {
                    dismissLoading()
                }
                is ToastEventState -> {
                    ToastUtils.showLong(it.message)
                }
                is StartActivityState->{
                    startActivity(createIntent(requireContext(), it.activity, it.params))
                }
            }
        })
    }

    private fun dismissLoading() {

    }

    private fun showLoading() {

    }


}