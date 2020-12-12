package com.saberalpha.mvvmlib.base

import android.app.Application
import androidx.lifecycle.*
import com.saberalpha.mvvmlib.network.ExceptionHandle
import com.saberalpha.mvvmlib.network.ResponseThrowable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * File: BaseViewModel
 * author: zhangjiabiao Create on 2020/12/2 17:09
 * Change (from 2020/12/2)
 *--------------------------------
 *decription:
 *----------------------------
 *
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application),
    IBaseViewModel {

    /**
     * 通用事件模型驱动(如：显示对话框、取消对话框、错误提示)
     */
    val mStateLiveData = MutableLiveData<StateActionEvent>()

    /**
     * 显示对话框
     */
    fun showLoading(){
        mStateLiveData.value = LoadState
    }

    /**
     * 取消对话框
     */
    fun dismissLoading(){
        mStateLiveData.value = DismissState
    }

    /**
     * 所有网络请求都在 viewModelScope 域中启动，当页面销毁时会自动
     * 调用ViewModel的  #onCleared 方法取消所有协程
     */
    fun launchUI(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch { block() }

    /**
     * 用流的方式进行网络请求
     */
    fun <T> launchFlow(block: suspend () -> T): Flow<T> {
        return flow {
            emit(block())
        }
    }

    /**
     * 过滤请求结果，其他全抛异常
     * @param block 请求体
     * @param success 成功回调
     * @param error 失败回调
     * @param complete  完成回调（无论成功失败都会调用）
     * @param isShowDialog 是否显示加载框
     */
    fun <T>  launchOnlyResult(
        block: suspend CoroutineScope.() -> IBaseResponse<T>,
        success: (T) -> Unit,
        error: (ResponseThrowable) -> Unit = {
//            defUI.toastEvent.postValue("${it.code}:${it.errMsg}")
        },
        complete: () -> Unit = {},
        isShowDialog: Boolean = true
    ) {
        launchUI {
            launchFlow { block() }
                .onStart {
//                    if (isShowDialog)
//                    defUI.showDialog.call()
                }
                .onCompletion{
//                    defUI.dismissDialog.call()
                    complete()
                }
                .catch {
                    error(ExceptionHandle.handleException(it))
                }
                .collect{ res->
                    executeResponse(res) { success(it) }
                }
        }
    }

    /**
     * 请求结果过滤
     */
    private suspend fun <T> executeResponse(
        response: IBaseResponse<T>,
        success: suspend CoroutineScope.(T) -> Unit
    ) {
        coroutineScope {
            if (response.isSuccess()) success(response.data())
            else throw ResponseThrowable(response.code(), response.msg())
        }
    }

    /**
     * 异常统一处理
     */
    private suspend fun <T> handleException(
        block: suspend CoroutineScope.() -> IBaseResponse<T>,
        success: suspend CoroutineScope.(IBaseResponse<T>) -> Unit,
        error: suspend CoroutineScope.(ResponseThrowable) -> Unit,
        complete: suspend CoroutineScope.() -> Unit
    ) {
        coroutineScope {
            try {
                success(block())
            } catch (e: Throwable) {
                error(ExceptionHandle.handleException(e))
            } finally {
                complete()
            }
        }
    }


    /**
     * 异常统一处理
     */
    private suspend fun handleException(
        block: suspend CoroutineScope.() -> Unit,
        error: suspend CoroutineScope.(ResponseThrowable) -> Unit,
        complete: suspend CoroutineScope.() -> Unit
    ) {
        coroutineScope {
            try {
                block()
            } catch (e: Throwable) {
                error(ExceptionHandle.handleException(e))
            } finally {
                complete()
            }
        }
    }


    override fun onAny(owner: LifecycleOwner, event: Lifecycle.Event) {

    }

    override fun onCreate() {

    }

    override fun onDestroy() {

    }

    override fun onStart() {

    }

    override fun onStop() {

    }

    override fun onResume() {

    }

    override fun onPause() {

    }


}