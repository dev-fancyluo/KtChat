package com.fancyluo.fancyim.contract

import com.fancyluo.fancyim.base.BasePresenter
import com.fancyluo.fancyim.base.BaseView

/**
 * fancyLuo
 * date: 2017/11/25 21:41
 * desc:
 */
class ContactsContract {

    interface View : BaseView {
        fun getContactsListSuccess()
        fun getContactsListFailed()
    }

    interface Presenter : BasePresenter {
        fun getContactsList()
    }

}