package com.example.snsproject.manager

import com.example.snsproject.model.Member
import com.example.snsproject.model.Post

interface MemberManager {

    fun addMember(id: String, pw: String, name: String, profileImg: String) : Boolean

    fun findMember(id: String) : Member?
    fun findAllMember() : List<Member>
    fun findMemberIndexOf(id: String) : Int

    fun updateMember(id: String, pw: String, name: String, mbti: String, profileImg: String, status: String, postList: MutableList<Post>) : Boolean

    fun deleteMember(id: String) : Boolean
}