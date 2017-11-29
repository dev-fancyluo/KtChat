package com.fancyluo.fancyim.contract

import com.fancyluo.fancyim.base.BasePresenter
import com.fancyluo.fancyim.base.BaseView

/**
 * fancyLuo
 * date: 2017/11/24 07:43
 * desc:
 */
class RegisterContract {

    interface View : BaseView {
        fun usernameError()
        fun passwordError()
        fun registerStart()
        fun registerSuccess()
        fun registerFailed()
        fun confirmPasswordError()
        fun userAlreadyExist()
    }

    interface Presenter : BasePresenter {
        fun registerUser(username: String, password: String, confirmPassword: String)
    }

}