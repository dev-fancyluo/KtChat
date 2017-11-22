package com.fancyluo.fancyim.base

import android.os.Handler
import android.os.Looper

/**
 * fancyLuo
 * date: 2017/11/21 12:59
 * desc:
 */
interface BasePresenter {

    companion object {
        private val handler by lazy {
            Handler(Looper.getMainLooper())
        }
    }

    fun uiThread(f: () -> Unit) {
        handler.post { f() }
    }


}