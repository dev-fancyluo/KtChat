package com.fancyluo.fancyim.contract

import com.fancyluo.fancyim.base.BasePresenter
import com.fancyluo.fancyim.base.BaseView

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
    }

    interface Presenter : BasePresenter {
        fun sendMsg(username: String, msg: String)
    }

}