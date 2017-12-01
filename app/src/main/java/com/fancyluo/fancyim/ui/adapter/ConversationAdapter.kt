package com.fancyluo.fancyim.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.fancyluo.fancyim.ui.widget.ConversationItem
import com.hyphenate.chat.EMConversation

/**
 * fancyLuo
 * date: 2017/12/1 20:20
 * desc:
 */
class ConversationAdapter(val context: Context, val allConversationList: MutableList<EMConversation>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val item = holder?.itemView as ConversationItem
        item.bindView(allConversationList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = VH(ConversationItem(context))

    override fun getItemCount() = allConversationList.size

    class VH(itemView: View?) : RecyclerView.ViewHolder(itemView)

}