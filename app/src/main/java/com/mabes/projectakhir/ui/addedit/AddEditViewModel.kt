package com.mabes.projectakhir.ui.addedit

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mabes.projectakhir.data.remote.response.SubmitResponse
import com.mabes.projectakhir.data.remote.response.*
import com.mabes.projectakhir.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddEditViewModel : ViewModel() {

    private val _listStatus = MutableLiveData<List<DataStatus>>()
    val listStatus: LiveData<List<DataStatus>> = _listStatus

    private val _listRanks = MutableLiveData<List<DataRanks>>()
    val listRanks: LiveData<List<DataRanks>> = _listRanks

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _responseMessage = MutableLiveData<String>()
    val responseMessage: LiveData<String> = _responseMessage

    init {
        showStatus()
        showRanks()
    }

    fun submitNewUser(inputData: SubmitData) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().submitNewUser(
            nrp = inputData.nrp,
            name = inputData.name,
            born_place = inputData.bornPlace,
            born_date = inputData.bornDate,
            address = inputData.address,
            rank_id = inputData.rankId,
            status_id = inputData.statusId,
            image = null
        )

        client.enqueue(object : Callback<SubmitResponse> {
            override fun onResponse(
                call: Call<SubmitResponse>,
                response: Response<SubmitResponse>,
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    if (response.body() != null) {
                        _responseMessage.value = response.body()!!.message
//                        _isSubmitSuccess.value = true
                    }
                } else {
                    _isLoading.value = false
                    Log.e(ContentValues.TAG, "onFailure : ${response.message()}")
//                    _isSubmitSuccess.value = false
                }
            }
            override fun onFailure(call: Call<SubmitResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
//                _isSubmitSuccess.value = false
            }
        })
    }

    fun submitEditUser(id: Int , inputData: SubmitData) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().submitEditUser(
            id = id,
            nrp = inputData.nrp,
            name = inputData.name,
            born_place = inputData.bornPlace,
            born_date = inputData.bornDate,
            address = inputData.address,
            rank_id = inputData.rankId,
            status_id = inputData.statusId,
            image = null
        )

        client.enqueue(object : Callback<SubmitResponse> {
            override fun onResponse(
                call: Call<SubmitResponse>,
                response: Response<SubmitResponse>,
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    if (response.body() != null) {
                        _responseMessage.value = response.body()!!.message
//                        _isSubmitSuccess.value = true
                    }
                } else {
                    _isLoading.value = false
                    Log.e(ContentValues.TAG, "onFailure : ${response.message()}")
//                    _isSubmitSuccess.value = false
                }
            }
            override fun onFailure(call: Call<SubmitResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
//                _isSubmitSuccess.value = false
            }
        })
    }


    private fun showStatus() {
        val client = ApiConfig.getApiService().getStatusList()
        client.enqueue(object : Callback<ListStatusResponse> {
            override fun onResponse(
                call: Call<ListStatusResponse>,
                response: Response<ListStatusResponse>
            ) {
                if(response.isSuccessful) {
                    _listStatus.value = response.body()?.data
                } else {
                    Log.e(ContentValues.TAG, "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ListStatusResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }

    private fun showRanks() {
        val client = ApiConfig.getApiService().getRanksList()
        client.enqueue(object : Callback<ListRanksResponse> {
            override fun onResponse(
                call: Call<ListRanksResponse>,
                response: Response<ListRanksResponse>
            ) {
                if (response.isSuccessful) {
                    _listRanks.value = response.body()?.data
                } else {
                    Log.e(ContentValues.TAG, "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ListRanksResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }



}