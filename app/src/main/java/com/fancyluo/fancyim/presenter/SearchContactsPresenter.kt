package com.fancyluo.fancyim.presenter

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.fancyluo.fancyim.bean.SearchContactsInfo
import com.fancyluo.fancyim.contract.SearchContactsContract
import com.fancyluo.fancyim.db.DBHelper
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/**
 * fancyLuo
 * date: 2017/11/28 21:28
 * desc:
 */
class SearchContactsPresenter(val view: SearchContactsContract.View) : SearchContactsContract.Presenter {

    override fun searchContacts(username: String) {
        view.searchContactsStart()
        val query = BmobQuery<BmobUser>()
        query.addWhereContains("username", username)
                .addWhereNotEqualTo("username", EMClient.getInstance().currentUser)
        query.findObjects(object : FindListener<BmobUser>() {
            override fun done(users: List<BmobUser>, e: BmobException?) {
                if (e == null) {
                    doAsync {
                        var isAdded = false
                        val searchContactsList = mutableListOf<SearchContactsInfo>()
                        users.forEach { it ->
                            // 判断是否已存在联系人
                            DBHelper.instance.seleteContacts().forEach { contacts ->
                                isAdded = contacts.name == it.username
                            }
                            // 添加到列表集合
                            searchContactsList.add(
                                    SearchContactsInfo(it.username, it.createdAt, isAdded))
                        }
                        uiThread { view.searchContactsSuccess(searchContactsList) }
                    }
                } else view.searchContactsFailed("更新用户信息失败:${e.message}")
            }
        })
    }

}