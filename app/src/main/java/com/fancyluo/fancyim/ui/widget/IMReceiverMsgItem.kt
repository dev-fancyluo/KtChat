package com.fancyluo.fancyim.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.fancyluo.fancyim.R
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import kotlinx.android.synthetic.main.im_send_msg_item.view.*
import java.sql.Date

/**
 * fancyLuo
 * date: 2017/11/30 08:02
 * desc:
 */
class IMReceiverMsgItem(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.im_receiver_msg_item, this)
    }

    fun bindView(emMessage: EMMessage, isShowTimestamp: Boolean) {
        // 设置时间戳
        if (isShowTimestamp) {
            textTimestamp.visibility = View.VISIBLE
            textTimestamp.text = DateUtils.getTimestampString(Date(emMessage.msgTime))
        } else textTimestamp.visibility = View.GONE
        // 设置消息
        val body = emMessage.body as EMTextMessageBody
        textSendMsg.text = body.message
    }

}