package com.example.snsproject.ui.activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.example.snsproject.R
import com.example.snsproject.adapter.MyPageItemAdapter
import com.example.snsproject.manager.MemberManager
import com.example.snsproject.manager.MemberManagerImpl
import com.example.snsproject.model.Post

class SignInActivity : AppCompatActivity() {
    private lateinit var memberManager: MemberManagerImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        memberManager = MemberManagerImpl.getInstance()

        val loginBtn = findViewById<Button>(R.id.signin_login_btn)
        val signupBtn = findViewById<Button>(R.id.signin_signup_button)

        loginBtn.setOnClickListener {
            val id = findViewById<EditText>(R.id.signin_id_text).text.toString()
            val pw = findViewById<EditText>(R.id.signin_pw_text).text.toString()

            if (id.isEmpty() || pw.isEmpty()) {
                Toast.makeText(this, "빈칸을 입력 해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                val member = memberManager.findMember(id)

                if (member == null) {
                    Toast.makeText(this, "존재하지 않는 사용자입니다. 올바른 아이디를 입력해주세요.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    if (member.pw != pw) {
                        Toast.makeText(this, "올바르지 않은 비밀번호 입니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainPageActivity::class.java)
                        intent.putExtra("userId", id)
                        startActivity(intent)
                    }
                }
            }

        }

        signupBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }
}
