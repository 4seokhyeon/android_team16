package com.example.snsproject.ui.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.snsproject.R
import com.example.snsproject.manager.MemberManager
import com.example.snsproject.manager.MemberManagerImpl
import com.google.android.material.internal.ContextUtils.getActivity

class InfoModifyDialog(context: Context, id: String) : Dialog(context) {

    private lateinit var memberManager : MemberManager
    private lateinit var confirmButton : Button
    private lateinit var closeButton : Button
    private var curId = id

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_modify_dialog)

        memberManager = MemberManagerImpl.getInstance()

        confirmButton = findViewById<Button>(R.id.mypage_confirm_btn)
        closeButton = findViewById<Button>(R.id.mypage_close_btn)

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        confirmButton.setOnClickListener {
            val name = findViewById<EditText>(R.id.mypage_name_edittext)
            val mbti = findViewById<EditText>(R.id.mypage_mbti_edittext)
            val status = findViewById<EditText>(R.id.mypage_status_edittext)

            memberManager.findMember(curId)?.let {
                memberManager.updateMember(it.id, it.pw, name.text.toString(), mbti.text.toString(), it.profileImg, status.text.toString(), it.post)
            }
            (getActivity(context) as MyPageActivity).dataChangeListener()
            dismiss()
        }

        closeButton.setOnClickListener {
            dismiss()
        }
    }
}