package com.fancyluo.fancyim.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.fancyluo.fancyim.ui.widget.IMReceiverMsgItem
import com.fancyluo.fancyim.ui.widget.IMSendMsgItem
import com.hyphenate.chat.EMMessage
import com.hyphenate.util.DateUtils

/**
 * fancyluo
 * date: 2017/11/30 09:52
 * desc:
 */
class ChatMsgAdapter(val context: Context, val msgList: MutableList<EMMessage>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        val TYPE_SEND = 1
        val TYPE_RECEIVER = 2
    }

    override fun getItemCount() = msgList.size

    override fun getItemViewType(position: Int) =
            if (msgList[position].direct() == EMMessage.Direct.SEND) TYPE_SEND else TYPE_RECEIVER

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val isShowTimestamp = isShowTimestamp(position)
        when (holder) {
            is SendMsgViewHolder -> {
                val imSendMsgItem = holder.itemView as IMSendMsgItem
                imSendMsgItem.bindView(msgList[position], isShowTimestamp)
            }
            is ReceiverMsgViewHolder -> {
                val imReceiverMsgItem = holder.itemView as IMReceiverMsgItem
                imReceiverMsgItem.bindView(msgList[position], isShowTimestamp)
            }
        }
    }

    private fun isShowTimestamp(position: Int): Boolean {
        var show = true
        if (position > 0) {
            show = !DateUtils.isCloseEnough(msgList[position].msgTime,msgList[position -1].msgTime)
        }
        return show
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            if (viewType == TYPE_SEND) SendMsgViewHolder(IMSendMsgItem(context))
            else ReceiverMsgViewHolder(IMReceiverMsgItem(context))

    class SendMsgViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    class ReceiverMsgViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

}