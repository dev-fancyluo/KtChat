package com.fancyluo.fancyim.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.fancyluo.fancyim.AppContext
import org.jetbrains.anko.db.*

/**
 * fancyLuo
 * date: 2017/11/29 08:13
 * desc:
 */
class MyOpenHelper(context: Context = AppContext.instance) : ManagedSQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {
        val NAME = "KK"
        val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(ContactsTable.TABLE_NAME,true,
                ContactsTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                ContactsTable.NAME to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.dropTable(ContactsTable.TABLE_NAME,true)
        onCreate(db)
    }

}