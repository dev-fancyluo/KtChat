package com.fancyluo.fancyim.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.fancyluo.fancyim.R
import com.fancyluo.fancyim.base.BaseFragment
import com.fancyluo.fancyim.intefaces.EMMessageListenerAdapter
import com.fancyluo.fancyim.ui.adapter.ConversationAdapter
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMConversation
import com.hyphenate.chat.EMMessage
import kotlinx.android.synthetic.main.fragment_conversation.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * fancyLuo
 * date: 2017/11/24 22:20
 * desc: 会话
 */
class ConversationFragment : BaseFragment() {

    private var allConversationList = mutableListOf<EMConversation>()

    override fun setupLayout() = R.layout.fragment_conversation

    override fun setupTitle() = "Kt Chat"

    override fun showBack() = false

    override fun init(view: View?, savedInstanceState: Bundle?) {
        super.init(view, savedInstanceState)
        initRecyclerView()
        initMsgListener()
    }

    private fun initRecyclerView() {
        recyclerConversation.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(mContext)
            adapter = ConversationAdapter(mContext,allConversationList)
        }
    }

    private fun loadAllConversation() {
        doAsync {
            allConversationList.clear()
            val allConversation = EMClient.getInstance().chatManager().allConversations
            allConversationList.addAll(allConversation.values)
            uiThread { recyclerConversation.adapter.notifyDataSetChanged() }
        }
    }

    private val msgListener = object : EMMessageListenerAdapter(){
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            loadAllConversation()
        }
    }

    private fun initMsgListener() {
        EMClient.getInstance().chatManager().addMessageListener(msgListener)
    }

    override fun onResume() {
        super.onResume()
        loadAllConversation()
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(msgListener)
    }

}