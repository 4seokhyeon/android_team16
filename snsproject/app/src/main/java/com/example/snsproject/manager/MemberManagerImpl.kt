package com.example.snsproject.manager

import com.example.snsproject.model.Comment
import com.example.snsproject.model.Member
import com.example.snsproject.model.Post

class MemberManagerImpl private constructor() : MemberManager {
    private val memberList = mutableListOf<Member>()

    init {
        memberList.add(
            Member("test1",
            "123",
            "dakyum",
            "INFJ",
            "dakyum",
            "online",
            mutableListOf(Post("dakyum",
                "feed",
                "my title",
            "my content",
                    mutableListOf(Comment("keuntae", "hello")))
                )
            )
        )

        memberList.add(
            Member("test2",
                "123",
                "hyemyung",
                "INFP",
                "hyemyung",
                "online",
                mutableListOf(Post("hyemyung",
                    "feed",
                    "my title",
                    "my content",
                    mutableListOf(Comment("keuntae", "hello")))
                )
            )
        )

        memberList.add(
            Member("test3",
                "123",
                "sukhyun",
                "ISFJ",
                "sukhyun",
                "online",
                mutableListOf(Post("sukhyun",
                    "feed",
                    "my title",
                    "my content",
                    mutableListOf(Comment("keuntae", "hello")))
                )
            )
        )

        memberList.add(
            Member("test4",
                "123",
                "kwanghee",
                "INFJ",
                "kwanghee",
                "online",
                mutableListOf(Post("kwanghee",
                    "feed",
                    "my title",
                    "my content",
                    mutableListOf(Comment("keuntae", "hello")))
                )
            )
        )

        memberList.add(
            Member("test5",
                "123",
                "keuntae",
                "ISTP",
                "keuntae",
                "online",
                mutableListOf(Post("keuntae",
                    "feed",
                    "my title",
                    "my content",
                    mutableListOf(Comment("keuntae", "hello")))
                )
            )
        )
    }

    override fun addMember(id: String, pw: String, name: String, profileImg: String): Boolean {
        if (findMember(id) != null) {
            return false
        }
        else {
            val newM = Member(id, pw, name, "", profileImg, "", mutableListOf())
            memberList.add(newM)
            return true
        }
    }

    override fun findMember(id: String): Member? {
        val idx = findMemberIndexOf(id)

        if(idx == -1) {
            return null
        }

        return memberList[idx]
    }

    override fun findAllMember(): List<Member> {
        return memberList.toList()
    }

    override fun deleteMember(id: String): Boolean {
        return findMember(id)?.let { memberList.remove(it)
                                     true } ?: false
    }

    override fun findMemberIndexOf(id: String): Int {

        for ((idx, member) in memberList.withIndex()) {
            if (member.id == id) {
                return idx
            }
        }

        return -1
    }

    override fun updateMember(
        id: String,
        pw: String,
        name: String,
        mbti: String,
        profileImg: String,
        status: String,
        postList: MutableList<Post>
    ): Boolean {
        TODO("Not yet implemented")
    }

    companion object {
        var instance : MemberManagerImpl? = null

        fun getInstance() : MemberManagerImpl {
            return instance ?: synchronized(this) {
                    instance ?: MemberManagerImpl().also {
                        instance = it
                }
            }
        }
    }

}