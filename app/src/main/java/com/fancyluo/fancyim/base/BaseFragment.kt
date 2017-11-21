package com.fancyluo.fancyim.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * fancyLuo
 * date: 2017/11/21 12:06
 * desc:
 */
abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
       = inflater?.inflate(setupLayout(),container,false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        init(view,savedInstanceState)
    }

    open fun init(view: View?, savedInstanceState: Bundle?) {

    }

    abstract fun setupLayout(): Int

}