package com.mabes.projectakhir.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BaseResponse(

	@field:SerializedName("message")
	val message: String
) : Parcelable
