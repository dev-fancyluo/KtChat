package com.fancyluo.fancyim.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.fancyluo.fancyim.bean.SearchContactsInfo
import com.fancyluo.fancyim.ui.widget.SearchContactsItem

/**
 * fancyLuo
 * date: 2017/11/28 21:56
 * desc:
 */
class SearchContactsListAdapter(
        val context: Context,
        private val searchContactsList: MutableList<SearchContactsInfo>) :
        RecyclerView.Adapter<SearchContactsListAdapter.MyViewHolder>() {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = holder.itemView as SearchContactsItem
        item.bindView(searchContactsList[position])
    }

    override fun getItemCount() = searchContactsList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        return MyViewHolder(SearchContactsItem(context))
    }


    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

}