package com.fancyluo.fancyim.contract

import com.fancyluo.fancyim.base.BasePresenter
import com.fancyluo.fancyim.base.BaseView

/**
 * fancyLuo
 * date: 2017/11/21 13:00
 * desc:
 */
class SplashContract {

    interface SplashView : BaseView {
        fun onNotLoggedIn()
        fun onLoggedIn()
    }

    interface SplashPresenter : BasePresenter {
        fun checkLoginStatus()
    }

}