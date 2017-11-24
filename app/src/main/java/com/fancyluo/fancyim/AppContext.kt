package com.fancyluo.fancyim

import android.app.Application
import cn.bmob.v3.Bmob
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions

/**
 * fancyLuo
 * date: 2017/11/21 20:10
 * desc:
 */
class AppContext : Application(){

    companion object {
        @JvmStatic
        lateinit var instance: AppContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initHx()
        initBmob();
    }

    private fun initBmob() {
        Bmob.initialize(this, "244e708577f0ac5ba47eee84a92c0d5b");
    }

    private fun initHx() {
        //初始化
        EMClient.getInstance().init(applicationContext, EMOptions())
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)
    }

}