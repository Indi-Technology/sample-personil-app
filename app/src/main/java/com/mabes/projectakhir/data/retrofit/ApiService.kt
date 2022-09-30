package com.mabes.projectakhir.data.response.retrofit


import com.mabes.projectakhir.ListRankResponse
import com.mabes.projectakhir.ListUserResponse
import com.mabes.projectakhir.data.response.DetailUserResponse

import com.mabes.projectakhir.data.response.ListStatusResponse
import com.mabes.projectakhir.data.response.SubmitResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("api/pers")
    fun getUserList():Call<ListUserResponse>

    @GET("api/ranks")
    fun getUserRank():Call<ListRankResponse>

    @GET("api/statuses")
    fun getUserStatus():Call<ListStatusResponse>

    @FormUrlEncoded
    @POST("api/pers")
    fun postNewUser(
        @Field("nrp") nrp:String,
        @Field("name") name:String,
        @Field("born_place") born_place:String,
        @Field("born_date") born_date: String,
        @Field("address") address: String,
        @Field("rank_id") rank_id: Int,
        @Field("status_id") status_id: Int,
        @Field("image") image: String ?= null
    ):Call<SubmitResponse>

    @GET("api/pers/{id}")
    fun getUserById(
        @Path("id") id:Int
    ):Call<DetailUserResponse>



}