package com.saberalpha.mvvmlib.binding.command

/**
 * Represents a function with zero arguments.
 *
 * @param <T> the result type
 */
interface BindingFunction<T> {
    fun call(): T
}