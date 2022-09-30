package com.mabes.projectakhir.ui.main

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mabes.projectakhir.DataUser
import com.mabes.projectakhir.ListUserResponse
import com.mabes.projectakhir.data.response.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query


class MainViewModel: ViewModel() {

    private val _listUser = MutableLiveData<List<DataUser>>()
    val listUser: LiveData<List<DataUser>> =_listUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading:LiveData<Boolean> = _isLoading

    fun getList(query: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserList()
        client.enqueue(object :Callback<ListUserResponse>{
            override fun onResponse(
                call: Call<ListUserResponse>,
                response: Response<ListUserResponse>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){

                    if(response.body() != null){
                        _listUser.value = response.body()?.data
                    }
                }
                else{
                    Log.e(ContentValues.TAG, "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ListUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure : ${t.message}")

            }

        })
    }
}