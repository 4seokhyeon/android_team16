package com.example.snsproject.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.snsproject.R
import com.example.snsproject.manager.MemberManager
import com.example.snsproject.manager.MemberManagerImpl

class MainPageActivity : AppCompatActivity() {
    lateinit var memberManager: MemberManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        memberManager = MemberManagerImpl.getInstance()
    }
}