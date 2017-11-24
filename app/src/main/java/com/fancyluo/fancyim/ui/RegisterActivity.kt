package com.fancyluo.fancyim.ui

import com.fancyluo.fancyim.R
import com.fancyluo.fancyim.base.BaseActivity
import com.fancyluo.fancyim.contract.RegisterContract
import com.fancyluo.fancyim.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

/**
 * fancyLuo
 * date: 2017/11/22 23:09
 * desc:
 */
class RegisterActivity : BaseActivity(), RegisterContract.View {

    private val mPresenter = RegisterPresenter(this)

    override fun setupLayout() = R.layout.activity_register

    override fun init() {
        super.init()
        btn_register.setOnClickListener {
            registerUser()
        }
        et_confirm_password.setOnEditorActionListener { _, _, _ ->
            registerUser()
            true
        }
    }

    private fun registerUser() {
        val username = et_username.text.toString().trim()
        val password = et_password.text.toString().trim()
        val confirmPassword = et_confirm_password.text.toString().trim()
        mPresenter.registerUser(username, password, confirmPassword)
    }

    override fun usernameError() {
        et_username.error = "无效的用户名"
    }

    override fun passwordError() {
        et_password.error = "无效的密码"
    }

    override fun confirmPasswordError() {
        et_confirm_password.error = "两次密码不相同"
    }

    override fun registerStart() {
        showProgressDialog(" 正在注册...")
    }

    override fun registerSuccess() {
        hideProgressDialog()
        toast("注册成功")
        this.finish()
    }

    override fun registerFailed() {
        hideProgressDialog()
        toast("注册失败")
    }

    override fun userAlreadyExist() {
        hideProgressDialog()
        toast(" 用户已存在")
    }

}