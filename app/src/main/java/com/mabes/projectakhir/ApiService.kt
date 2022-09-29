package com.mabes.projectakhir

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/pers")
    fun getUserList():Call<ListPersonilResponse>
}