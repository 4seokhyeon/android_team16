package com.example.snsproject.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import com.example.snsproject.R
import com.example.snsproject.adapter.MyPageItemAdapter
import com.example.snsproject.manager.MemberManager
import com.example.snsproject.manager.MemberManagerImpl

class MyPageActivity : AppCompatActivity() {
    lateinit var memberManager : MemberManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        memberManager = MemberManagerImpl.getInstance()
        val listview = findViewById<ListView>(R.id.mypage_listview)
        val member = memberManager.findMember("test1")

        initView()

        if (member != null) {
            listview.adapter = MyPageItemAdapter(this, member.post)
        }

        val modifyButton = findViewById<TextView>(R.id.mypage_modify_button)

        modifyButton.setOnClickListener {
            val modifyDialog = InfoModifyDialog(this, "test1")
            modifyDialog.show()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        val id = findViewById<TextView>(R.id.mypage_tv_id)
        val name = findViewById<TextView>(R.id.mypage_tv_name)
        val mbti = findViewById<TextView>(R.id.mypage_tv_mbti)
        val status = findViewById<TextView>(R.id.mypage_tv_status)
        val member = memberManager.findMember("test1")!!

        id.text = "ID : ${member.id}"
        name.text = "NAME : ${member.name}"
        mbti.text = "MBTI : ${member.mbti}"
        status.text = member.status


    }

    @SuppressLint("SetTextI18n")
    fun dataChangeListener() {
        val id = findViewById<TextView>(R.id.mypage_tv_id)
        val name = findViewById<TextView>(R.id.mypage_tv_name)
        val mbti = findViewById<TextView>(R.id.mypage_tv_mbti)
        val status = findViewById<TextView>(R.id.mypage_tv_status)
        val member = memberManager.findMember("test1")!!

        id.text = "ID : ${member.id}"
        name.text = "NAME : ${member.name}"
        mbti.text = "MBTI : ${member.mbti}"
        status.text = member.status
    }

}