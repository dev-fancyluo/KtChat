package com.fancyluo.fancyim.ui

import android.Manifest
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.support.v4.app.ActivityCompat
import com.fancyluo.fancyim.R
import com.fancyluo.fancyim.base.BaseActivity
import com.fancyluo.fancyim.contract.LoginContract
import com.fancyluo.fancyim.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * fancyLuo
 * date: 2017/11/21 19:23
 * desc:
 */
class LoginActivity : BaseActivity(), LoginContract.LoginView {

    private val mPresenter = LoginPresenter(this)

    override fun setupLayout(): Int = R.layout.activity_login

    override fun init() {
        super.init()
        btn_login.setOnClickListener { login() }
        et_password.setOnEditorActionListener { p0, p1, p2 ->
            login()
            true
        }
    }

    private fun login() {
        if (hasPermission()) {
            hideSoftKeyboard()
            val username = et_username.text.toString().trim()
            val password = et_password.text.toString().trim()
            mPresenter.login(username, password)
        } else {
            val permissionArray = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            ActivityCompat.requestPermissions(this,permissionArray,10086)
        }
    }

    private fun hasPermission(): Boolean {
        val permission = ActivityCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return permission == PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PERMISSION_GRANTED && requestCode == 10086) {
             login()
        } else{
            toast("权限授权失败")
        }
    }

    override fun usernameError() {
        et_username.error = "无效的用户名"
    }

    override fun passwordError() {
        et_password.error = "无效的密码"
    }

    override fun loginStart() {
        showProgressDialog(" 正在登录...")
    }

    override fun loginSuccess() {
        hideProgressDialog()
        startActivity<MainActivity>()
        this.finish()
    }

    override fun loginFailed() {
        hideProgressDialog()
        toast(" 登录失败")
    }

}