package com.example.snsproject.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.snsproject.R
import com.example.snsproject.adapter.MainPageItemAdapter
import com.example.snsproject.manager.MemberManager
import com.example.snsproject.manager.MemberManagerImpl
import com.google.android.material.snackbar.Snackbar

class MainPageActivity : AppCompatActivity() {
private lateinit var memberManager: MemberManager
    private lateinit var coordinatorLayout: CoordinatorLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        memberManager = MemberManagerImpl.getInstance()
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        val listView: ListView = findViewById(R.id.main_page_listview)

        val allMembers = memberManager.findAllMember()
        val adapter = MainPageItemAdapter(this,allMembers.flatMap { it.post })
        listView.adapter = adapter

        val myImgButton = findViewById<ImageButton>(R.id.my_img)




        myImgButton.setOnClickListener {
            // 클릭 시 토스트 메시지 띄우기
            showSnackbar("안녀아세욤ㄴ공지사항입니다")
        }
    }

    private fun showSnackbar(message: String){
        Snackbar.make(coordinatorLayout,message,Snackbar.LENGTH_LONG).show()
    }
}