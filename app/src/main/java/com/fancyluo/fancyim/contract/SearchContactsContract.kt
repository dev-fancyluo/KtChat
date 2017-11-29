package com.fancyluo.fancyim.contract

import com.fancyluo.fancyim.base.BasePresenter
import com.fancyluo.fancyim.base.BaseView
import com.fancyluo.fancyim.bean.SearchContactsInfo

/**
 * fancyLuo
 * date: 2017/11/28 21:26
 * desc:
 */
class SearchContactsContract {

    interface View : BaseView {
        fun searchContactsStart()
        fun searchContactsSuccess(searchContactsList: MutableList<SearchContactsInfo>)
        fun searchContactsFailed(s: String)
    }

    interface Presenter : BasePresenter {
        fun searchContacts(username: String)
    }

}