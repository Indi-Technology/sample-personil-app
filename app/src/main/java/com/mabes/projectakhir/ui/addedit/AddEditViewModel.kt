package com.mabes.projectakhir.ui.addedit

import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mabes.projectakhir.DataRank
import com.mabes.projectakhir.ListRankResponse
import com.mabes.projectakhir.data.response.DataStatus
import com.mabes.projectakhir.data.response.ListStatusResponse
import com.mabes.projectakhir.data.response.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddEditViewModel:ViewModel() {
    private val _listRank = MutableLiveData<List<DataRank>>()
    val listRank:LiveData<List<DataRank>> = _listRank


    private val _listStatus = MutableLiveData<List<DataStatus>>()
    val listStatus:LiveData<List<DataStatus>> = _listStatus

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading:LiveData<Boolean> = _isLoading

    init {
        showRanks()
        showStatuses()
    }

    private fun showRanks(){
        val client = ApiConfig.getApiService().getUserRank()
        client.enqueue(object : Callback<ListRankResponse> {
            override fun onResponse(call: Call<ListRankResponse>, response: Response<ListRankResponse>) {
                if(response.isSuccessful){
                    _listRank.value = response.body()?.data
                }else{
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }

            }

            override fun onFailure(call: Call<ListRankResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")

            }

        })
    }

    private fun showStatuses(){
        val client = ApiConfig.getApiService().getUserStatus()
        client.enqueue(object :Callback<ListStatusResponse>{
            override fun onResponse(
                call: Call<ListStatusResponse>,
                response: Response<ListStatusResponse>
            ) {
                if(response.isSuccessful){
                    if(response.body()!=null) {
                        _listStatus.value = response.body()?.data
                    }
                }
                else{
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")

                }

            }

            override fun onFailure(call: Call<ListStatusResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")

            }

        })
    }


}