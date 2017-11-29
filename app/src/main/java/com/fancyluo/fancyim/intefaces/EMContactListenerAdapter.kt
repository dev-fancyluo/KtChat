package com.fancyluo.fancyim.intefaces

import com.hyphenate.EMContactListener

/**
 * fancyLuo
 * date: 2017/11/26 12:18
 * desc:
 */
open class EMContactListenerAdapter : EMContactListener {
    override fun onContactInvited(p0: String?, p1: String?) {

    }

    override fun onContactDeleted(p0: String?) {
    }

    override fun onFriendRequestAccepted(p0: String?) {
    }

    override fun onContactAdded(p0: String?) {
    }

    override fun onFriendRequestDeclined(p0: String?) {
    }
}