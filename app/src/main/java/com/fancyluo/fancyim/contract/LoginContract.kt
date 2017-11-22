package com.fancyluo.fancyim.contract

import com.fancyluo.fancyim.base.BasePresenter
import com.fancyluo.fancyim.base.BaseView

/**
 * fancyLuo
 * date: 2017/11/21 21:54
 * desc:
 */
class LoginContract {

    interface LoginView : BaseView {
        fun usernameError()
        fun passwordError()
        fun loginStart()
        fun loginSuccess()
        fun loginFailed()
    }

    interface LoginPresenter : BasePresenter {
        fun login(username: String, password: String)
    }

}