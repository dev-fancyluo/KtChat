package com.fancyluo.fancyim.ui.activity

import com.fancyluo.fancyim.R
import com.fancyluo.fancyim.base.BaseActivity
import com.fancyluo.fancyim.contract.ChatContract
import com.fancyluo.fancyim.presenter.ChatPresenter
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.include_toolbar.*
import org.jetbrains.anko.toast

/**
 * fancyLuo
 * date: 2017/11/26 11:49
 * desc:
 */
class ChatActivity : BaseActivity(),ChatContract.View {

    var title = ""

    val presenter = ChatPresenter(this)

    override fun setupLayout() = R.layout.activity_chat

    override fun setupTitle() = "聊天"

    override fun init() {
        super.init()
        initTitle()
        initEditText()
    }

    private fun initTitle() {
        title = intent.getStringExtra("username")
        toolbar.title = title
    }

    private fun initEditText() {
        editMsg.setOnEditorActionListener { _ , _, _ ->
            sendMsg()
            true
        }
    }

    private fun sendMsg() {
        val msg = editMsg.text.trim().toString()
        if (msg.isEmpty()) toast("消息不可为空") else presenter.sendMsg(title,msg)
    }

    override fun onSendMsgStart() {
        recyclerView.adapter.notifyDataSetChanged()
    }

    override fun onSendMsgSuccess() {
        editMsg.text.clear()
        recyclerView.adapter.notifyDataSetChanged()
    }

    override fun onSendMsgFailed() {
        recyclerView.adapter.notifyDataSetChanged()
    }

}