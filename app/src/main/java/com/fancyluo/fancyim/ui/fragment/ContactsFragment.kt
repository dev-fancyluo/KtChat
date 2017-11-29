package com.fancyluo.fancyim.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.fancyluo.fancyim.R
import com.fancyluo.fancyim.base.BaseFragment
import com.fancyluo.fancyim.contract.ContactsContract
import com.fancyluo.fancyim.intefaces.EMContactListenerAdapter
import com.fancyluo.fancyim.presenter.ContactsPresenter
import com.fancyluo.fancyim.ui.activity.SearchContactsActivity
import com.fancyluo.fancyim.ui.adapter.ContactsAdapter
import com.fancyluo.fancyim.ui.widget.SlideBar
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_contacts.*
import org.jetbrains.anko.startActivityForResult

/**
 * fancyLuo
 * date: 2017/11/24 22:20
 * desc: 联系人
 */
class ContactsFragment : BaseFragment(), ContactsContract.View {

    val mPresenter = ContactsPresenter(this)

    override fun setupLayout() = R.layout.fragment_contacts

    override fun setupTitle(): String? = "联系人"

    override fun showBack() = false

    override fun setupMenu() = R.menu.menu_add_contacts

    override fun onMenuClick(item: MenuItem?) = mContext.startActivityForResult<SearchContactsActivity>(10086)


    override fun init(view: View?, savedInstanceState: Bundle?) {
        super.init(view, savedInstanceState)

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

        slideBar.setOnSlideSelectListener(object : SlideBar.OnSlideSelectListener {
            override fun onSelectLetter(letter: String) {
                tvCenter.text = letter
                tvCenter.visibility = View.VISIBLE
                val position = getPosition(letter)
                recyclerView.smoothScrollToPosition(position)
            }

            override fun onUnSelectLetter() {
                tvCenter.visibility = View.GONE
            }
        })

        mPresenter.getContactsList()

    }

    // 使用二分查找计算滚动位置
    private fun getPosition(letter: String) = mPresenter.contactsInfoList.binarySearch { item ->
        item.letter.minus(letter[0])
    }

    override fun getContactsListSuccess() {
        swipeLayout?.isRefreshing = false
        tvFailed?.visibility = View.GONE
        recyclerView.adapter.notifyDataSetChanged()
    }

    override fun getContactsListFailed() {
        swipeLayout?.isRefreshing = false
        tvFailed?.visibility = View.VISIBLE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10086 && resultCode == Activity.RESULT_OK){
            mPresenter.getContactsList()
        }
    }


}