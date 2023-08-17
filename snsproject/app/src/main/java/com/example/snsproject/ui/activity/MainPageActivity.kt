package com.example.snsproject.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import com.example.snsproject.R
import com.example.snsproject.adapter.MainPageItemAdapter
import com.example.snsproject.manager.MemberManager
import com.example.snsproject.manager.MemberManagerImpl

class MainPageActivity : AppCompatActivity() {
    private lateinit var memberManager: MemberManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        memberManager = MemberManagerImpl.getInstance()

        val listView: ListView = findViewById(R.id.main_page_listview)

        val allMembers = memberManager.findAllMember()
        val adapter = MainPageItemAdapter(this,allMembers.flatMap { it.post })
        listView.adapter = adapter

        val myImgButton = findViewById<ImageButton>(R.id.my_img)

        myImgButton.setOnClickListener {
            // 클릭 시 토스트 메시지 띄우기
            Toast.makeText(this, "ImageButton 클릭됨", Toast.LENGTH_SHORT).show()
        }
    }
}