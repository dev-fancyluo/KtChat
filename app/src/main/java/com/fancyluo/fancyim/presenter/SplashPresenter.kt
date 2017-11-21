package com.fancyluo.fancyim.presenter

import com.fancyluo.fancyim.contract.SplashContract

/**
 * fancyLuo
 * date: 2017/11/21 13:06
 * desc:
 */
class SplashPresenter(val view:SplashContract.SplashView) : SplashContract.SplashPresenter {

    override fun checkLoginStatus() {
        if (isLoggedIn()) view.onLoggedIn() else view.onNotLoggedIn()
    }

    private fun isLoggedIn(): Boolean = false

}