package com.fancyluo.fancyim.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.fancyluo.fancyim.R
import com.fancyluo.fancyim.bean.SearchContactsInfo
import kotlinx.android.synthetic.main.widget_search_contacts_item.view.*

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
    }

}