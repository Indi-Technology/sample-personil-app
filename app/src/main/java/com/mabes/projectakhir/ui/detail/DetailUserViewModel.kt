package com.mabes.projectakhir.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mabes.projectakhir.data.response.Data
import com.mabes.projectakhir.data.response.retrofit.ApiConfig

class DetailUserViewModel:ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _userData = MutableLiveData<Data>()
    val userData: LiveData<Data> = _userData

    private val _responseMessage = MutableLiveData<String>()
    val responseMessage: LiveData<String> = _responseMessage

    fun getUserById(id:Int){
        _isLoading.value = true
        val client = ApiConfig.getApiService().get
    }

}