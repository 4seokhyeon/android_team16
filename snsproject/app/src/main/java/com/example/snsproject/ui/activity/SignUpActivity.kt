package com.example.snsproject.ui.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.snsproject.R
import com.example.snsproject.anim.slideLeft
import com.example.snsproject.manager.MemberManager
import com.example.snsproject.manager.MemberManagerImpl
// textWatcher 로 실시간 해보기
class SignUpActivity : AppCompatActivity() {
    lateinit var memberManager: MemberManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        memberManager = MemberManagerImpl.getInstance()
        val supname = findViewById<EditText>(R.id.edtv_supname)
        val supid = findViewById<EditText>(R.id.edtv_supid)
        val suppw = findViewById<EditText>(R.id.edtv_suppw)

        supid.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkId()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })


//        val supcheck = findViewById<TextView>(R.id.tv_supcheck)
//        val memberid = memberManager.findAllMember()

//        val supidcheck = findViewById<Button>(R.id.bnt_supidcheck)
//        supidcheck.setOnClickListener {
//            if (supid.text.toString() == ""){
//                Toast.makeText(this, "아이디 작성해주세요", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//            val memberid = memberManager.findAllMember()
//            var memver = false
//            for (a in memberid) {
//                if (a.id == supid.text.toString()){
//                    memver = true
//                    break
//                }
//            }
//
//            if (memver) {
//                Toast.makeText(this, "중복된 아이디가 있습니다. 다시 작성해주세요", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this, "사용 가능한 아이디 입니다.", Toast.LENGTH_SHORT).show()
//            }
//        }

        val supdone = findViewById<Button>(R.id.bnt_supdone)

        // 빈칸있나확인하고 멤버에 추가하고 signIn 에 Id,pw 갖다주기.
        supdone.setOnClickListener{
            if(supname.text.toString().isEmpty() ||
                supid.text.toString().isEmpty() ||
                suppw.text.toString().isEmpty()) {
                Toast.makeText(this, "빈칸을 모두 채워 주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } // 이름, 아이디, 비밀번호 빈칸있는지 체크 하는 부분

            if(pwcheck(suppw.text.toString())){
            } else {
                Toast.makeText(this, "비밀번호 형식에 맞춰 작성 해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } // 비밀번호 체크 하는 조건문

            if(checkId()){
            } else {
                Toast.makeText(this, "아이디를 확인 해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } // 아이디 중복되는 체크 하는 조건문

            memberManager.addMember(supid.text.toString(), suppw.text.toString(), supname.text.toString(), "dakyum") // 데이터를 먼저 넣었어서 if 문들이 실행되어 마지막에 빨간 글씨가 되었다.


            val intent = Intent(this,SignInActivity::class.java)
            intent.putExtra("userId", supid.text.toString())
            intent.putExtra("userPw", suppw.text.toString())
            setResult(RESULT_OK, intent)
            finish()
            slideLeft()

        }

        val supcancel = findViewById<Button>(R.id.bnt_supcancel)
        supcancel.setOnClickListener{
            finish()
            slideLeft()
        }
    }

    fun checkId() : Boolean{
        val supid = findViewById<EditText>(R.id.edtv_supid)

        val supcheck = findViewById<TextView>(R.id.tv_supcheck)
        val memberid = memberManager.findAllMember()
        val a = supid.text.toString().trim() // 글자로 변경하고 trim이 공백제거
        var check = false
        for(b in memberid) { //멘버 안에 정보가 b에 들어가고
            if(b.id == a){ //b 중에 id 부분만 갖고와서 앱사용자가 작성한 a 와 비교하여
                supcheck.setTextColor(Color.RED) // 비교 일치시 빨간색 글자로 된다.
                supcheck.text = "중복된 ID가 있습니다." // 앱 내에서 textview를 바꾸는 방법.
                break // 일치하면 붉은색글자를 해두고 for 문을 멈춰야 해서 break를 넣어 반복문을 빠져나온다.
            } else {
                supcheck.setTextColor(Color.BLACK)
                supcheck.text = "사용 가능한 ID 입니다."
                check = true
            }
        }
        return check
    } // 아이디 중복되는지 체크하는 함수

    fun pwcheck(password : String) : Boolean{

        val minLength = 5 // 비밀번호 최소 자릿수
        // 밑에 4개는 모두 Boolean 형태로 반환
        val hasDigit = password.any {it.isDigit()} //  문자열 중에 하나이상이 숫자인지 확인
        val hasLowerCase = password.any {it.isLowerCase()} // 문자열 중에 하나이상의 소문자 있는지 확인
        val hasUpperCase = password.any {it.isUpperCase()} // 문자열 중에 하나이상의 대문자 있는지 확인
        val hasSpecialChar = password.any {!it.isLetterOrDigit()} // 문자 열 중에 영문자나 숫자 제외하고 하나이상의 특수기호가 있는지 확인

        return password.length >= minLength && hasDigit && (hasLowerCase || hasUpperCase)&& hasSpecialChar
    }

    override fun onBackPressed() {
        finish()
        slideLeft()
    }
}