package com.example.snsproject.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.snsproject.R
import com.example.snsproject.adapter.MainPageItemAdapter
import com.example.snsproject.manager.MemberManager
import com.example.snsproject.manager.MemberManagerImpl
import com.example.snsproject.model.Member
import com.google.android.material.snackbar.Snackbar
import de.hdodenhof.circleimageview.CircleImageView
import org.w3c.dom.Text


class MainPageActivity : AppCompatActivity() {
    private lateinit var memberManager: MemberManager
    private lateinit var coordinatorLayout: CoordinatorLayout //알람 상태바인 스냅바를 받아옴
    private lateinit var userId:String //유저 아이디를 받아옴
    private lateinit var user: Member

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        memberManager = MemberManagerImpl.getInstance()

        val listView: ListView = findViewById(R.id.main_page_listview)

        userId = intent.getStringExtra("userId") ?:" "

        user = memberManager.findMember(userId) ?: Member("","","","","","", mutableListOf())

        val welcomText: TextView = findViewById(R.id.welcom_text)
        welcomText.text = "환영합니다 ! ${user.name}님!"

        val allMembers = memberManager.findAllMember()
        val adapter = MainPageItemAdapter(this,allMembers.flatMap { it.post })
        listView.adapter = adapter

        val myImagBtn: ImageButton = findViewById(R.id.imageBtn1)
        val userImageResource1 =  getProfileImageResource(user.profileImg)
        myImagBtn.setImageResource(userImageResource1)
        myImagBtn.setOnClickListener{
            val intent = Intent(this,MyPageActivity::class.java)
            startActivity(intent)
        }


        val myImagButton: CircleImageView = findViewById(R.id.my_img)
        val textView1: TextView = findViewById(R.id.user)
        textView1.text = "${user.name}"
        val userImageResource = getProfileImageResource(user.profileImg)
        myImagButton.setImageResource(userImageResource)
        myImagButton.setOnClickListener{
            //클릭하면 사용자 프로필 화면으로  가는 로직 & 데이터 넘김 처리
            val intent = Intent(this, MyPageActivity::class.java)
            intent.putExtra("userId",userId)
            startActivity(intent)
        }




        val scrollView:HorizontalScrollView = findViewById(R.id.horizontalScrollView)
        setupDynamicItems()

    }

    private fun setupDynamicItems() {
        val mainPageView: LinearLayout = findViewById(R.id.main_page_view)
        val memberManager = MemberManagerImpl.getInstance()
        val memberList = memberManager.findAllMember()

        val loginMember = memberManager.findMember(userId)

        // 첫 번째 이미지 버튼과 텍스트는 XML 레이아웃에 이미 정의되어 있음
        // 따라서 두 번째부터 열번째까지 동적으로 아이템을 추가해줌
        for (member in memberList) {
            if(member != loginMember) {   //리스트 안에서 맴버의 로그인 정보를 조회해서 로그인 정보를 뺴고 담음
                val itemView = layoutInflater.inflate(R.layout.main_page_item, mainPageView, false)
                configureItemView(itemView, member)
                mainPageView.addView(itemView)
            }
        }

    }

    private fun configureItemView(itemView: View, member: Member) {
        val profileImageButton: ImageButton = itemView.findViewById(R.id.per_image_button)
        val nameTextView: TextView = itemView.findViewById(R.id.per_text1)

        val userImageResource = getProfileImageResource(member.profileImg)
        profileImageButton.setImageResource(userImageResource)
        nameTextView.text = member.name

        profileImageButton.setOnClickListener {
            // Handle ImageButton click event here
        }
    }



    private fun getProfileImageResource(profileImg: String): Int {
        return when (profileImg) {
            "dakyum" -> R.drawable.dakyum
            "hyemyung" -> R.drawable.hyemyung
            "sukhyun" -> R.drawable.sukhyun
            "kwanghee" -> R.drawable.kwanghee
            "keuntae" -> R.drawable.keuntae
            else -> R.drawable.blank_img // 기본 이미지 리소스 ID (예: 미정의 프로필 이미지 처리)
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show()
    }
}