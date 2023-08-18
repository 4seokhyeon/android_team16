package com.example.snsproject.model

data class Post (
    val author: String,
    val postImg: String, // ex) "feed"
    val title: String,
    val content: String,
    val comment: MutableList<Comment>
    )
