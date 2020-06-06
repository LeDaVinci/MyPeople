package com.wanghang.mypeople

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**************************************************************
 * * Copyright (C), 2018-2028, OPPO Mobile Comm Corp., Ltd
 * * VENDOR_EDIT
 * * File: - PeopleRoomDatabase.java
 * * Description:
 * * Version: 1.0
 * * Date : 2020/6/6
 * * Author: wanghang
 * *
 * * ------------------- Revision History: -------------------
 * *   <author>       <data>       <version >      <desc>
 * *   wanghang       2020/6/6         1.0      build this module
 ****************************************************************/
@Database(entities = [People::class], version = 1)
abstract class PeopleRoomDatabase : RoomDatabase() {
    abstract fun peopleDao(): PeopleDao

    companion object {

        @Volatile
        private var INSTANCE: PeopleRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): PeopleRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PeopleRoomDatabase::class.java,
                    "people_database"
                )
                    .addCallback(PeopleDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class PeopleDatabaseCallback(private val scope: CoroutineScope) :
            RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.peopleDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(dao: PeopleDao) {
            dao.insertPeople(People("wanghang", 24, "湖北黄冈"))
        }
    }
}