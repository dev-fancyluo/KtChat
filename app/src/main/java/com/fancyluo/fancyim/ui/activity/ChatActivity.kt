package com.fancyluo.fancyim.ui.activity

import com.fancyluo.fancyim.R
import com.fancyluo.fancyim.base.BaseActivity
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.include_toolbar.*

/**
 * fancyLuo
 * date: 2017/11/26 11:49
 * desc:
 */
class ChatActivity : BaseActivity() {

    override fun setupLayout() = R.layout.activity_chat

    override fun setupTitle() = "聊天"

    override fun init() {
        super.init()
        initTitle()
        initEditText()
    }

    private fun initTitle() {
        val title = intent.getStringExtra("username")
        toolbar.title = title
    }

    private fun initEditText() {
        editMsg.setOnEditorActionListener { _ , _, _ ->
            sendMsg()
            true
        }
    }

    private fun sendMsg() {

    }

}