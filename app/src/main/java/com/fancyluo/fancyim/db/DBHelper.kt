package com.fancyluo.fancyim.db

import com.fancyluo.fancyim.extentions.toVarargArray
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * fancyLuo
 * date: 2017/11/29 08:25
 * desc:
 */
class DBHelper private constructor(){

    companion object {
        val db = MyOpenHelper()
        val instance = DBHelper()
    }

    fun insertContacts(contacts: Contacts) {
        db.use {
            insert(ContactsTable.TABLE_NAME, *contacts.map.toVarargArray())
        }
    }

    fun seleteContacts(): List<Contacts> {
        return db.use {
            select(ContactsTable.TABLE_NAME).parseList(object : MapRowParser<Contacts> {
                override fun parseRow(columns: Map<String, Any?>): Contacts {
                    return Contacts(columns.toMutableMap())
                }
            })
        }
    }

    fun deleteAllContacts() {
        db.use { delete(ContactsTable.TABLE_NAME, null, null) }
    }

}