package com.example.snsproject.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.snsproject.R
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

        val supidcheck = findViewById<Button>(R.id.bnt_supidcheck)
        supidcheck.setOnClickListener {
            if (supid.text.toString() == ""){
                Toast.makeText(this, "아이디 작성해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val memberid = memberManager.findAllMember()
            var memver = false
            for (a in memberid) {
                if (a.id == supid.text.toString()){
                    memver = true
                    break
                }
            }

            if (memver) {
                Toast.makeText(this, "중복된 아이디가 있습니다. 다시 작성해주세요", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "사용 가능한 아이디 입니다.", Toast.LENGTH_SHORT).show()
            }
        }


        val supdone = findViewById<Button>(R.id.bnt_supdone) // 빈칸있나확인하고 멤버에 추가하고 signIn 에 Id,pw 갖다주기.

        supdone.setOnClickListener{
            if(supname.text.toString().isEmpty() ||
                supid.text.toString().isEmpty() ||
                suppw.text.toString().isEmpty()) {
                Toast.makeText(this, "빈칸을 모두 채워 주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            memberManager.addMember(supid.text.toString(), suppw.text.toString(), supname.text.toString(), "dakyum")

            finish()
//            val intent = Intent(this,SignInActivity::class.java)
//            val newMId = intent.putExtra("newID", supid.text.toString())
//            val newMPw = intent.putExtra("newPW", suppw.text.toString())
//            startActivity(newMPw)
//            startActivity(newMId)
        }

        val supcancel = findViewById<Button>(R.id.bnt_supcancel)
        supcancel.setOnClickListener{ // 취소버튼 누르면 뒤로가진다. signIn 페이지로 가는지 확인하기
            finish()
        }
    }
}