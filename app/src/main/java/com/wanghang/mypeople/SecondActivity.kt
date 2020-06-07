package com.wanghang.mypeople

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class SecondActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        textView.setOnClickListener {
            MyLiveData.instance.set("xxxx")
        }
    }
}