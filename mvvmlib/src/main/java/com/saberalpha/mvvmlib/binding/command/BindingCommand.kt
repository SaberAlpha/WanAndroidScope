package com.saberalpha.mvvmlib.binding.command

class BindingCommand<T>() {
    private var execute: (()->Unit)? = null
    private var consumer: BindingConsumer<T>? = null
    private var canExecute0: BindingFunction<Boolean>? = null

    constructor(execute: ()->Unit) : this() {
        this.execute = execute
    }

    /**
     * @param execute 带泛型参数的命令绑定
     */
    constructor(execute: BindingConsumer<T>?) : this(){
        consumer = execute
    }

    /**
     * @param execute     触发命令
     * @param canExecute0 true则执行,反之不执行
     */
    constructor(execute: ()->Unit, canExecute0: BindingFunction<Boolean>?): this() {
        this.execute = execute
        this.canExecute0 = canExecute0
    }

    /**
     * @param execute     带泛型参数触发命令
     * @param canExecute0 true则执行,反之不执行
     */
    constructor(execute: BindingConsumer<T>?, canExecute0: BindingFunction<Boolean>?): this() {
        consumer = execute
        this.canExecute0 = canExecute0
    }

    /**
     * 执行BindingAction命令
     */
    fun execute() {
        if (execute != null && canExecute0()) {
            execute?.invoke()
        }
    }

    /**
     * 执行带泛型参数的命令
     *
     * @param parameter 泛型参数
     */
    fun execute(parameter: T) {
        if (consumer != null && canExecute0()) {
            consumer!!.call(parameter)
        }
    }

    /**
     * 是否需要执行
     *
     * @return true则执行, 反之不执行
     */
    private fun canExecute0(): Boolean {
        return if (canExecute0 == null) {
            true
        } else canExecute0!!.call()
    }
}