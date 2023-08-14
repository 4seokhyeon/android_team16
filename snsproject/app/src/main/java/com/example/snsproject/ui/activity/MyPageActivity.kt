package com.example.snsproject.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.snsproject.R
import com.example.snsproject.adapter.MyPageItemAdapter

class MyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

//        val listview = findViewById<ListView>(R.id.mypage_listview)
//        listview.adapter = MyPageItemAdapter(this, ArrayList<Any>())
    }
}