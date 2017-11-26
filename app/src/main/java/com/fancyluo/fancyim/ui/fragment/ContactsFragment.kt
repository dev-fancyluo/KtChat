package com.fancyluo.fancyim.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.fancyluo.fancyim.R
import com.fancyluo.fancyim.base.BaseFragment
import com.fancyluo.fancyim.contract.ContactsContract
import com.fancyluo.fancyim.intefaces.EMContactListenerAdapter
import com.fancyluo.fancyim.presenter.ContactsPresenter
import com.fancyluo.fancyim.ui.adapter.ContactsAdapter
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.include_toolbar.*

/**
 * fancyLuo
 * date: 2017/11/24 22:20
 * desc: 联系人
 */
class ContactsFragment : BaseFragment(), ContactsContract.View {

    val mPresenter = ContactsPresenter(this)

    override fun setupLayout() = R.layout.fragment_contacts

    override fun init(view: View?, savedInstanceState: Bundle?) {
        super.init(view, savedInstanceState)

        mContext.toolbar.title = "联系人"

        // apply: 调用某对象的 apply 函数，在函数范围内，可以调用该对象的任意方法，并返回该对象

        swipeLayout.apply {
            setColorSchemeResources(R.color.primary_dark)
            isRefreshing = true
            setOnRefreshListener {
                mPresenter.getContactsList()
            }
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(mContext)
            adapter = ContactsAdapter(context, mPresenter.contactsInfoList)
        }

        EMClient.getInstance().contactManager().setContactListener(object : EMContactListenerAdapter() {
            override fun onContactDeleted(p0: String?) {
                mPresenter.getContactsList()
            }
        })

        mPresenter.getContactsList()

    }

    override fun getContactsListSuccess() {
        swipeLayout.isRefreshing = false
        tvFailed.visibility = View.GONE
        recyclerView.adapter.notifyDataSetChanged()
    }

    override fun getContactsListFailed() {
        swipeLayout.isRefreshing = false
        tvFailed.visibility = View.VISIBLE
    }


}