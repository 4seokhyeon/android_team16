package com.example.snsproject.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import com.example.snsproject.R
import com.example.snsproject.adapter.MyPageItemAdapter
import com.example.snsproject.manager.MemberManager
import com.example.snsproject.manager.MemberManagerImpl

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}