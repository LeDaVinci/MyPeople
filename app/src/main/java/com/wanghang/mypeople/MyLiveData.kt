package com.wanghang.mypeople

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

class MyLiveData : LiveData<String>() {

    companion object {
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            MyLiveData()
        }
    }

    fun set(str: String) {
        value = str
    }

    fun observe(activity: AppCompatActivity, owner: LifecycleOwner, observer: Observer<in String>) {
//        println(activity.localClassName + " livedata")
        super.observe(owner, observer)
    }
}