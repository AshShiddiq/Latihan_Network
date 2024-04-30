package com.example.latihan_network.ui.home
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.latihan_network.model.Tweet
import com.example.latihan_network.model.TweetRespons
import com.example.latihan_network.network.ApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class HomeViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response : LiveData<String>
        get() = _response

    private val _teksTweet = MutableLiveData<List<Tweet>>()
    val teksTweet : LiveData<List<Tweet>>
        get() = _teksTweet

    private var vmJob = Job()
    private  val crScope = CoroutineScope(vmJob + Dispatchers.IO)


    init {
        initData()
    }


    fun initData(){
        crScope.launch {
            try {
                val items = ApiCall.retrofitService.showlist()
                val tweetData = items.data
                val tweetMessage = items.message
                val tweetBool = items.succses
                if (tweetData.isNotEmpty()){
                    _teksTweet.postValue(tweetData.reversed())
                    _response.postValue(tweetMessage)
                }

                if(tweetBool){

                }

            }catch (t : Throwable){
                Log.i("dataAPI", t.message.toString())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }

//    fun initData() {
//        ApiCall.retrofitService.showlist().enqueue(object : Callback<TweetRespons>{
//            override fun onResponse(call: Call<TweetRespons>, response: Response<TweetRespons>) {
//                _response.value = response.body()!!.data.get(0).Text
//                _teksTweet.value = response.body()!!.data
//            }
//
//            override fun onFailure(call: Call<TweetRespons>, t: Throwable) {
//                _response.value = "failed / gagal"
//            }
//        })
//        _response.value = "hanya data kosong"
//    }

}