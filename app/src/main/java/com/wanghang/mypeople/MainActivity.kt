package com.wanghang.mypeople

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()

        val peopleDao = PeopleRoomDatabase.getDatabase(context = this, scope = this).peopleDao()
        launch {
            var people:People?
            withContext(Dispatchers.IO) {
                people = peopleDao.getPeople("wanghang")
                println(people)
                textView2.text = people!!.name
                delay(5000)
            }
            println(Thread.currentThread().name)

            launch (Dispatchers.IO){
                println(Thread.currentThread().name)
                peopleDao.insertPeople(People("gaoguan", 24, "湖北黄冈"))
                println("insert gaoguan")
            }

            println("in")
        }


        println("xxxxx")
        println(Thread.currentThread().name)
        textView.text = "xxx"
    }
}