package com.mabes.projectakhir.ui.detail

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mabes.projectakhir.data.response.Data
import com.mabes.projectakhir.data.response.BaseResponse
import com.mabes.projectakhir.data.response.DetailUserResponse
import com.mabes.projectakhir.data.response.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel:ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _userData = MutableLiveData<Data>()
    val userData: LiveData<Data> = _userData

    private val _responseMessage = MutableLiveData<String>()
    val responseMessage: LiveData<String> = _responseMessage

    fun getUserById(id:Int){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserById(id)
        client.enqueue(object:Callback<DetailUserResponse>{
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    if (response.body() != null) {
                        _userData.value = response.body()?.data
                        Log.d("_userData:", _userData.value.toString())
                    }
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")

            }

        })
    }

    fun deleteUserById(id: Int){
        _isLoading.value = true
        val client = ApiConfig.getApiService().deleteUser(id)
        client.enqueue(object : Callback<BaseResponse>{
            override fun onResponse(
                call: Call<BaseResponse>,
                response: Response<BaseResponse>,
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    if (response.body() != null) {
                        _responseMessage.value = response.body()!!.message
                    }
                } else {
                    _isLoading.value = false
                    Log.e(ContentValues.TAG, "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure : ${t.message}")
            }

        })
    }

}