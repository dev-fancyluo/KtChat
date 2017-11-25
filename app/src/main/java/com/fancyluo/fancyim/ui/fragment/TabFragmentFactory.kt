package com.fancyluo.fancyim.ui.fragment

import android.support.v4.app.Fragment
import com.fancyluo.fancyim.R

/**
 * fancyLuo
 * date: 2017/11/24 22:35
 * desc:
 */
class TabFragmentFactory private constructor() {

    companion object {
        val instance = TabFragmentFactory()
    }

    private val conversation by lazy {
        ConversationFragment()
    }

    private val contacts by lazy {
        ContactsFragment()
    }

    private val mine by lazy {
        MineFragment()
    }

    fun getTabFragment(resId: Int): Fragment? {
        return when (resId) {
            R.id.tab_conversation -> conversation
            R.id.tab_contacts -> contacts
            R.id.tab_mine -> mine
            else -> null
        }
    }

}