package com.fancyluo.fancyim.ui

import com.fancyluo.fancyim.R
import com.fancyluo.fancyim.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun setupLayout(): Int =R.layout.activity_main

    override fun setupTitle(): String? = "KK"

    override fun showBack(): Boolean  = false



}
