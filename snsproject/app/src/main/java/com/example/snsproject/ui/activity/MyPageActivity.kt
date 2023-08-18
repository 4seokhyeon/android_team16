package com.example.snsproject.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import com.example.snsproject.R
import com.example.snsproject.adapter.MyPageItemAdapter
import com.example.snsproject.anim.slideLeft
import com.example.snsproject.anim.slideRight
import com.example.snsproject.manager.MemberManager
import com.example.snsproject.manager.MemberManagerImpl
import de.hdodenhof.circleimageview.CircleImageView

class MyPageActivity : AppCompatActivity() {
    private lateinit var memberManager : MemberManager
    private lateinit var curId : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        memberManager = MemberManagerImpl.getInstance()
        curId = intent.getStringExtra("userId") ?: "test1"
        val listview = findViewById<ListView>(R.id.mypage_listview)
        val member = memberManager.findMember(curId)


        initView()

        if (member != null) {
            listview.adapter = MyPageItemAdapter(this, member.post)
        }

        val modifyButton = findViewById<TextView>(R.id.mypage_modify_button)

        modifyButton.setOnClickListener {
            val modifyDialog = InfoModifyDialog(this, curId)
            modifyDialog.show()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        val id = findViewById<TextView>(R.id.mypage_tv_id)
        val name = findViewById<TextView>(R.id.mypage_tv_name)
        val mbti = findViewById<TextView>(R.id.mypage_tv_mbti)
        val status = findViewById<TextView>(R.id.mypage_tv_status)
        val backButton = findViewById<ImageButton>(R.id.mypage_back_btn)
        val profile = findViewById<CircleImageView>(R.id.mypage_profile_picture)
        val member = memberManager.findMember(curId)!!

        id.text = "ID : ${member.id}"
        name.text = "NAME : ${member.name}"
        mbti.text = "MBTI : ${member.mbti}"
        status.text = member.status

        val profileImg = if (member.profileImg.isEmpty()) "blank_img" else member.profileImg
        val profileId = resources.getIdentifier(profileImg, "drawable", packageName)
        profile.setImageResource(profileId)

        backButton.setOnClickListener {
            finish()
            slideLeft()
        }
    }
    override fun onBackPressed() {
        finish()
        slideLeft()
    }

    @SuppressLint("SetTextI18n")
    fun dataChangeListener() {
        val id = findViewById<TextView>(R.id.mypage_tv_id)
        val name = findViewById<TextView>(R.id.mypage_tv_name)
        val mbti = findViewById<TextView>(R.id.mypage_tv_mbti)
        val status = findViewById<TextView>(R.id.mypage_tv_status)
        val member = memberManager.findMember(curId)!!

        id.text = "ID : ${member.id}"
        name.text = "NAME : ${member.name}"
        mbti.text = "MBTI : ${member.mbti}"
        status.text = member.status
    }

}