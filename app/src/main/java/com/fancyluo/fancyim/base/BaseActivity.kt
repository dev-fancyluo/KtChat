package com.fancyluo.fancyim.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * fancyLuo
 * date: 2017/11/21 11:57
 * desc:
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setupLayout())
        init()
    }

    abstract fun setupLayout(): Int

    /**
     * 初始化一些公共功能
     * 子类也可覆写该方法，完成自己的初始化
     */
    open fun init(){

    }
}