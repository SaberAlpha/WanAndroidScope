package com.saberalpha.mvvmlib.utils

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.saberalpha.mvvmlib.R

/**
 * File: DialogUtils
 * author: zhangjiabiao Create on 2020/12/4 10:29
 * Change (from 2020/12/4)
 *--------------------------------
 *decription:
 *----------------------------
 *
 */
class DialogUtils {

    companion object {
        val instance by lazy { DialogUtils() }
    }

    private var dialog: MaterialDialog? = null

    /**
     * 打开等待框
     */
    fun showLoading(context: Context,owner: LifecycleOwner) {
        if (dialog == null) {
            dialog = MaterialDialog(context)
                    .cancelable(false)
                    .cornerRadius(8f)
                    .customView(R.layout.custom_progress_dialog_view, noVerticalPadding = true)
                    .lifecycleOwner(owner)
                    .maxWidth(R.dimen.dialog_width)
        }
        dialog?.show()
    }

    /**
     * 关闭等待框
     */
    fun dismissLoading() {
        dialog?.run { if (isShowing) dismiss() }
    }

}