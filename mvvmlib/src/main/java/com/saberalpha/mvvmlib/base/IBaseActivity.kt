package com.saberalpha.mvvmlib.base


interface IBaseActivity {

    /**
     * 初始化界面传递参数
     */
    fun initParam()

    /**
     * 初始化数据
     */
    fun initData()

    /**
     * 初始化点击事件
     */
    fun initClickEvent()

    /**
     * 初始化界面观察者的监听
     */
    fun initViewObservable()


    /**
     * 注册RxBus
     */
//    fun registerRxBus()

    /**
     * 移除RxBus
     */
//    fun removeRxBus()

    /**
     * 初始化网络监听
     */
//    fun initNetWorkListener()

    /**
     * 初始化字体大小
     */
//    fun initFontScale():Float = FontScale.FRSCALE
}