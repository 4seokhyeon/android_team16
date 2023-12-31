package com.example.snsproject.manager

import com.example.snsproject.model.Member
import com.example.snsproject.model.Post

interface MemberManager {

    fun addMember(id: String, pw: String, name: String, profileImg: String) : Boolean

    fun findMember(id: String) : Member?

    fun findAllMember() : List<Member>
    fun findMemberIndexOf(id: String) : Int

    fun updateMember(id: String, pw: String, name: String, mbti: String, profileImg: String, status: String, post: MutableList<Post>) : Boolean

    fun deleteMember(id: String) : Boolean

    fun findMemberByAuthor(id: String) : Member?
}