package com.fancyluo.fancyim.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.fancyluo.fancyim.R
import com.fancyluo.fancyim.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_conversation.*

/**
 * fancyLuo
 * date: 2017/11/24 22:20
 * desc: 会话
 */
class ConversationFragment : BaseFragment() {

    override fun setupLayout() = R.layout.fragment_conversation

    override fun setupTitle() = "Kt Chat"

    override fun showBack() = false

    override fun init(view: View?, savedInstanceState: Bundle?) {
        super.init(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerConversation.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(mContext)
        }
    }

}