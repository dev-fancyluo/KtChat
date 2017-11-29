package com.fancyluo.fancyim.presenter

import com.fancyluo.fancyim.bean.ContactsInfo
import com.fancyluo.fancyim.contract.ContactsContract
import com.fancyluo.fancyim.db.Contacts
import com.fancyluo.fancyim.db.DBHelper
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/**
 * fancyLuo
 * date: 2017/11/25 21:43
 * desc:
 */
class ContactsPresenter(val view: ContactsContract.View) : ContactsContract.Presenter {
    val contactsInfoList = mutableListOf<ContactsInfo>()
    override fun getContactsList() {
        doAsync {
            try {
                contactsInfoList.clear()
                DBHelper.instance.deleteAllContacts()
                val usernameList = EMClient.getInstance().contactManager().allContactsFromServer
                if (usernameList.size == 0) {
                    uiThread { view.getContactsListFailed() }
                    return@doAsync
                }

                usernameList.sortBy { it[0] }
                usernameList.forEachIndexed { index, s ->
                    // 第一条 Item 显示头部
                    // 当前 Item 首字母与上一条 Item 首字母不相同时显示头部
                    val showLetter = index == 0 || usernameList[index][0] != usernameList[index - 1][0]
                    contactsInfoList.add(ContactsInfo(s[0].toUpperCase(), s, showLetter))
                    // 保存到数据库
                    DBHelper.instance.insertContacts(Contacts(mutableMapOf("name" to s)))
                }
                uiThread { view.getContactsListSuccess() }
            } catch (e: HyphenateException) {
                uiThread { view.getContactsListFailed() }
            }
        }
    }


}