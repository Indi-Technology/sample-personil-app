package com.mabes.projectakhir.data.remote.response

import com.google.gson.annotations.SerializedName

data class ListStatusResponse(
    @field:SerializedName("data")
    val data: List<DataStatus>
)

data class DataStatus(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("created_at")
    val createdAt: String,

    @field:SerializedName("updated_at")
    val updatedAt: String,

)