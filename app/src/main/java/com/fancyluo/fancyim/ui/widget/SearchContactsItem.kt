package com.fancyluo.fancyim.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.fancyluo.fancyim.R
import com.fancyluo.fancyim.bean.SearchContactsInfo
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import kotlinx.android.synthetic.main.widget_search_contacts_item.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

/**
 * fancyLuo
 * date: 2017/11/28 21:53
 * desc:
 */
class SearchContactsItem(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.widget_search_contacts_item, this)
    }

    fun bindView(item: SearchContactsInfo) {
        textUserName.text = item.username
        textTime.text = item.time
        if (item.isAdded){
            btn_add.isEnabled = false
            btn_add.text = "已添加"
        } else{
            btn_add.isEnabled = true
            btn_add.text = "添加"
            setAddClick(item)
        }
    }

    private fun setAddClick(item: SearchContactsInfo) {
        btn_add.setOnClickListener {
            addFriend(item.username)
        }
    }

    private fun addFriend(username: String) {
        doAsync {
            try {
                //参数为要添加的好友的username和添加理由
                EMClient.getInstance().contactManager().addContact(username,
                        EMClient.getInstance().currentUser+ "申请添加您为好友")
                uiThread { context.toast("添加好友成功") }
            } catch (e : HyphenateException){
                e.printStackTrace()
                uiThread { context.toast("添加好友失败") }
            }
        }
    }

}