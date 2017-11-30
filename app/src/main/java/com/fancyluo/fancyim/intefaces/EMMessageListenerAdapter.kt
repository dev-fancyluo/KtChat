package com.fancyluo.fancyim.intefaces

import com.hyphenate.EMMessageListener
import com.hyphenate.chat.EMMessage

/**
 * fancyluo
 * date: 2017/11/30 11:15
 * desc:
 */
open class EMMessageListenerAdapter : EMMessageListener {

    override fun onMessageRecalled(p0: MutableList<EMMessage>?) {
        //消息被撤回
    }

    override fun onMessageChanged(p0: EMMessage?, p1: Any?) {
        //消息状态变动
    }

    override fun onCmdMessageReceived(p0: MutableList<EMMessage>?) {
        //收到透传消息
    }

    override fun onMessageReceived(p0: MutableList<EMMessage>?) {
        // 收到消息
    }

    override fun onMessageDelivered(p0: MutableList<EMMessage>?) {
        //收到已送达回执
    }

    override fun onMessageRead(p0: MutableList<EMMessage>?) {
        //收到已读回执
    }

}