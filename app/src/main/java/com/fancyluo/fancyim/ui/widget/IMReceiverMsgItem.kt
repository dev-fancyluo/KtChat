package com.fancyluo.fancyim.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.fancyluo.fancyim.R

/**
 * fancyLuo
 * date: 2017/11/30 08:02
 * desc:
 */
class IMReceiverMsgItem(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.im_receiver_msg_item,this)
    }

}