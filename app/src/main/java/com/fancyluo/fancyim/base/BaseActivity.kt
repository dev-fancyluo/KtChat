package com.fancyluo.fancyim.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import com.afollestad.materialdialogs.MaterialDialog
import com.fancyluo.fancyim.R
import kotlinx.android.synthetic.main.include_toolbar.*
import org.jetbrains.anko.startActivity


/**
 * fancyLuo
 * date: 2017/11/21 11:57
 * desc:
 */
abstract class BaseActivity : AppCompatActivity() {

    private var mProgressDialog: MaterialDialog? = null

    private val inputManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setupLayout())
        initToolbar()
        init()
    }

    private fun initToolbar() {
        if (toolbar == null) return
        toolbar.title = setupTitle()
        if (showBack()) {
            toolbar.setNavigationIcon(R.drawable.btn_return)
            toolbar.setNavigationOnClickListener { finish() }
        }
        if (setupMenu() != 0){
            toolbar.inflateMenu(setupMenu())
            toolbar.setOnMenuItemClickListener { it ->
                onMenuClick(it)
                false
            }
        }
    }

    open fun onMenuClick(item: MenuItem?){}

    open fun setupMenu(): Int = 0

    open fun showBack(): Boolean = true

    open fun setupTitle(): String? = null

    abstract fun setupLayout(): Int

    /**
     * 初始化一些公共功能
     * 子类也可覆写该方法，完成自己的初始化
     */
    open fun init() {

    }

    fun showProgressDialog(content: String = "正在加载中...") {
        mProgressDialog = MaterialDialog.Builder(this)
                .progress(true, 0)
                .content(content).show()
    }

    fun hideProgressDialog() {
        mProgressDialog?.dismiss()
    }

    fun hideSoftKeyboard() {
        inputManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }

    inline fun <reified T : BaseActivity> startActivityAndFinish(){
        startActivity<T>()
        finish()
    }

}