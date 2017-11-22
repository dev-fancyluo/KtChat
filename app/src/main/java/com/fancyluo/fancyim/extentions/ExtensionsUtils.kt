package com.fancyluo.fancyim.extentions

/**
 * fancyLuo
 * date: 2017/11/21 22:28
 * desc: 扩展方法工具类
 */

fun String.isValidUsername(): Boolean =
        this.matches(Regex("^[a-zA-Z]\\w{2,19}$"))

fun String.isValidPassword(): Boolean =
        this.matches(kotlin.text.Regex("^(\\w){6,20}\$"))