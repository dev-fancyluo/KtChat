package com.fancyluo.fancyim.presenter

import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.fancyluo.fancyim.contract.RegisterContract
import com.fancyluo.fancyim.extentions.isValidPassword
import com.fancyluo.fancyim.extentions.isValidUsername
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * fancyLuo
 * date: 2017/11/24 07:45
 * desc:
 */
class RegisterPresenter(val view: RegisterContract.View) : RegisterContract.Presenter {

    override fun registerUser(username: String, password: String, confirmPassword: String) {
        if (username.isValidUsername()) {
            if (password.isValidPassword()) {
                if (password == confirmPassword) {
                    view.registerStart()
                    toRegisterBmob(username, password)
                } else {
                    view.confirmPasswordError()
                }
            } else {
                view.passwordError()
            }
        } else {
            view.usernameError()
        }
    }

    private fun toRegisterBmob(username: String, password: String) {
        val user = BmobUser()
        user.username = username
        user.setPassword(password)
        user.signUp(object : SaveListener<BmobUser>() {
            override fun done(user: BmobUser?, e: BmobException?) {
                // BmobException对象为空表示注册成功
                if (e == null) {
                    // 注册环信
                    toRegisterEasemob(username, password)
                } else {
                    e.printStackTrace()
                    // 用户已存在
                    if (e.errorCode == 202) view.userAlreadyExist()
                }
            }
        })
    }

    private fun toRegisterEasemob(username: String, password: String) {
        doAsync {
            try {
                //注册失败会抛出 HyphenateException
                //同步方法
                EMClient.getInstance().createAccount(username, password)
                uiThread { view.registerSuccess() }
            } catch (e: HyphenateException) {
                e.printStackTrace()
                uiThread { view.registerFailed() }
            }
        }
    }

}