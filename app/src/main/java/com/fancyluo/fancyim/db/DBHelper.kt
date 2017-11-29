package com.fancyluo.fancyim.db

/**
 * fancyLuo
 * date: 2017/11/29 08:25
 * desc:
 */
class DBHelper {

    companion object {
        val db = MyOpenHelper()
    }

    fun insertContacts(){
        db.use {
//            insert(ContactsTable.TABLE_NAME,"",)
        }
    }

}