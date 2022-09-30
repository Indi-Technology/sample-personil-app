package com.mabes.projectakhir

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RankResponse(

	@field:SerializedName("data")
	val data: List<DataRank>
) : Parcelable

@Parcelize
data class DataRank(

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int
) : Parcelable
