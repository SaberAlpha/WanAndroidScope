package com.saberalpha.mvvmlib.ext

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import org.jetbrains.anko.internals.AnkoInternals

fun <T> MutableLiveData<T>.call() {
    this.value = null
}

fun <T> Fragment.createIntent(ctx: Context, clazz: Class<out T>, params: Array<out Pair<String, Any?>>): Intent {
    return AnkoInternals.createIntent(ctx,clazz,params)
}