package com.saberalpha.mvvmlib.binding.command

/**
 * A one-argument action.
 * @param <T> the first argument type
 */
interface BindingConsumer<T> {
    fun call(t: T)
}