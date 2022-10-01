package com.mabes.projectakhir.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.File

@Parcelize
data class SubmitData(

	@field:SerializedName("“name”")
	val name: String,

	@field:SerializedName("“born_date”")
	val bornDate: String,

	@field:SerializedName("“nrp”")
	val nrp: String,

	@field:SerializedName("“born_place”")
	val bornPlace: String,

	@field:SerializedName("“status_id”")
	val statusId: Int,

	@field:SerializedName("“address”")
	val address: String,

	@field:SerializedName("“rank_id”")
	val rankId: Int,

	@field:SerializedName("“image”")
	val image: File? = null
):Parcelable
