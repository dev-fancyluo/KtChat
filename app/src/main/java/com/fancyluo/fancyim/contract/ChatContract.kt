package com.fancyluo.fancyim.contract

import com.fancyluo.fancyim.base.BasePresenter
import com.fancyluo.fancyim.base.BaseView
import com.hyphenate.chat.EMMessage

/**
 * fancyLuo
 * date: 2017/11/30 08:05
 * desc:
 */
class ChatContract {

    interface View : BaseView {
        fun onSendMsgStart()
        fun onSendMsgSuccess()
        fun onSendMsgFailed()
        fun onLoadInitMsgSuccess()
        fun onLoadMoreInitMsgSuccess(size:Int)
    }

    interface Presenter : BasePresenter {
        fun sendMsg(username: String, msg: String)
        fun addReceiverMsg(title: String, msgList: MutableList<EMMessage>?)
        fun loadInitMsg(title: String)
        fun loadMoreInitMsg(title: String)
    }

}