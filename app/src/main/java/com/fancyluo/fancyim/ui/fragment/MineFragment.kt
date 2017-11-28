package com.fancyluo.fancyim.ui.fragment

import android.os.Bundle
import android.view.View
import com.fancyluo.fancyim.R
import com.fancyluo.fancyim.base.BaseFragment
import com.fancyluo.fancyim.intefaces.EMCallbackAdapter
import com.fancyluo.fancyim.ui.activity.LoginActivity
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.android.synthetic.main.include_toolbar.*
import org.jetbrains.anko.toast

/**
 * fancyLuo
 * date: 2017/11/24 22:20
 * desc: 我的
 */
class MineFragment : BaseFragment() {

    override fun setupLayout() = R.layout.fragment_mine

    override fun init(view: View?, savedInstanceState: Bundle?) {
        super.init(view, savedInstanceState)
        mContext.toolbar.title = "我的"

        // 设置用户
        tv_name.text = EMClient.getInstance().currentUser

        doLogout()
    }

    private fun doLogout() {
        btn_logout.setOnClickListener {
            EMClient.getInstance().logout(true, object : EMCallbackAdapter() {
                override fun onSuccess() {
                    mContext.runOnUiThread {
                        mContext.toast("您已退出登录")
                        startActivityAndFinish<LoginActivity>()
                    }
                }
                override fun onError(p0: Int, p1: String?) {
                    mContext.runOnUiThread {
                        mContext.toast("退出登录失败，请重新尝试")
                    }
                }
            })
        }
    }

}