package com.fancyluo.fancyim.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.fancyluo.fancyim.R
import com.fancyluo.fancyim.ui.activity.ChatActivity
import com.hyphenate.chat.EMConversation
import com.hyphenate.chat.EMTextMessageBody
import kotlinx.android.synthetic.main.widget_conversation_item.view.*
import org.jetbrains.anko.startActivity
import java.sql.Date

/**
 * fancyLuo
 * date: 2017/12/1 20:05
 * desc:
 */
class ConversationItem(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.widget_conversation_item, this)
    }

    fun bindView(conversation: EMConversation) {
        textUserName.text = conversation.conversationId()
        textLastMsg.text = (conversation.lastMessage.body as EMTextMessageBody).message
        textTime.text = com.hyphenate.util.DateUtils.getTimestampString(Date(conversation.lastMessage.msgTime))
        if (conversation.unreadMsgCount > 0) {
            textMsgCount.visibility = View.VISIBLE
            if (conversation.unreadMsgCount < 99) textMsgCount.text = conversation.unreadMsgCount.toString()
            else textMsgCount.text = "..."
        } else {
            textMsgCount.visibility = View.GONE
        }

        setOnClickListener {
            context.startActivity<ChatActivity>("username" to conversation.conversationId())
        }
    }

}