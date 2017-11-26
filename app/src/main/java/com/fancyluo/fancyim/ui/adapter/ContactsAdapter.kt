package com.fancyluo.fancyim.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.fancyluo.fancyim.bean.ContactsInfo
import com.fancyluo.fancyim.ui.widget.ContactsListItem

/**
 * fancyLuo
 * date: 2017/11/25 21:25
 * desc:
 */
class ContactsAdapter(private val context: Context, private val contactsInfoList: MutableList<ContactsInfo>) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(ContactsListItem(context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contactsListItem = holder.itemView as ContactsListItem
        contactsListItem.bindView(contactsInfoList[position])
    }

    override fun getItemCount() = contactsInfoList.size

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

}