package com.fancyluo.fancyim.presenter

import com.fancyluo.fancyim.contract.LoginContract
import com.fancyluo.fancyim.extentions.isValidPassword
import com.fancyluo.fancyim.extentions.isValidUsername
import com.fancyluo.fancyim.intefaces.EMCallbackAdapter
import com.fancyluo.fancyim.ui.activity.LoginActivity
import com.hyphenate.chat.EMClient

/**
 * fancyLuo
 * date: 2017/11/21 22:13
 * desc:
 */
class LoginPresenter(val view: LoginActivity) : LoginContract.LoginPresenter {

    override fun login(username: String, password: String) {
        if (username.isValidUsername()) {
            if (password.isValidPassword()) {
                toLogin(username, password)
            } else {
                view.passwordError()
            }
        } else {
            view.usernameError()
        }
    }

    private fun toLogin(username: String, password: String) {
        view.loginStart()
        EMClient.getInstance().login(username, password, object : EMCallbackAdapter() {
            override fun onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups()
                EMClient.getInstance().chatManager().loadAllConversations()

                uiThread { view.loginSuccess() }
            }

            override fun onError(p0: Int, p1: String?) {
                uiThread { view.loginFailed() }
            }
        })
    }

}