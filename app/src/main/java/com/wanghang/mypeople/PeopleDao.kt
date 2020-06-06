package com.wanghang.mypeople

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**************************************************************
 * * Copyright (C), 2018-2028, OPPO Mobile Comm Corp., Ltd
 * * VENDOR_EDIT
 * * File: - PeopleDao.java
 * * Description:
 * * Version: 1.0
 * * Date : 2020/6/6
 * * Author: wanghang
 * *
 * * ------------------- Revision History: -------------------
 * *   <author>       <data>       <version >      <desc>
 * *   wanghang       2020/6/6         1.0      build this module
 ****************************************************************/
@Dao
interface PeopleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = People::class)
    fun insertPeople(people: People)

    @Query("SELECT * FROM people WHERE name= :name LIMIT 1")
    fun getPeople(name: String):People
}