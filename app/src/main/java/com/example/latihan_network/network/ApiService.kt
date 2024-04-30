package com.example.latihan_network.network

import com.example.latihan_network.model.PostRespons
import com.example.latihan_network.model.TweetRequest
import com.example.latihan_network.model.TweetRespons
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl("https://api-latihan.rakryan.id/api/")
    .build()

interface ApiService {
    @GET("tweet")
    suspend fun showlist() : TweetRespons

    @POST("tweet")
    suspend fun submiTweet(@Body tweetRequest : TweetRequest) : PostRespons
}

object ApiCall {
    val retrofitService =  retrofit.create(ApiService::class.java)
}