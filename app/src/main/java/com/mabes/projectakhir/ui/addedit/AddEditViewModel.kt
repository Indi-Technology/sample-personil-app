package com.mabes.projectakhir.ui.addedit

import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mabes.projectakhir.DataRank
import com.mabes.projectakhir.RankResponse
import com.mabes.projectakhir.data.response.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddEditViewModel:ViewModel() {
    private val _listRank = MutableLiveData<List<DataRank>>()
    val listRank:LiveData<List<DataRank>> = _listRank

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading:LiveData<Boolean> = _isLoading

    private fun showRanks(){
        val client = ApiConfig.getApiService().getUserRank()
        client.enqueue(object : Callback<RankResponse> {
            override fun onResponse(call: Call<RankResponse>, response: Response<RankResponse>) {
                if(response.isSuccessful){
                    _listRank.value = response.body()?.data
                }else{
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }

            }

            override fun onFailure(call: Call<RankResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")

            }

        })
    }

}