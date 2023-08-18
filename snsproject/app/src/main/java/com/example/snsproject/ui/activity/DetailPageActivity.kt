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

        val detailImg = findViewById<ImageView>(R.id.detail_img) //이미지뷰 변수
        val detailID = findViewById<TextView>(R.id.detail_IDInputText) //사용자 ID 텍스트뷰
        val detailName = findViewById<TextView>(R.id.detail_NameInputText) // 사용자 이름 텍스트뷰
        val detailMBTI = findViewById<TextView>(R.id.detail_MBTIInputText) // 사용자 MBTI 텍스트뷰
        val detailStatus = findViewById<TextView>(R.id.detail_StatusInputText) // 사용자 상태 텍스트뷰
        val detailListView = findViewById<ListView>(R.id.detail_listview)// 리스트뷰
        val detailUndoBtn = findViewById<Button>(R.id.detail_Undo_Btn)// 뒤로가기 버튼




        val id = intent.getStringExtra("Id").toString()
        val member = memberManager.findMember(id) //멤버변수 설정 FindMember 사용 - 추후 Detail_ID 입력
        var postList = member?.post// 찾은 멤버의 Post리스트 지정
        val imgIdx =  member?.profileImg//이미지 제목 불러오기

        detailID.text = member?.id //텍스트뷰에 자료값들 입력
        detailName.text =  member?.name
        detailMBTI.text = member?.mbti
        detailStatus.text = member?.status
        if(!imgIdx.isNullOrEmpty())  {
            var img = resources.getIdentifier(imgIdx,"drawable",packageName)//setImageResource를 사용하기위해 getIdentifier를 이용하여 이미지의 인덱스값 추출
            detailImg.setImageResource(img)//detail 이미지뷰에 이미지 출력
        }



        val detailAdapter = postList?.let { DetailItemAdapter(this, it) }// ListView출력을 위해 adapter 지정
        detailListView.adapter = detailAdapter// ListView 출력

        detailUndoBtn.setOnClickListener{//뒤로가기 버튼이 눌렸을 때,
            finish()
            slideLeft()// 오른쪽으로 넘기는 애니메이션 추가
        }




        }

    override fun onBackPressed(){//핸드폰 뒤로가기 버튼 눌렀을 때,

        finish()
        slideLeft()// 오른쪽으로 넘기는 애니메이션 추가
    }

    }
