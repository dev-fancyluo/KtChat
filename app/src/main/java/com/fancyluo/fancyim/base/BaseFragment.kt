package com.fancyluo.fancyim.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.startActivity

/**
 * fancyLuo
 * date: 2017/11/21 12:06
 * desc:
 */
abstract class BaseFragment : Fragment() {

    protected lateinit var mContext: Activity

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context as Activity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(setupLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init(view, savedInstanceState)
    }

    open fun init(view: View?, savedInstanceState: Bundle?) {

    }

    abstract fun setupLayout(): Int

    inline fun <reified T : BaseActivity> startActivityAndFinish(){
        `access$mContext`.startActivity<T>()
        `access$mContext`.finish()
    }

    @PublishedApi
    internal var `access$mContext`: Activity
        get() = mContext
        set(value) {
            mContext = value
        }

}