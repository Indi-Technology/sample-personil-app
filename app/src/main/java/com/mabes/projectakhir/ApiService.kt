package com.mabes.projectakhir

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/pers")
    fun getUserList():Call<ListPersonilResponse>

    @GET("api/ranks")
    fun getUserRank():Call<RankResponse>

    @GET("api/statuses")
    fun getUserStatus():Call<StatusResponse>
}