package com.fancyluo.fancyim.presenter

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.fancyluo.fancyim.bean.SearchContactsInfo
import com.fancyluo.fancyim.contract.SearchContactsContract
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
                        val searchContactsList = mutableListOf<SearchContactsInfo>()
                        users.forEach {
                            searchContactsList.add(
                                    SearchContactsInfo(it.username, it.createdAt, false))
                        }
                        uiThread { view.searchContactsSuccess(searchContactsList) }
                    }
                } else view.searchContactsFailed("更新用户信息失败:${e.message}")
            }
        })
    }

}