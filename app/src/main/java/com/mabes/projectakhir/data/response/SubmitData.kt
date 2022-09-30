package com.mabes.projectakhir.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SubmitData(

	@field:SerializedName("image")
	val image: String?=null,

	@field:SerializedName("rank_id")
	val rankId: Int,

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("status_id")
	val statusId: Int,

	@field:SerializedName("born_place")
	val bornPlace: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("nrp")
	val nrp: String,

	@field:SerializedName("born_date")
	val bornDate: String
) : Parcelable
