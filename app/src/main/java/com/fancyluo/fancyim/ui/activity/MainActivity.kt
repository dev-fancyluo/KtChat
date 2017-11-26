package com.fancyluo.fancyim.ui.activity

import com.fancyluo.fancyim.R
import com.fancyluo.fancyim.base.BaseActivity
import com.fancyluo.fancyim.ui.fragment.TabFragmentFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun setupLayout(): Int = R.layout.activity_main

    override fun setupTitle(): String? = "KK"

    override fun showBack(): Boolean = false

    override fun init() {
        super.init()
        initTab()
    }

    private fun initTab() {
        tab_conversation.setCompoundDrawablesWithIntrinsicBounds(
                0, R.drawable.tab_conversation_press, 0, 0)
        tab_conversation.setTextColor(resources.getColor(R.color.colorPrimaryDark))

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container,
                TabFragmentFactory.instance.getTabFragment(R.id.tab_conversation)).commit()

        tab_conversation.setOnClickListener {
            setTabPress(R.id.tab_conversation)
        }

        tab_contacts.setOnClickListener {
            setTabPress(R.id.tab_contacts)
        }

        tab_mine.setOnClickListener {
            setTabPress(R.id.tab_mine)
        }
    }

    private fun setTabPress(resId: Int) {
        clearTabState()
        val transaction = supportFragmentManager.beginTransaction()
        when (resId) {
            R.id.tab_conversation -> {
                tab_conversation.setCompoundDrawablesWithIntrinsicBounds(
                        0, R.drawable.tab_conversation_press, 0, 0)
                tab_conversation.setTextColor(resources.getColor(R.color.colorPrimaryDark))
            }
            R.id.tab_contacts -> {
                tab_contacts.setCompoundDrawablesWithIntrinsicBounds(
                        0, R.drawable.tab_contacts_press, 0, 0)
                tab_contacts.setTextColor(resources.getColor(R.color.colorPrimaryDark))
            }
            R.id.tab_mine -> {
                tab_mine.setCompoundDrawablesWithIntrinsicBounds(
                        0, R.drawable.tab_mine_press, 0, 0)
                tab_mine.setTextColor(resources.getColor(R.color.colorPrimaryDark))
            }
        }
        transaction.replace(R.id.container,
                TabFragmentFactory.instance.getTabFragment(resId)).commit()
    }

    private fun clearTabState() {
        tab_conversation.setCompoundDrawablesWithIntrinsicBounds(
                0, R.drawable.tab_conversation_nomal, 0, 0)
        tab_conversation.setTextColor(resources.getColor(R.color.secondary_text))
        tab_contacts.setCompoundDrawablesWithIntrinsicBounds(
                0, R.drawable.tab_contacts_nomal, 0, 0)
        tab_contacts.setTextColor(resources.getColor(R.color.secondary_text))
        tab_mine.setCompoundDrawablesWithIntrinsicBounds(
                0, R.drawable.tab_mine_nomal, 0, 0)
        tab_mine.setTextColor(resources.getColor(R.color.secondary_text))
    }


}
