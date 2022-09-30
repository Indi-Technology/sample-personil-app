package com.mabes.projectakhir.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListStatusResponse(

	@field:SerializedName("data")
	val data: List<DataStatus>
) : Parcelable

@Parcelize
data class DataStatus(

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int
) : Parcelable
