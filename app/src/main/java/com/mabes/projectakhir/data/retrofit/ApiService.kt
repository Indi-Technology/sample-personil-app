package com.mabes.projectakhir.data.response.retrofit

import com.mabes.projectakhir.ListPersonilResponse
import com.mabes.projectakhir.RankResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/pers")
    fun getUserList():Call<ListPersonilResponse>

    @GET("api/ranks")
    fun getUserRank():Call<RankResponse>
}