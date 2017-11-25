package com.fancyluo.fancyim.ui.activity

import android.os.Handler
import com.fancyluo.fancyim.R
import com.fancyluo.fancyim.base.BaseActivity
import com.fancyluo.fancyim.contract.SplashContract
import com.fancyluo.fancyim.presenter.SplashPresenter
import org.jetbrains.anko.startActivity

/**
 * fancyLuo
 * date: 2017/11/21 12:39
 * desc:
 */
class SplashActivity : BaseActivity(),SplashContract.SplashView {

    private val mPresenter = SplashPresenter(this)

    companion object {
        val DELAY_TIME = 2000L
    }

    private val mHandler by lazy {
        Handler()
    }

    override fun setupLayout(): Int = R.layout.activity_splash

    override fun init() {
        super.init()
        mPresenter.checkLoginStatus()
    }

    override fun onNotLoggedIn() {
        mHandler.postDelayed({
            startActivity<LoginActivity>()
            this.finish()
        }, DELAY_TIME)
    }

    override fun onLoggedIn() {
        startActivity<MainActivity>()
        this.finish()
    }
}