package com.fancyluo.fancyim.presenter

import com.fancyluo.fancyim.contract.ChatContract
import com.fancyluo.fancyim.intefaces.EMCallbackAdapter
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage

/**
 * fancyLuo
 * date: 2017/11/30 08:12
 * desc:
 */
class ChatPresenter(val view: ChatContract.View) : ChatContract.Presenter {

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


}