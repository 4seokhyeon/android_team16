package com.example.snsproject.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.example.snsproject.R
import com.example.snsproject.adapter.DetailItemAdapter
import com.example.snsproject.anim.slideLeft
import com.example.snsproject.anim.slideRight
import com.example.snsproject.manager.MemberManager
import com.example.snsproject.manager.MemberManagerImpl

class DetailPageActivity : AppCompatActivity() {
    lateinit var memberManager: MemberManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)
        memberManager = MemberManagerImpl.getInstance()

        val detailImg = findViewById<ImageView>(R.id.detail_img)
        val detailID = findViewById<TextView>(R.id.detail_IDInputText)
        val detailName = findViewById<TextView>(R.id.detail_NameInputText)
        val detailMBTI = findViewById<TextView>(R.id.detail_MBTIInputText)
        val detailStatus = findViewById<TextView>(R.id.detail_StatusInputText)
        val detailListView = findViewById<ListView>(R.id.detail_listview)
        val detailUndoBtn = findViewById<Button>(R.id.detail_Undo_Btn)



        val member = memberManager.findMember("test2")
        var postList = member?.post
        val imgIdx =  member?.profileImg

        println(imgIdx)
        detailID.text = member?.id
        detailName.text =  member?.name
        detailMBTI.text = member?.mbti
        detailStatus.text = member?.status
        var img = resources.getIdentifier(imgIdx,"drawable",packageName)
        detailImg.setImageResource(img)

        val detailAdapter = postList?.let { DetailItemAdapter(this, it) }
        detailListView.adapter = detailAdapter

        detailUndoBtn.setOnClickListener{
            var intent= Intent(this,MainPageActivity::class.java)
            startActivity(intent)
            slideRight()
        }


        }

    }
