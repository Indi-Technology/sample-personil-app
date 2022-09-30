package com.mabes.projectakhir.data.response.retrofit


import com.mabes.projectakhir.ListUserResponse
import com.mabes.projectakhir.RankResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/pers")
    fun getUserList():Call<ListUserResponse>

    @GET("api/ranks")
    fun getUserRank():Call<RankResponse>
}