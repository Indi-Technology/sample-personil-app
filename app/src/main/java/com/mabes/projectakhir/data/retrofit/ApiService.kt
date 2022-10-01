package com.mabes.projectakhir.data.remote.retrofit

import com.mabes.projectakhir.data.remote.response.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("api/pers")
    fun getUsersList(
        @Query("search") name: String,
        @Query("perPage") page: Int ?= 100
    ): Call<ListUserResponse>

    @GET("api/statuses")
    fun getStatusList(): Call<ListStatusResponse>

    @GET("api/ranks")
    fun getRanksList(): Call<ListRanksResponse>

    @GET("api/pers/{id}")
    fun getUserById(
        @Path("id") id:Int
    ): Call<DetailUserResponse>

    @DELETE("api/pers/{id}")
    fun deleteUser(
        @Path("id") id:Int
    ): Call<DeleteUserResponse>

    @FormUrlEncoded
//    @Headers("Accept : application/json")
    @POST("api/pers")
    fun submitNewUser(
        @Field("nrp") nrp: String,
        @Field("name") name: String,
        @Field("born_place") born_place: String,
        @Field("born_date") born_date: String,
        @Field("address") address: String,
        @Field("rank_id") rank_id: Int,
        @Field("status_id") status_id: Int,
        @Field("image") image: String ?= null
    ): Call<SubmitResponse>

    @FormUrlEncoded
    @POST("public/api/pers/{id}?_method=PATCH")
    fun submitEditUser(
        @Path("id") id:Int,
        @Field("nrp") nrp: String,
        @Field("name") name: String,
        @Field("born_place") born_place: String,
        @Field("born_date") born_date: String,
        @Field("address") address: String,
        @Field("rank_id") rank_id: Int,
        @Field("status_id") status_id: Int,
        @Field("image") image: String ?= null
    ): Call<SubmitResponse>
}