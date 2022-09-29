package com.mabes.projectakhir

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListPersonilResponse(

	@field:SerializedName("data")
	val data: List<DataItem>
) : Parcelable

@Parcelize
data class DataItem(

	@field:SerializedName("image")
	val image: String,

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
