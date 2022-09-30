package com.mabes.projectakhir.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SubmitResponse(

	@field:SerializedName("data")
	val data: SubmitDataResponse,

	@field:SerializedName("message")
	val message: String
) : Parcelable

@Parcelize
data class SubmitDataResponse(

	@field:SerializedName("image")
	val image: String?=null,

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("born_place")
	val bornPlace: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("rank")
	val rank: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("nrp")
	val nrp: String,

	@field:SerializedName("born_date")
	val bornDate: String,

	@field:SerializedName("status")
	val status: String
) : Parcelable
