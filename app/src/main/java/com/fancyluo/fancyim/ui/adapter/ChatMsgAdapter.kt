package com.fancyluo.fancyim.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.fancyluo.fancyim.ui.widget.IMReceiverMsgItem
import com.fancyluo.fancyim.ui.widget.IMSendMsgItem
import com.hyphenate.chat.EMMessage

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
        when (holder) {
            is SendMsgViewHolder -> {
                val imSendMsgItem = holder.itemView as IMSendMsgItem
                imSendMsgItem.bindView(msgList[position], if (position == 0) null else msgList[position - 1])
            }
            is ReceiverMsgViewHolder -> {
                val imReceiverMsgItem = holder?.itemView as IMReceiverMsgItem
                imReceiverMsgItem.bindView(msgList[position], if (position == 0) null else msgList[position - 1])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            if (viewType == TYPE_SEND) SendMsgViewHolder(IMSendMsgItem(context))
            else ReceiverMsgViewHolder(IMReceiverMsgItem(context))

    class SendMsgViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    class ReceiverMsgViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

}