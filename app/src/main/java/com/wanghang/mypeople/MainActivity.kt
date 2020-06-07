package com.wanghang.mypeople

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

open class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*MyLiveData.instance.observe(this, this, Observer {
            println("$it $localClassName")
            textView2.text = it
        })*/
    }

    override fun onResume() {
        super.onResume()
        val peopleDao = PeopleRoomDatabase.getDatabase(context = this, scope = this).peopleDao()
        launch {
            peopleDao.getPeoples().observe(this@MainActivity, Observer {
                it.forEach { _ ->
                    println(it)
                }
            })

            launch(Dispatchers.IO) {
                repeat(10) {
                    peopleDao.insertPeople(People("gaoguan$it", 24, "湖北黄冈"))
                    delay(5000)
                }
            }
        }

        MyLiveData.instance.observe(this, this, observer1)

        textView3.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

    private val observer1 = Observer<String> {
        println("$it $localClassName")
        textView2.text = it
    }
}