package com.example.snsproject.model

data class Member (
        val id: String,
        val pw: String,
        val name: String,
        val mbti: String,
        val profileImg: String, // ex) "keuntae"
        val status: String,
        val post: MutableList<Post>
        )

