package com.example.latihan_network.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.latihan_network.model.TweetRequest
import com.example.latihan_network.model.TweetRespons
import com.example.latihan_network.network.ApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProfilViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response : LiveData<String>
        get() = _response

    private var vmJob = Job()
    private  val crScope = CoroutineScope(vmJob + Dispatchers.IO)

    init {

    }

    fun post(tweetRequest: TweetRequest) = crScope.launch {

        try {
            val data = ApiCall.retrofitService.submiTweet(tweetRequest)
            _response.postValue(data.message)
        }catch (t : Throwable){
            Log.i("data api", t.toString())
        }
    }

}