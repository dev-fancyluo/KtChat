package com.fancyluo.fancyim.ui.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.afollestad.materialdialogs.MaterialDialog
import com.fancyluo.fancyim.R
import com.fancyluo.fancyim.bean.ContactsInfo
import com.fancyluo.fancyim.intefaces.EMCallbackAdapter
import com.fancyluo.fancyim.ui.activity.ChatActivity
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.widget_contacts_item.view.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * fancyLuo
 * date: 2017/11/25 20:24
 * desc:
 */
class ContactsListItem(context: Context, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.widget_contacts_item, this)
    }

    fun bindView(contactsInfo: ContactsInfo) {
        if (contactsInfo.showLetter) {
            tvLetter.visibility = View.VISIBLE
            tvLetter.text = contactsInfo.letter.toString()
        } else {
            tvLetter.visibility = View.GONE
        }
        tvUserName.text = contactsInfo.username

        setOnClickListener {
            context.startActivity<ChatActivity>("username" to contactsInfo.username)
        }

        setOnLongClickListener {
            MaterialDialog.Builder(context)
                    .content("确定要删除${contactsInfo.username}吗")
                    .negativeText("取消")
                    .positiveColor(Color.RED)
                    .positiveText("确定")
                    .onPositive { dialog, _ ->
                        dialog.dismiss()
                        deleteContacts(contactsInfo.username)
                    }
                    .show()
            true
        }

    }

    private fun deleteContacts(username: String) {
        EMClient.getInstance().contactManager().aysncDeleteContact(username,object : EMCallbackAdapter() {
            override fun onSuccess() {
                context.runOnUiThread { toast("删除${username}成功") }
            }

            override fun onError(p0: Int, p1: String?) {
                context.runOnUiThread { toast("删除${username}失败") }
            }
        })
    }

}