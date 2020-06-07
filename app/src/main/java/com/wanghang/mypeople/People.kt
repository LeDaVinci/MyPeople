package com.wanghang.mypeople

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**************************************************************
 * * Copyright (C), 2018-2028, OPPO Mobile Comm Corp., Ltd
 * * VENDOR_EDIT
 * * File: - People.java
 * * Description:
 * * Version: 1.0
 * * Date : 2020/6/6
 * * Author: wanghang
 * *
 * * ------------------- Revision History: -------------------
 * *   <author>       <data>       <version >      <desc>
 * *   wanghang       2020/6/6         1.0      build this module
 ****************************************************************/
@Entity(tableName = "people")
data class People(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "age", defaultValue = "18") val age: Int, @ColumnInfo val address: String?
)