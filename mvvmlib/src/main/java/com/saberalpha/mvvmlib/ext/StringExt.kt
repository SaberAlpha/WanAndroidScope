package com.saberalpha.mvvmlib.ext

import android.text.Html
import android.widget.TextView

fun String.toHtml(): String {
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT).toString()
    } else {
        Html.fromHtml(this).toString()
    }
}

fun TextView.str(): String {
    return this.text.toString().trim()
}
