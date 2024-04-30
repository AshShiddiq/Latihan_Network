package com.example.latihan_network.model

data class TweetRespons(
    val succses : Boolean,
    val message : String,
    val data : List<Tweet>
)

data class Tweet(
    val id : Int,
    val teks : String,
    val author : String
)

data class PostRespons(
    val succses : Boolean,
    val message : String,

)