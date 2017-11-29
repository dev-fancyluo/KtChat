package com.fancyluo.fancyim.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.fancyluo.fancyim.R
import com.fancyluo.fancyim.base.BaseActivity
import com.fancyluo.fancyim.bean.SearchContactsInfo
import com.fancyluo.fancyim.contract.SearchContactsContract
import com.fancyluo.fancyim.presenter.SearchContactsPresenter
import com.fancyluo.fancyim.ui.adapter.SearchContactsListAdapter
import kotlinx.android.synthetic.main.activity_search_contacts.*
import org.jetbrains.anko.toast

/**
 * fancyLuo
 * date: 2017/11/28 21:04
 * desc:
 */
class SearchContactsActivity : BaseActivity(), SearchContactsContract.View {

    val presenter = SearchContactsPresenter(this)

    override fun setupLayout() = R.layout.activity_search_contacts

    override fun setupTitle() = "添加联系人"

    override fun init() {
        super.init()

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }

        editUsername.setOnEditorActionListener { _, _, _ ->
            val username = editUsername.text.trim().toString()
            presenter.searchContacts(username)
            true
        }
    }

    override fun searchContactsStart() {
        showProgressDialog()
    }

    override fun searchContactsSuccess(searchContactsList: MutableList<SearchContactsInfo>) {
        hideProgressDialog()
        toast("搜索成功")
        recyclerView.adapter = SearchContactsListAdapter(this, searchContactsList)
    }

    override fun searchContactsFailed(s: String) {
        hideProgressDialog()
        toast(s)
    }

}