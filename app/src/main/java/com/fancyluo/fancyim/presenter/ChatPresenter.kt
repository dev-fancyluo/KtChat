package com.fancyluo.fancyim.presenter

import com.fancyluo.fancyim.contract.ChatContract
import com.fancyluo.fancyim.intefaces.EMCallbackAdapter
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import org.jetbrains.anko.doAsync

/**
 * fancyLuo
 * date: 2017/11/30 08:12
 * desc:
 */
class ChatPresenter(val view: ChatContract.View) : ChatContract.Presenter {

    companion object {
        val PAGE_SIZE = 10
    }

    var msgList = mutableListOf<EMMessage>()

    override fun sendMsg(username: String, msg: String) {
        val emMessage = EMMessage.createTxtSendMessage(msg, username)
        msgList.add(emMessage)
        view.onSendMsgStart()

        emMessage.setMessageStatusCallback(object : EMCallbackAdapter() {
            override fun onSuccess() {
                uiThread { view.onSendMsgSuccess() }
            }

            override fun onError(p0: Int, p1: String?) {
                uiThread { view.onSendMsgFailed() }
            }
        })

        EMClient.getInstance().chatManager().sendMessage(emMessage)
    }

    override fun addReceiverMsg(title: String, msgList: MutableList<EMMessage>?) {
        // 添加消息到列表
        msgList?.let { this.msgList.addAll(it) }
        // 标为已读
        EMClient.getInstance().chatManager().getConversation(title).markAllMessagesAsRead()
    }

    override fun loadInitMsg(title: String) {
        doAsync {
            val conversation = EMClient.getInstance().chatManager().getConversation(title)
            conversation.markAllMessagesAsRead()
            val initMsgList = conversation.allMessages
            msgList.addAll(initMsgList)
            uiThread { view.onLoadInitMsgSuccess() }
        }
    }

    override fun loadMoreInitMsg(title: String) {
        doAsync {
            val conversation = EMClient.getInstance().chatManager().getConversation(title)
            val initMsgList = conversation.loadMoreMsgFromDB(msgList[0].msgId, PAGE_SIZE)
            msgList.addAll(0, initMsgList)
            uiThread { view.onLoadMoreInitMsgSuccess(initMsgList.size) }
        }
    }

}