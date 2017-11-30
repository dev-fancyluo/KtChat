package com.fancyluo.fancyim.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.fancyluo.fancyim.R
import com.fancyluo.fancyim.base.BaseActivity
import com.fancyluo.fancyim.contract.ChatContract
import com.fancyluo.fancyim.intefaces.EMMessageListenerAdapter
import com.fancyluo.fancyim.presenter.ChatPresenter
import com.fancyluo.fancyim.ui.adapter.ChatMsgAdapter
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.include_toolbar.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

/**
 * fancyLuo
 * date: 2017/11/26 11:49
 * desc:
 */
class ChatActivity : BaseActivity(), ChatContract.View {

    var title = ""

    val presenter = ChatPresenter(this)

    override fun setupLayout() = R.layout.activity_chat

    override fun setupTitle() = "聊天"

    override fun init() {
        super.init()
        initTitle()
        initEditText()
        initRecyclerView()
        initChatListener()
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ChatMsgAdapter(context, presenter.msgList)
        }
    }

    private fun initTitle() {
        title = intent.getStringExtra("username")
        toolbar.title = title
    }

    private fun initEditText() {
        editMsg.setOnEditorActionListener { _, _, _ ->
            sendMsg()
            true
        }
    }

    private fun sendMsg() {
        val msg = editMsg.text.trim().toString()
        if (msg.isEmpty()) toast("消息不可为空") else presenter.sendMsg(title, msg)
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

    private fun initChatListener() {
        EMClient.getInstance().chatManager().addMessageListener(msgListener)
    }

    private val msgListener = object : EMMessageListenerAdapter() {

        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            // 收到消息
            p0?.forEach {
                presenter.msgList.add(it)
            }
            context.runOnUiThread { recyclerView.adapter.notifyDataSetChanged() }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(msgListener)
    }

}